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

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            user.setActivationCode(UUID.randomUUID().toString());

            String message = String.format(
                    "Здравствуйте, %s! \n" +
                            "Добро пожаловать на наш сервис MatrixCalc. Пожалуйста, перейдите по следующей ссылке для подтверждения вашей электронной почты: http://localhost:8080/activate/%s",
                    user.getNickname(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Код активации", message);
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

        sendMessage(user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            user.setAvatarFileName(resultFileName);
        }

        userRepo.save(user);
        return null;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);
        userRepo.save(user);

        return true;
    }

    public boolean changeUserData(User user, String nickname, String password, String email) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged) {
            user.setEmail(email);

            if (!StringUtils.isEmpty(email) && isUniqueEmail(email)) {
                sendMessage(user);
            } else {
                return false;
            }
        }

        if (!StringUtils.isEmpty(password) && !user.getPassword().equals(password)) {
            user.setPassword(password);
        }

        if (!StringUtils.isEmpty(nickname) && !user.getNickname().equals(nickname)) {
            user.setNickname(nickname);
        }

        userRepo.save(user);

        return true;
    }
}
