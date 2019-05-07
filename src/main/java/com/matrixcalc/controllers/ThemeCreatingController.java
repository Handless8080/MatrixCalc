package com.matrixcalc.controllers;

import com.matrixcalc.entities.Theme;
import com.matrixcalc.entities.User;
import com.matrixcalc.services.ThemeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ThemeCreatingController {
    private final ThemeService themeService;

    public ThemeCreatingController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping("/forum/create-theme")
    public String createTheme(){
        return "create-theme";
    }

    @PostMapping("/forum/create-theme")
    public String createTheme(Theme theme, MultipartFile file, @AuthenticationPrincipal User author) throws IOException {
        themeService.createTheme(theme, file, author);

        return "forum";
    }
}
