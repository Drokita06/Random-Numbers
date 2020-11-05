package com.drokita.model;

public enum OPERATION {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/");

    private final String sign;

    OPERATION(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}