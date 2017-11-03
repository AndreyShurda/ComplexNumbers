package com.ukrenergo.calculator.exception;

public class IncorrectDataType extends Exception {
    private String msg;

    public IncorrectDataType(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
