package com.matrixcalc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ForumController {
    @GetMapping("/forum")
    public String forum(){
        return "forum";
    }

    @PostMapping("/filter")
    public String getThemesByFilter(String category, String filter){
        if (!StringUtils.isEmpty(filter)){

        }

        return "forum";
    }

    @GetMapping("/forum/theme/*")
    public String theme() {
        return "theme-form";
    }
}
