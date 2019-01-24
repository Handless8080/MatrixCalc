package com.matrixcalc.controllers;

import com.matrixcalc.bodies.Matrices;
import com.matrixcalc.functions.MatrixFunctions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class MatrixController {
    @GetMapping("/")
    public String main (Model model) {
        return "main";
    }

    @GetMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("message", "message");
        return "greeting";
    }

    @PostMapping("/sum-sub-mul")
    public @ResponseBody BigDecimal[][] showAnswer1(@RequestBody Matrices matrices) {
        BigDecimal[][][] values = matrices.getNumbers();

        switch (matrices.getOperator()) {
            case "Сложение":
                values[0] = MatrixFunctions.sum(values);
                break;
            case "Вычитание":
                values[0] = MatrixFunctions.sub(values);
                break;
            case "Умножение":
                values[0] = MatrixFunctions.mul(values);
        }
        return values[0];
    }

    @PostMapping("/pow-rev-tran")
    public @ResponseBody BigDecimal[][][] showAnswer2(@RequestBody Matrices matrices) {
        BigDecimal[][][] values = matrices.getNumbers();

        switch (matrices.getOperator()) {
            case "Воззведение в степень":
                break;
            case "Найти обратную":
                values = MatrixFunctions.inverse(values);
                break;
            case "Транспонировать":
                values = MatrixFunctions.transpose(values);
        }
        return values;
    }

    @PostMapping("/det-rank")
    public @ResponseBody BigDecimal[] showAnswer3(@RequestBody Matrices matrices) {
        BigDecimal[][][] values = matrices.getNumbers();
        BigDecimal[] result = new BigDecimal[values.length];

        switch (matrices.getOperator()) {
            case "Найти ранг":
                break;
            case "Найти определитель":
                result = MatrixFunctions.det(values);
        }
        return result;
    }
}