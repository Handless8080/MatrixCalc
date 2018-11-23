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
            matr[0] = mulTwoMatrices(matr[t - 1], matr[t]);
        }
        return matr[0];
    }

    private static double[][] mulTwoMatrices(double[][] matr1, double[][] matr2) {
        double[][] result = new double[matr1.length][matr2[0].length];

        for (int i = 0; i < matr1.length; i++) {
            for (int j = 0; j < matr2.length; j++) {
                double temp = 0;
                for (int l = 0; l < matr2.length; l++) {
                    temp += matr1[i][l] * matr2[l][j];
                }
                result[i][j] = temp;
            }
        }
        return result;
    }
}