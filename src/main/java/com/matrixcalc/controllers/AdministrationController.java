package com.matrixcalc.controllers;

import com.matrixcalc.entities.Role;
import com.matrixcalc.entities.User;
import com.matrixcalc.repositories.UserRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AdministrationController {
    private UserRepo userRepo;

    public AdministrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/change-moder/{id}")
    public @ResponseBody boolean changeModerationRights(@PathVariable Long id, @AuthenticationPrincipal User currentUser) {
        if (!currentUser.isAdmin()) {
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
        if (!currentUser.isAdmin() && !currentUser.isModer()) {
            return "login";
        }

        User user = userRepo.findById(id).get();
        if (user.isUser()) {
            user.setActive(!user.isActive());
        }

        userRepo.save(user);

        return "profile";
    }
}