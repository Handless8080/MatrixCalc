package com.matrixcalc.controllers;

import com.matrixcalc.entities.User;
import com.matrixcalc.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserRepo userRepo;

    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model, String passwordConfirm) {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null) {
            model.addAttribute("message", "Такой пользователь уже существует!");
            return "registration";
        }

        if (!user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("message", "Пароли не совпадают!");
            return "registration";
        }

        user.setInitialParams();

        user.setEmail("");
        userRepo.save(user);

        return "redirect:/login";
    }
}
