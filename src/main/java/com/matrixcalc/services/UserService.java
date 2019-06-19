package com.matrixcalc.services;

import com.matrixcalc.entities.User;
import com.matrixcalc.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private enum MessageType {
        ACTIVATE, DEACTIVATE, CHANGE_PASSWORD
    }

    @Value("${upload.path}")
    private String uploadPath;

    private final UserRepo userRepo;
    private final MailSender mailSender;

    public UserService(UserRepo userRepo, MailSender mailSender) {
        this.userRepo = userRepo;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        return user;
    }

    private void sendMessage(User user, String email, MessageType messageType) {
        if (!StringUtils.isEmpty(email)) {
            String message, code, subject;
            message = subject = null;
            code = UUID.randomUUID().toString();

            switch (messageType) {
                case ACTIVATE:
                    message = "Добро пожаловать на наш сервис MatrixCalc. Пожалуйста, перейдите по следующей ссылке для подтверждения вашей электронной почты: http://localhost:8080/activate/";
                    user.setActivationCode(code);
                    subject = "Код активации";
                    break;
                case DEACTIVATE:
                    message = "Для смены электронной почты перейдите по следующей ссылке, чтобы сбросить текущую почту: http://localhost:8080/deactivate/";
                    user.setDeactivationCode(code);
                    subject = "Код деактивации";
                    break;
                case CHANGE_PASSWORD:
                    message = "Для смены пароля перейдите по следующей ссылке: http://localhost:8080/change-password/";
                    user.setPasswordChangeCode(code);
                    subject = "Смена пароля";
            }

            String msg = String.format(
                    "Здравствуйте, %s! \n" +
                            message + "%s",
                    user.getNickname(),
                    code
            );

            mailSender.send(email, subject, msg);
        }
    }

    private boolean isUniqueEmail(String email) {
        User userByEmail = userRepo.findByEmail(email);

        return userByEmail == null;
    }

    public String addUser(User user, MultipartFile file) throws IOException {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return "Пользователь с таким именем уже существует!";
        }

        if (!StringUtils.isEmpty(user.getEmail()) && !isUniqueEmail(user.getEmail())) {
            return "Введенная почта уже используется";
        }

        user.setInitialParams();

        sendMessage(user, user.getNewEmail(), MessageType.ACTIVATE);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            user.setAvatarFileName(resultFileName);
        } else {
            user.setAvatarFileName("default_avatar.png");
        }

        userRepo.save(user);
        return null;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        if (!StringUtils.isEmpty(user.getEmail()) && !StringUtils.isEmpty(user.getDeactivationCode())) {
            return false;
        }

        user.setActivationCode(null);
        user.setEmail(user.getNewEmail());
        user.setNewEmail("");
        userRepo.save(user);

        return true;
    }

    public boolean deactivateUser(String code) {
        User user = userRepo.findByDeactivationCode(code);

        if (user == null) {
            return false;
        }

        user.setDeactivationCode(null);
        user.setEmail("");
        userRepo.save(user);

        return true;
    }

    public boolean changeUserData(User user, String nickname, String password, String newEmail, MultipartFile file) throws IOException {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (newEmail != null && !newEmail.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(newEmail));

        if (isEmailChanged) {
            if (!StringUtils.isEmpty(userEmail)) {
                sendMessage(user, userEmail, MessageType.DEACTIVATE);
            }

            if (StringUtils.isEmpty(newEmail)) {
                if (StringUtils.isEmpty(userEmail)) {
                    user.setEmail("");
                } else {
                    user.setNewEmail("");
                }
                userRepo.save(user);

                return true;
            }

            if (isUniqueEmail(newEmail)) {
                user.setNewEmail(newEmail);
                sendMessage(user, newEmail, MessageType.ACTIVATE);
            } else {
                return false;
            }
        }

        if (!StringUtils.isEmpty(password) && !user.getPassword().equals(password)) {
            if (StringUtils.isEmpty(user.getEmail())) {
                user.setPassword(password);
            } else {
                user.setNewPassword(password);
                sendMessage(user, user.getEmail(), MessageType.CHANGE_PASSWORD);
            }
        }

        if (!StringUtils.isEmpty(nickname) && !user.getNickname().equals(nickname)) {
            user.setNickname(nickname);
        }

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String resultFileName;
            if (user.getAvatarFileName().equals("default_avatar.png")) {
                String uuidFile = UUID.randomUUID().toString();
                resultFileName = uuidFile + "." + file.getOriginalFilename();
            } else {
                resultFileName = user.getAvatarFileName();
            }

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            user.setAvatarFileName(resultFileName);
        }

        userRepo.save(user);

        return true;
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).get();
    }
}
