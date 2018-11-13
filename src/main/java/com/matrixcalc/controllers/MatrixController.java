package com.matrixcalc.controllers;

import com.matrixcalc.bodies.Matrices;
import com.matrixcalc.functions.MatrixFunctions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/answer")
    public @ResponseBody double[][] showAnswer(@RequestBody Matrices matrices) {
        double[][][] values = matrices.getNumbers();

        switch (matrices.getOperator()) {
            case "Сложение":
                values[0] = MatrixFunctions.sum(values);
                break;
            case "Вычитание":
                values[0] = MatrixFunctions.sub(values);
                break;
            case "Умножение":
                values[0] = MatrixFunctions.mul(values);
                break;
            case "Возведение в степень":

                break;
            case "Найти определитель":

                break;
            case "Найти обратную":

                break;
            case "Транспонировать":

                break;
            case "Найти ранг":

        }
        return values[0];
    }
}