package com.matrixcalc.controllers;

import com.matrixcalc.entities.User;
import com.matrixcalc.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            User user,
            String passwordConfirm,
            MultipartFile file,
            Model model
    ) throws IOException {
        String result = userService.addUser(user, passwordConfirm, file);

        if (result != null) {
            model.addAttribute("message", result);
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    private String activation(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("activate", "Почта успешно привязана");
        } else {
            model.addAttribute("activate", "Код активации не найден");
        }

        return "/login";
    }
}