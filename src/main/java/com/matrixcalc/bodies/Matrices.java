package com.matrixcalc.bodies;

import java.math.BigDecimal;

public class Matrices {
    private BigDecimal[][][] numbers;
    private int[] params;
    private String operator;

    public BigDecimal[][][] getNumbers() {
        return numbers;
    }

    public String getOperator() {
        return operator;
    }

    public int[] getParams() {
        return params;
    }
}