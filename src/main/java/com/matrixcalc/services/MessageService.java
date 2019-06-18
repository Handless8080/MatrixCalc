package com.matrixcalc.services;

import com.matrixcalc.entities.Message;
import com.matrixcalc.entities.Theme;
import com.matrixcalc.entities.User;
import com.matrixcalc.repositories.MessageRepo;
import com.matrixcalc.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class MessageService {
    @Value("${upload.path}")
    private String uploadPath;

    private final MessageRepo messageRepo;
    private final UserRepo userRepo;

    public MessageService(MessageRepo messageRepo, UserRepo userRepo) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }

    public void createMessage(Message message, MultipartFile file, User author, Theme theme) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            message.setFilePath(resultFileName);
        }

        message.setCreationDate();
        message.setAuthor(author);
        message.setTheme(theme);

        author.setMessageCount(author.getMessageCount() + 1);

        userRepo.save(author);
        messageRepo.save(message);
    }

    public Message getMessageById(Long id) {
        return messageRepo.findById(id).get();
    }
}
