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
        return userRepo.findByUsername(username);
    }

    public String addUser(User user, String passwordConfirm, MultipartFile file) throws IOException {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return "Такой пользователь уже существует!";
        }

        if (!user.getPassword().equals(passwordConfirm)) {
            return "Пароли не совпадают!";
        }

        user.setInitialParams();
        user.setActivationCode(UUID.randomUUID().toString());

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Здравствуйте, %s! \n" +
                            "Добро пожаловать на наш сервис MatrixCalc. Пожалуйста, посетите следующую ссылку для подтверждения вашей электронной почты: http://localhost:8080/activate/%s",
                    user.getNickname(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Код активации", message);
        }

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            user.setAvatarFileName(resultFileName);
        } else {
            user.setAvatarFileName("");
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
}
