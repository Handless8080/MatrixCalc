package com.matrixcalc.controllers;

import com.matrixcalc.entities.Theme;
import com.matrixcalc.entities.User;
import com.matrixcalc.services.ThemeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    public String createTheme(
            @Valid Theme theme,
            BindingResult bindingResult,
            MultipartFile file,
            @AuthenticationPrincipal User author,
            Model model
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
            model.addAttribute("theme", theme);

            return "create-theme";
        }

        if (StringUtils.isEmpty(theme.getCategory()) || !ControllerUtils.validateThemeCategory(theme.getCategory())) {
            return "create-theme";
        }

        themeService.createTheme(theme, file, author);
        model.addAttribute("category", "alg");

        List<Theme> themes = themeService.getThemes(themeService.getThemeByUrl("alg"));
        model.addAttribute("themes", themes);

        return "forum";
    }
}
