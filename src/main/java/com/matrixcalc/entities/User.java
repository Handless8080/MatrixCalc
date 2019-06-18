package com.matrixcalc.entities;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Length(min = 3, max = 15, message = "Длина логина должна быть от 3 до 15")
    private String username;
    @Length(min = 3, max = 15, message = "Длина имени должна быть от 3 до 15")
    private String nickname;
    @Length(min = 5, max = 25, message = "Длина пароля должна быть от 5 до 25")
    private String password;
    private boolean active;

    @Email(message = "Почта указана неверно")
    private String email;

    private String activationCode;
    private String deactivationCode;
    private String passwordChangeCode;

    private String newEmail;
    private String newPassword;

    private String avatarFileName;

    private String createAccountDate;
    private int themeCount;
    private int messageCount;
    private int rate;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public String getDate() {
        return createAccountDate;
    }

    public String getPasswordChangeCode() {
        return passwordChangeCode;
    }

    public void setPasswordChangeCode(String passwordChangeCode) {
        this.passwordChangeCode = passwordChangeCode;
    }

    public boolean isModer() {
        return  roles.contains(Role.MODER);
    }

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public boolean isUser() {
        return roles.contains(Role.USER);
    }

    public void setInitialParams() {
        roles = Collections.singleton(Role.USER);
        active = true;

        createAccountDate = DateFormat.getDateInstance(SimpleDateFormat.LONG, new Locale("ru")).format(new Date());

        newEmail = email;
        email = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeactivationCode() {
        return deactivationCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public void setDeactivationCode(String deactivationCode) {
        this.deactivationCode = deactivationCode;
    }

    public String getAvatarFileName() {
        return avatarFileName;
    }

    public void setAvatarFileName(String avatarFileName) {
        this.avatarFileName = avatarFileName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public int getThemeCount() {
        return themeCount;
    }

    public void setThemeCount(int themeCount) {
        this.themeCount = themeCount;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }
}