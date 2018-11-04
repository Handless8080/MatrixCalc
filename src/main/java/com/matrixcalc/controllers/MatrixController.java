package com.matrixcalc.controllers;

import com.matrixcalc.functions.MatrixFunctions;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class MatrixController {
    @GetMapping("/")
    public String main (Model model) {
        return "main";
    }

    @GetMapping("greeting")
    public String greeting(Model model) {
        model.addAttribute("message", "fock u");
        return "greeting";
    }

    @PostMapping("/")
    @ResponseBody
    public String showAnswer(
            /*@RequestParam(name="number") double[] numbers,
            @RequestParam(name="cols") int cols,
            @RequestParam(name="rows") int rows,
            @RequestParam(name="operators") String operator,
            Model model*/
    ) {
        /*double[][][] matrices;
        switch (operator) {
            case "Сложение":
            case "Вычитание":
            case "Умножение":
                matrices = MatrixFunctions.splitArray(numbers, 2, cols, rows);
                break;
            default:
                matrices = MatrixFunctions.splitArray(numbers, 1, cols, rows);
        }
        switch (operator) {
            case "Сложение":
                matrices[0] = MatrixFunctions.sum(matrices[0], matrices[1]);
                break;
            case "Вычитание":
                matrices[0] = MatrixFunctions.sub(matrices[0], matrices[1]);
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
        ArrayList<ArrayList<Double>> list = MatrixFunctions.createAnswer(matrices[0]);
        model.addAttribute("answer", list);*/
        String result = "main";
        return result;
    }

    /*private void out(int[][] arr) {
        for (int[] x : arr) {
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }*/
}