package com.matrixcalc.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Length(min = 5, max = 30, message = "Длина названия темы должна быть от 5 до 30")
    private String name;
    @Length(min = 10, max = 250, message = "Длина сообщения темы должна быть от 10 до 250")
    private String text;
    private String filePath;
    private int rate;
    private String creationDate;
    private String category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "theme")
    private List<Message> messages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {

        this.text = text;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate() {
        creationDate = DateFormat.getDateInstance(SimpleDateFormat.LONG, new Locale("ru")).format(new Date());
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
