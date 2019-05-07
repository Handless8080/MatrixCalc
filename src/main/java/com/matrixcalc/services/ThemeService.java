package com.matrixcalc.services;

import com.matrixcalc.entities.Theme;
import com.matrixcalc.entities.User;
import com.matrixcalc.repositories.ThemeRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ThemeService {
    @Value("${upload.path}")
    private String uploadPath;

    private final ThemeRepo themeRepo;

    public ThemeService(ThemeRepo themeRepo) {
        this.themeRepo = themeRepo;
    }

    public void createTheme(Theme theme, MultipartFile file, User author) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            theme.setFilePath(resultFileName);
        }

        theme.setAuthor(author);

        themeRepo.save(theme);
    }
}
