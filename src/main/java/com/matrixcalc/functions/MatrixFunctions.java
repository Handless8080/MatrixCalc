package com.matrixcalc.functions;

import java.util.ArrayList;

public class MatrixFunctions {
    /*public static ArrayList<ArrayList<Double>> createAnswer(double[][] input) {
        ArrayList<ArrayList<Double>> list = new ArrayList<>();
        for (double[] x : input) {
            ArrayList<Double> buf = new ArrayList<>();
            for (double y : x) {
                buf.add(y);
            }
            list.add(buf);
        }
        return list;
    }

    public static double[][][] splitArray(double[] array, int arraysCount, int cols, int rows) {
        double[][][] newArray = new double[arraysCount][cols][rows];
        for (int i = 0; i < arraysCount; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < rows; k++) {
                    newArray[i][j][k] = array[i * cols * rows + j * rows + k];
                }
            }
        }
        return newArray;
    }*/

    public static double[][] sum(double[][] matr1, double[][] matr2) {
        for (int i = 0; i < matr1.length; i++) {
            for (int j = 0; j < matr1[i].length; j++) {
                matr1[i][j] += matr2[i][j];
            }
        }
        return matr1;
    }

    public static double[][] sub(double[][] matr1, double[][] matr2) {
        for (int i = 0; i < matr1.length; i++) {
            for (int j = 0; j < matr1[i].length; j++) {
                matr1[i][j] -= matr2[i][j];
            }
        }
        return matr1;
    }
}