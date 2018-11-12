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
        model.addAttribute("message", "fock u");
        return "greeting";
    }

    @PostMapping("/answer")
    public @ResponseBody double showAnswer(@RequestBody Matrices matrices) {
        double[][][] values = matrices.getNumbers();
        int cols = matrices.getCols(), rows = matrices.getRows();
        String operator = matrices.getOperator();
        switch (operator) {
            case "Сложение":
                values[0] = MatrixFunctions.sum(values[0], values[1]);
                break;
            case "Вычитание":
                values[0] = MatrixFunctions.sub(values[0], values[1]);
                break;
            case "Умножение":

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

        double out = 0;
        for (double[] x : values[0]) {
            for (double y : x) {
                out += y;
            }
        }
        return out;
    }
}