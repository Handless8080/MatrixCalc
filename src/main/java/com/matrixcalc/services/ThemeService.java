package com.matrixcalc.services;

import com.matrixcalc.entities.Theme;
import com.matrixcalc.entities.User;
import com.matrixcalc.repositories.ThemeRepo;
import com.matrixcalc.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ThemeService {
    @Value("${upload.path}")
    private String uploadPath;

    private final ThemeRepo themeRepo;
    private final UserRepo userRepo;

    public ThemeService(ThemeRepo themeRepo, UserRepo userRepo) {
        this.themeRepo = themeRepo;
        this.userRepo = userRepo;
    }

    public void createTheme(Theme theme, MultipartFile file, User author) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            theme.setFilePath(resultFileName);
        }

        theme.setCreationDate();
        theme.setAuthor(author);

        author.setThemeCount(author.getThemeCount() + 1);

        userRepo.save(author);
        themeRepo.save(theme);
    }

    public List<Theme> getThemes(String category) {

        return themeRepo.findByCategoryOrderByRateDesc(category);
    }

    public List<Theme> getThemesByFilter(String category, String filter) {
        List<Theme> list = getThemes(category);

        List<Theme> buffer = new ArrayList<>();

        for (Theme theme : list) {
            if (theme.getName().contains(filter) || theme.getText().contains(filter)) {
                buffer.add(theme);
            }
        }

        return buffer;
    }

    public String getThemeByUrl(String url) {
        String category;

        switch (url) {
            case "lalg":
                category = "Линейная алгебра";
                break;
            case "geom":
                category = "Геометрия";
                break;
            case "dmath":
                category = "Дискретная математика";
                break;
            case "theor":
                category = "Теория вероятностей";
                break;
            case "mathstat":
                category = "Математическая статистика";
                break;
            default:
                category = "Алгебра";
        }

        return category;
    }

    public Theme getThemeById(Long id) {
        return themeRepo.findById(id).get();
    }
}
