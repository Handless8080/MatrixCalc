package com.matrixcalc.controllers;

import com.matrixcalc.entities.Message;
import com.matrixcalc.entities.Role;
import com.matrixcalc.entities.Theme;
import com.matrixcalc.entities.User;
import com.matrixcalc.repositories.MessageRepo;
import com.matrixcalc.repositories.ThemeRepo;
import com.matrixcalc.repositories.UserRepo;
import com.matrixcalc.services.ThemeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdministrationController {
    private final UserRepo userRepo;
    private final MessageRepo messageRepo;
    private final ThemeRepo themeRepo;

    private final ThemeService themeService;

    public AdministrationController(UserRepo userRepo, MessageRepo messageRepo, ThemeRepo themeRepo, ThemeService themeService) {
        this.userRepo = userRepo;
        this.messageRepo = messageRepo;
        this.themeRepo = themeRepo;

        this.themeService = themeService;
    }

    @PostMapping("/change-moder/{id}")
    public @ResponseBody boolean changeModerationRights(@PathVariable Long id, @AuthenticationPrincipal User currentUser) {
        if (!currentUser.isAdmin()) {
            return false;
        }

        if (!userRepo.existsById(id)) {
            return false;
        }

        User user = userRepo.findById(id).get();
        Set<Role> roles = new HashSet<>();
        if (!user.isAdmin()) {
            if (user.isModer()) {
                roles.add(Role.USER);
            } else {
                roles.add(Role.MODER);
            }
        } else {
            return false;
        }

        user.setRoles(roles);
        userRepo.save(user);

        return true;
    }

    @PostMapping("/block-user/{id}")
    public String blockUser(@PathVariable Long id, @AuthenticationPrincipal User currentUser) {
        if (currentUser.isUser()) {
            return "login";
        }

        if (!userRepo.existsById(id)) {
            return "login";
        }

        User user = userRepo.findById(id).get();
        if (user.isUser()) {
            user.setActive(!user.isActive());
        }

        userRepo.save(user);

        return "profile";
    }

    @PostMapping("/delete-message/{id}")
    public @ResponseBody boolean deleteMessage(@PathVariable Long id, @AuthenticationPrincipal User currentUser) {
        if (currentUser.isUser()) {
            return false;
        }

        Message message = messageRepo.findById(id).get();
        messageRepo.delete(message);

        return true;
    }

    @PostMapping("/delete-theme/{id}")
    public String deleteTheme(@PathVariable Long id, @AuthenticationPrincipal User currentUser, Model model) {
        if (currentUser.isUser()) {
            return "login";
        }

        if (!themeRepo.existsById(id)) {
            return "login";
        }

        Theme theme = themeRepo.findById(id).get();
        for (Message message : theme.getMessages()) {
            messageRepo.delete(message);
        }
        themeRepo.delete(theme);

        List<Theme> themes = themeService.getThemes(themeService.getThemeByUrl("alg"));

        model.addAttribute("themes", themes);
        model.addAttribute("category", "alg");
        return "forum";
    }
}