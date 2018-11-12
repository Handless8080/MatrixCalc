package com.matrixcalc.bodies;

public class Matrices {
    private double[][][] numbers;
    private int cols;
    private int rows;
    private String operator;

    public double[][][] getNumbers() {
        return numbers;
    }

    public void setNumbers(double[][][] numbers) {
        this.numbers = numbers;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
