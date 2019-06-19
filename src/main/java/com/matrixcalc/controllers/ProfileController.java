package com.matrixcalc.controllers;

import com.matrixcalc.entities.User;
import com.matrixcalc.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ProfileController {
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @PostMapping("/profile")
    public String changeUserData(
            @AuthenticationPrincipal User user,
            String nickname,
            String password,
            String email,
            MultipartFile file,
            Model model
    ) throws IOException {
        if (nickname.length() < 3 || password.length() < 5 || (!StringUtils.isEmpty(email) && !ControllerUtils.validateEmail(email))) {
            if (nickname.length() < 3) {
                model.addAttribute("nicknameError", "Длина имени должна быть от 3 до 15");
            }
            if (password.length() < 5) {
                model.addAttribute("passwordError", "Длина пароля должна быть от 5 до 25");
            }
            if (!StringUtils.isEmpty(email) && !ControllerUtils.validateEmail(email)) {
                model.addAttribute("emailError", "Почта указана неверно");
            }
        } else {
            boolean correct = userService.changeUserData(user, nickname, password, email, file);

            StringBuilder message = new StringBuilder("Изменения сохранены");
            if (!StringUtils.isEmpty(user.getActivationCode()) || !StringUtils.isEmpty(user.getDeactivationCode())) {
                message.append(". Для подтверждения изменения почты проверьте старую почту, затем новую");
            }
            if (!StringUtils.isEmpty(user.getPasswordChangeCode())) {
                message.append(". Для подтверждения изменения пароля проверьте почту");
            }

            if (correct) {
                model.addAttribute("success", message.toString());
            } else {
                model.addAttribute("emailError", "Введенная почта уже используется");
            }
        }

        return "profile";
    }

    @GetMapping("/profile/{id}")
    public String showUserProfile(@PathVariable Long id, @AuthenticationPrincipal User currentUser, Model model) {
        User user = userService.getUserById(id);

        if (currentUser == null || !user.getId().equals(currentUser.getId())) {
            model.addAttribute("u", user);
        }

        return "profile";
    }
}
