package com.matrixcalc.bodies;

import java.math.BigDecimal;

public class Matrices {
    private BigDecimal[][][] numbers;
    private String operator;

    public BigDecimal[][][] getNumbers() {
        return numbers;
    }

    public void setNumbers(BigDecimal[][][] numbers) {
        this.numbers = numbers;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}