package com.matrixcalc.functions;

import java.math.BigDecimal;
import java.util.Stack;

public class MatrixFunctions {
    private static final int SCALE_FOR_CALC = 8;
    private static final int SCALE_FOR_ANSWER = 4;
    
    public static BigDecimal[][] sum(BigDecimal[][]... matr) {
        for (int t = 1; t < matr.length; t++) {

            for (int i = 0; i < matr[t].length; i++) {
                for (int j = 0; j < matr[t][i].length; j++) {
                    matr[0][i][j] = matr[0][i][j].add(matr[t][i][j]);
                }
            }
        }
        return matr[0];
    }

    public static BigDecimal[][] sub(BigDecimal[][]... matr) {
        for (int t = 1; t < matr.length; t++) {

            for (int i = 0; i < matr[t].length; i++) {
                for (int j = 0; j < matr[t][i].length; j++) {
                    matr[0][i][j] = matr[0][i][j].subtract(matr[t][i][j]);
                }
            }
        }
        return matr[0];
    }

    public static BigDecimal[][] mul(BigDecimal[][]... matr) {
        for (int t = 1; t < matr.length; t++) {
            matr[0] = mulTwoMatrices(matr[0], matr[t]);
        }

        for (int i = 0; i < matr[0].length; i++) {
            for (int j = 0; j < matr[0][0].length; j++) {
                matr[0][i][j] = matr[0][i][j].setScale(SCALE_FOR_ANSWER, BigDecimal.ROUND_HALF_UP);
            }
        }
        return matr[0];
    }

    public static BigDecimal[][][] transpose(BigDecimal[][]... matr) {
        BigDecimal[][][] result = new BigDecimal[matr.length][][];

        for (int i = 0; i < matr.length; i++) {
            result[i] = new BigDecimal[matr[i][0].length][matr[i].length];

            for (int j = 0; j < matr[i].length; j++) {
                for (int l = 0; l < matr[i][0].length; l++) {
                    result[i][l][j] = matr[i][j][l];
                }
            }
        }
        return result;
    }

    public static BigDecimal[][][] inverse(BigDecimal[][]... matr) {
        BigDecimal[][][] result = new BigDecimal[matr.length][][];
        BigDecimal[][][] finalResult = new BigDecimal[matr.length][][];
        BigDecimal[][][] clone = new BigDecimal[matr.length][][];

        BigDecimal[] det = det(clone);
        for (int i = 0; i < matr.length; i++) {
            clone[i] = cloneArray(matr[i]);
            result[i] = getDiagonalMatrix(matr[i].length);
        }

        for (int i = 0; i < matr.length; i++) {
            if (det[i].signum() == 0) {
                result[i] = null;
                continue;
            }

            int[] positions = new int[matr[i].length];
            removeZerosFromDiagonal(matr[i], positions);

            for (int j = 0; j < matr[i].length; j++) {
                BigDecimal digit = matr[i][j][j];

                for (int l = 0; l < matr[i].length; l++) {
                    matr[i][j][l] = matr[i][j][l].divide(digit, SCALE_FOR_CALC, BigDecimal.ROUND_HALF_UP);
                    result[i][j][l] = result[i][j][l].divide(digit, SCALE_FOR_CALC, BigDecimal.ROUND_HALF_UP);
                }

                for (int l = j + 1; l < matr[i].length; l++) {
                    digit = matr[i][l][j];

                    for (int k = 0; k < matr[i].length; k++) {
                        matr[i][l][k] = matr[i][l][k].subtract(matr[i][j][k].multiply(digit));
                        result[i][l][k] = result[i][l][k].subtract(result[i][j][k].multiply(digit));
                    }
                }
            }

            for (int j = matr[i].length - 1; j >= 0; j--) {
                for (int l = j - 1; l >= 0; l--) {
                    BigDecimal digit = matr[i][l][j];

                    for (int k = 0; k < matr[i].length; k++) {
                        matr[i][l][k] = matr[i][l][k].subtract(matr[i][j][k].multiply(digit));
                        result[i][l][k] = result[i][l][k].subtract(result[i][j][k].multiply(digit));
                    }
                }
            }

            finalResult[i] = new BigDecimal[matr[i].length][matr[i].length];
            for (int j = 0; j < matr[i].length; j++) {
                for (int l = 0; l < matr[i].length; l++) {
                    finalResult[i][j][positions[l]] = result[i][j][l].setScale(SCALE_FOR_ANSWER, BigDecimal.ROUND_HALF_UP);
                }
            }
        }
        return finalResult;
    }

    public static BigDecimal[][][] pow(int[] params, BigDecimal[][]... matr) {
        for (int i = 0; i < matr.length; i++) {
            int count = 0;
            Stack<Boolean> mulParams = new Stack<>();
            for (int j = params[i]; j > 1; j /= 2) {
                count++;
                mulParams.push((j & 1) != 0);
            }

            BigDecimal[][] start = cloneArray(matr[i]);
            for (int j = count - 1; j >= 0; j--) {
                matr[i] = mulTwoMatrices(matr[i], matr[i]);
                if (mulParams.pop()) {
                    matr[i] = mulTwoMatrices(matr[i], start);
                }
            }
        }
        return matr;
    }

    public static BigDecimal[] det(BigDecimal[][]... matr) {
        BigDecimal[] result = new BigDecimal[matr.length];

        for (int i = 0; i < matr.length; i++) {
            Gauss(matr[i]);
            result[i] = Gauss(matr[i]) ? new BigDecimal(-1) : new BigDecimal(1);

            for (int j = 0; j < matr[i].length; j++) {
                result[i] = result[i].multiply(matr[i][j][j]);
            }
            result[i] = result[i].setScale(SCALE_FOR_ANSWER, BigDecimal.ROUND_HALF_UP);
        }
        return result;
    }

    public static BigDecimal[] rank(BigDecimal[][]... matr) {
        BigDecimal[] ranks = new BigDecimal[matr.length];
        BigDecimal inc = new BigDecimal(1);

        for (int i = 0; i < matr.length; i++) {
            Gauss(matr[i]);
            ranks[i] = new BigDecimal(0);

            for (int j = 0; j < matr[i].length; j++) {
                for (int l = 0; l < matr[i][0].length; l++) {

                    if (matr[i][j][l].signum() != 0) {
                        ranks[i] = ranks[i].add(inc);
                        break;
                    }
                }
            }
        }
        return ranks;
    }

    private static boolean Gauss(BigDecimal[][] matr) {
        int size = matr.length > matr[0].length ? matr[0].length : matr.length;
        boolean sign = false;

        for (int i = 0; i < size - 1; i++) {

            if (matr[i][i].signum() == 0) {
                for (int l = i + 1; l < matr.length; l++) {

                    if (matr[l][i].signum() != 0) {
                        BigDecimal[] buf = new BigDecimal[matr[0].length];

                        System.arraycopy(matr[l], 0, buf, 0, matr[0].length);
                        System.arraycopy(matr[i], 0, matr[l], 0, matr[0].length);
                        System.arraycopy(buf, 0, matr[i], 0, matr[0].length);
                        sign = !sign;
                    }
                }
            }

            if (matr[i][i].signum() == 0) {
                continue;
            }

            for (int j = i + 1; j < matr.length; j++) {
                BigDecimal mul = matr[j][i].divide(matr[i][i], SCALE_FOR_CALC, BigDecimal.ROUND_HALF_UP);
                matr[j][i] = new BigDecimal(0);

                for (int l = i + 1; l < matr[0].length; l++) {
                    BigDecimal sub = matr[i][l].multiply(mul);
                    matr[j][l] = matr[j][l].subtract(sub);
                }
            }
        }
        return sign;
    }

    private static BigDecimal[][] mulTwoMatrices(BigDecimal[][] matr1, BigDecimal[][] matr2) {
        BigDecimal[][] result = new BigDecimal[matr1.length][matr2[0].length];

        for (int i = 0; i < matr1.length; i++) {
            for (int j = 0; j < matr2[0].length; j++) {
                BigDecimal temp = new BigDecimal(0);
                for (int l = 0; l < matr2.length; l++) {
                    temp = temp.add(matr1[i][l].multiply(matr2[l][j]));
                }
                result[i][j] = temp;
            }
        }
        return result;
    }

    private static void removeZerosFromDiagonal(BigDecimal[][] matr, int[] positions) {
        for (int i = 0; i < matr.length; i++) {
            positions[i] = i;
        }

        for (int i = 0; i < matr.length; i++) {
            if (matr[i][i].signum() == 0) {
                BigDecimal[] movedLine = new BigDecimal[matr.length];
                System.arraycopy(matr[i], 0, movedLine, 0, matr.length);

                for (int j = 0; j < matr.length; j++) {
                    if (matr[j][i].signum() != 0 && matr[i][j].signum() != 0) {
                        System.arraycopy(matr[j], 0, matr[i], 0, matr.length);
                        System.arraycopy(movedLine, 0, matr[j], 0, matr.length);

                        int buf = positions[i];
                        positions[i] = positions[j];
                        positions[j] = buf;
                        break;
                    }
                }
            }
        }
    }

    private static BigDecimal[][] cloneArray(BigDecimal[][] array) {
        BigDecimal[][] clone = new BigDecimal[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, clone[i], 0, array.length);
        }
        return clone;
    }

    private static BigDecimal[][] getDiagonalMatrix(int dimension) {
        BigDecimal[][] result = new BigDecimal[dimension][dimension];

        for (int j = 0; j < dimension; j++) {
            for (int l = 0; l < dimension; l++) {
                result[j][l] = new BigDecimal(0);
            }
            result[j][j] = new BigDecimal(1);
        }
        return result;
    }
}