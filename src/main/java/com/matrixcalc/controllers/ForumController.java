package com.matrixcalc.controllers;

import com.matrixcalc.entities.*;
import com.matrixcalc.entities.compositekeys.RatedMessagesKeys;
import com.matrixcalc.entities.compositekeys.RatedThemesKey;
import com.matrixcalc.repositories.*;
import com.matrixcalc.services.MessageService;
import com.matrixcalc.services.ThemeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
public class ForumController {
    private final ThemeService themeService;
    private final MessageService messageService;

    private final RatedThemesRepo ratedThemesRepo;
    private final RatedMessagesRepo ratedMessagesRepo;

    private final ThemeRepo themeRepo;
    private final MessageRepo messageRepo;
    private final UserRepo userRepo;

    public ForumController(
            ThemeService themeService,
            MessageService messageService,
            RatedMessagesRepo ratedMessagesRepo,
            RatedThemesRepo ratedThemesRepo,
            ThemeRepo themeRepo,
            MessageRepo messageRepo,
            UserRepo userRepo
    ) {
        this.themeService = themeService;
        this.messageService = messageService;

        this.ratedMessagesRepo = ratedMessagesRepo;
        this.ratedThemesRepo = ratedThemesRepo;

        this.themeRepo = themeRepo;
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/forum/{category}")
    public String forum(Model model, @PathVariable String category){
        List<Theme> themes = themeService.getThemes(themeService.getThemeByUrl(category));

        model.addAttribute("themes", themes);
        model.addAttribute("category", category);

        return "forum";
    }

    @PostMapping("/forum/{category}/filter")
    public String getThemesByFilter(@PathVariable String category, String filter, Model model){
        List<Theme> themes;
        if (!StringUtils.isEmpty(filter)){
            themes = themeService.getThemesByFilter(themeService.getThemeByUrl(category), filter);

            model.addAttribute("filter", filter);
        } else {
            themes = themeService.getThemes(themeService.getThemeByUrl(category));
        }
        model.addAttribute("themes", themes);
        model.addAttribute("category", category);

        return "forum";
    }

    @GetMapping("/forum/theme/{themeId}")
    public String theme(@PathVariable Long themeId, Model model) {
        Theme theme = themeService.getThemeById(themeId);

        if (theme != null) {
            model.addAttribute("theme", theme);
        }

        return "theme-form";
    }

    @PostMapping("/forum/theme/{themeId}")
    public String addMessage(
            @PathVariable Long themeId,
            Message message,
            @AuthenticationPrincipal User user,
            MultipartFile file,
            Model model
    ) throws IOException {
        Theme theme = themeService.getThemeById(themeId);

        if (theme == null) {
            return "theme-form";
        }

        messageService.createMessage(message, file, user, theme);
        model.addAttribute("theme", theme);

        return "theme-form";
    }

    @PostMapping("/change-theme-rate/{themeId}")
    public @ResponseBody boolean changeThemeRate(@AuthenticationPrincipal User user, @PathVariable Long themeId, boolean add) {
        Theme theme = themeService.getThemeById(themeId);

        if (theme == null) {
            return false;
        }

        User author = theme.getAuthor();

        RatedThemes ratedTheme = new RatedThemes();
        RatedThemesKey id = new RatedThemesKey();

        id.setThemeId(themeId);
        id.setUserId(author.getId());

        ratedTheme.setId(id);
        if (ratedThemesRepo.existsById(id)) {
            return false;
        }
        ratedThemesRepo.save(ratedTheme);

        int operator = add ? 1 : -1;

        theme.setRate(theme.getRate() + operator);
        author.setRate(author.getRate() + operator);
        themeRepo.save(theme);
        userRepo.save(author);

        return true;
    }

    @PostMapping("/change-message-rate/{messageId}")
    public @ResponseBody boolean changeMessageRate(@AuthenticationPrincipal User user, @PathVariable Long messageId, boolean add) {
        Message message = messageService.getMessageById(messageId);

        if (message == null) {
            return false;
        }

        User author = message.getAuthor();

        RatedMessages ratedMessage = new RatedMessages();
        RatedMessagesKeys id = new RatedMessagesKeys();

        id.setMessageId(messageId);
        id.setUserId(author.getId());

        ratedMessage.setId(id);
        if (ratedMessagesRepo.existsById(id)) {
            return false;
        }
        ratedMessagesRepo.save(ratedMessage);

        int operator = add ? 1 : -1;

        message.setRate(message.getRate() + operator);
        author.setRate(author.getRate() + operator);
        messageRepo.save(message);
        userRepo.save(author);

        return true;
    }
}
