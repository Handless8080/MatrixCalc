package com.matrixcalc.controllers;

import com.matrixcalc.entities.User;
import com.matrixcalc.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @PostMapping("/profile")
    public String changeUserData(@AuthenticationPrincipal User user, String nickname, String password, String email, Model model) {
        userService.changeUserData(user, nickname, password, email);

        model.addAttribute("message", "Изменения сохранены");

        return "profile";
    }
}
