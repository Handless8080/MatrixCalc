package com.matrixcalc.functions;

public class MatrixFunctions {

    public static double[][] sum(double[][]... matr) {
        for (int t = 1; t < matr.length; t++) {
            
            for (int i = 0; i < matr[t].length; i++) {
                for (int j = 0; j < matr[t][i].length; j++) {
                    matr[0][i][j] += matr[t][i][j];
                }
            }
        }
        return matr[0];
    }

    public static double[][] sub(double[][]... matr) {
        for (int t = 1; t < matr.length; t++) {

            for (int i = 0; i < matr[t].length; i++) {
                for (int j = 0; j < matr[t][i].length; j++) {
                    matr[0][i][j] -= matr[t][i][j];
                }
            }
        }
        return matr[0];
    }

    public static double[][] mul(double[][]... matr) {
        for (int t = 1; t < matr.length; t++) {
            double[][] bufMatrix = matr[0].clone();

            for (int i = 0; i < matr[t].length; i++) {
                for (int j = 0; j < matr[t][i].length; j++) {

                    double temp = 0;
                    for (int l = 0; l < matr[t][i].length; i++) {
                        temp += bufMatrix[i][l] * matr[t][l][j];
                    }
                    matr[0][i][j] = temp;
                }
            }
        }
        return matr[0];
    }
}