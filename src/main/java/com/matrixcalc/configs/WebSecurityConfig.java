package com.matrixcalc.configs;

import com.matrixcalc.services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    public WebSecurityConfig(DataSource dataSource, UserService userService) {
        this.userService = userService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers
                            ("/",
                                    "/sum-sub-mul",
                                    "/pow-rev-tran",
                                    "/det-rank",
                                    "/js/**",
                                    "/css/**",
                                    "/favicon.ico",
                                    "/registration",
                                    "/activate/*",
                                    "/forum",
                                    "/forum/theme/*"
                            ).permitAll()
                    .antMatchers
                            (
                                    "/profile",
                                    "/forum/create-theme"
                            ).authenticated()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
