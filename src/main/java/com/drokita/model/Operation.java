package com.drokita.model;

public enum Operation {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/");

    private final String sign;

    Operation(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}