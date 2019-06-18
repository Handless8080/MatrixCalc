package com.matrixcalc.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class ControllerUtils {
    static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }

    static boolean validateEmail(String email) {
        String regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    static boolean validateThemeCategory(String category) {
        switch (category) {
            case "Алгебра":
            case "Линейная алгебра":
            case "Геометрия":
            case "Дискретная математика":
            case "Теория вероятностей":
            case "Математическая статистика":
                return true;
        }
        
        return false;
    }
}