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
            matr[0] = mulTwoMatrices(matr[0], matr[t]);
        }
        return matr[0];
    }

    public static double[][][] transpose(double[][]... matr) {
        double[][][] result = new double[matr.length][][];

        for (int i = 0; i < matr.length; i++) {
            result[i] = new double[matr[i][0].length][matr[i].length];

            for (int j = 0; j < matr[i].length; j++) {
                for (int l = 0; l < matr[i][0].length; l++) {
                    result[i][l][j] = matr[i][j][l];
                }
            }
        }
        return result;
    }

    public static double[][][] inverse(double[][]... matr) {
        double[][][] result = new double[matr.length][][];

        double[][][] clone = new double[matr.length][][];
        for (int i = 0; i < matr.length; i++) {
            clone[i] = new double[matr[i].length][matr[i].length];
            for (int j = 0; j < matr[i].length; j++) {
                System.arraycopy(matr[i][j], 0, clone[i][j], 0, matr[i].length);
            }
        }

        double[] det = det(clone);

        for (int i = 0; i < matr.length; i++) {
            result[i] = new double[matr[i].length][matr[i].length];
            for (int j = 0; j < matr[i].length; j++) {
                result[i][j][j] = 1;
            }
        }

        for (int i = 0; i < matr.length; i++) {
            if (det[i] == 0) {
                result[i] = null;
                continue;
            }

            matr[i] = removeZerosFromDiagonal(matr[i]);

            for (int j = 0; j < matr[i].length; j++) {
                double digit = matr[i][j][j];

                for (int l = 0; l < matr[i].length; l++) {
                    matr[i][j][l] /= digit;
                    result[i][j][l] /= digit;
                }

                for (int l = j + 1; l < matr[i].length; l++) {
                    digit = matr[i][l][j];

                    for (int k = 0; k < matr[i].length; k++) {
                        matr[i][l][k] -= matr[i][j][k] * digit;
                        result[i][l][k] -= result[i][j][k] * digit;
                    }
                }
            }

            for (int j = matr[i].length - 1; j >= 0; j--) {
                for (int l = j - 1; l >= 0; l--) {
                    double digit = matr[i][l][j];

                    for (int k = 0; k < matr[i].length; k++) {
                        matr[i][l][k] -= matr[i][j][k] * digit;
                        result[i][l][k] -= result[i][j][k] * digit;
                    }
                }
            }
        }
        return result;
    }

    public static double[] det(double[][]... matr) {
        double[] result = new double[matr.length];

        for (int i = 0; i < matr.length; i++) {
            double[][][] buf = new double[matr[i].length][][];
            buf[matr[i].length - 1] = new double[matr[i].length][matr[i].length];
            for (int j = 0; j < matr[i].length; j++) {
                System.arraycopy(matr[i][j], 0, buf[matr[i].length - 1][j], 0, matr[i].length);
            }

            for (int j = 0; j < matr[i].length - 1; j++) {
                for (int l = 0; l < matr[i].length - 1; l++) {
                    matr[i][j][l] = matr[i][j][l] * matr[i][j + 1][l + 1] - matr[i][j][l + 1] * matr[i][j + 1][l];
                }
            }

            for (int k = matr[i].length - 2; k > 0; k--) {
                buf[k] = new double[k + 1][k + 1];

                for (int j = 0; j < k; j++) {
                    for (int l = 0; l < k; l++) {
                        buf[k][j][l] = matr[i][j][l];
                        matr[i][j][l] = (matr[i][j][l] * matr[i][j + 1][l + 1] - matr[i][j][l + 1] * matr[i][j + 1][l]) / buf[k + 1][j + 1][l + 1];
                    }
                }
            }
            result[i] = matr[i][0][0];
        }
        return result;
    }

    private static double[][] mulTwoMatrices(double[][] matr1, double[][] matr2) {
        double[][] result = new double[matr1.length][matr2[0].length];

        for (int i = 0; i < matr1.length; i++) {
            for (int j = 0; j < matr2[0].length; j++) {
                double temp = 0;
                for (int l = 0; l < matr2.length; l++) {
                    temp += matr1[i][l] * matr2[l][j];
                }
                result[i][j] = temp;
            }
        }
        return result;
    }

    private static double[][] removeZerosFromDiagonal(double[][] matr) {
        for (int i = 0; i < matr.length; i++) {
            if (matr[i][i] == 0) {
                double[] movedLine = new double[matr.length];
                System.arraycopy(matr[i], 0, movedLine, 0, matr.length);

                for (int j = 0; j < matr.length; j++) {
                    if (matr[j][i] != 0 && matr[i][j] != 0) {
                        System.arraycopy(matr[j], 0, matr[i], 0, matr.length);
                        System.arraycopy(movedLine, 0, matr[j], 0, matr.length);
                        break;
                    }
                }
            }
        }
        return matr;
    }
}