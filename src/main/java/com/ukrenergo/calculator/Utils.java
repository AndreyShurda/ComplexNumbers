package com.ukrenergo.calculator;

public class Utils {
    public static final char PLUS = '+';
    public static final char MINUS = '-';
    public static final char DIVISION = '/';
    public static final char MULTIPLICATION = '*';
    public static final char LEFT_BRACKET = '(';
    public static final char RIGHT_BRACKET = ')';
    public static final char POW = '^';

    public static boolean isNumber(String string) {
        if (string == null)
            return false;
        return string.matches("^-?\\d+(\\.\\d+)?+i?$") || string.equals("i");
    }

    public static boolean isOperator(String string) {
        if (string == null || string.isEmpty())
            return false;
        return isOperator(string.charAt(0));
    }

    public static boolean isOperator(char ch) {
        if (("+-/*^()".indexOf(ch) != -1))
            return true;
        return false;
    }

    public static byte getPriority(String s) {
        return getPriority(s.charAt(0));
    }

    public static byte getPriority(char s) {
        switch (s) {
            case LEFT_BRACKET:
                return 0;
            case RIGHT_BRACKET:
                return 1;
            case PLUS:
                return 2;
            case MINUS:
                return 3;
            case MULTIPLICATION:
                return 4;
            case DIVISION:
                return 4;
            case POW:
                return 5;
            default:
                return 6;
        }
    }
}
