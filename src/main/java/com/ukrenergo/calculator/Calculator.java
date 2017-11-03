package com.ukrenergo.calculator;

import com.ukrenergo.calculator.exception.IncorrectDataType;
import com.ukrenergo.calculator.model.ComplexNumber;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Calculator {
    private String input;

    public Calculator() {
    }

    public Calculator(String input) {
        this.input = input;
    }

    public ComplexNumber calculate() throws IncorrectDataType, ParseException {
        if (input != null) {
            String validString = getValidString(input);
            ValidationData.check(Arrays.asList(validString.split(" ")));

            String postfixExpression = getPostfixExpression(validString);

            return countingComplex(postfixExpression);
        }
        return null;
    }

    public ComplexNumber calculate(String input) throws ParseException, IncorrectDataType {
        this.input = input;
        return calculate();
    }

    public String getPostfixExpression(String input) {
        String output = "";
        String[] values = input.split(" ");
        Deque<String> stack = new LinkedList<>();

        for (String value : values) {
            if (Utils.isNumber(value)) {
                output += value + " ";
            } else if (Utils.isOperator(value)) {
                if (value.equals("(")) {
                    stack.push(value);
                } else if (value.equals(")")) {
                    String s = stack.pop();

                    while (!s.equals("(")) {
                        output += s + " ";
                        s = stack.pop();
                    }
                } else {
                    if (stack.size() > 0) {
                        if (Utils.getPriority(value) <= Utils.getPriority(stack.peek())) {
                            output += stack.pop() + " ";
                        }
                    }
                    stack.push(value);

                }
            }
        }
        while (stack.size() > 0)
            output += stack.pop() + " ";

        return output;
    }

    public String getValidString(String input) {
        String outputResult = "";
        input = input.replace(" ", "");
        char[] chars = input.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i]) || 'i' == chars[i]) {
                while (!Utils.isOperator(chars[i])) {
                    char ch = chars[i];

                    outputResult += ch;
                    i++;
                    if (i == chars.length)
                        break;
                }
                outputResult += " ";
                i--;
                continue;
            }
            outputResult += String.valueOf(chars[i]) + ' ';
        }

        return outputResult;
    }

    public ComplexNumber countingComplex(String st) throws ParseException {
        ComplexNumber result;
        String[] values = st.split(" ");
        Deque<String> stack = new LinkedList<>();
        String temp;
        for (int i = 0; i < values.length; i++) {
            temp = values[i];
            if (Utils.isNumber(temp))
                stack.push(temp);
            if (Utils.isOperator(temp)) {
                ComplexNumber y = new ComplexNumber(stack.pop());
                ComplexNumber x = new ComplexNumber(stack.pop());
                ComplexNumber res = solveOperation(x, y, temp.charAt(0));
                stack.push(res.toString());
            }
        }
        result = new ComplexNumber(stack.pop());
        return result;
    }

    private ComplexNumber solveOperation(ComplexNumber x, ComplexNumber y, int operatorType) throws ParseException {
        ComplexNumber res;
        res = x;
        switch (operatorType) {
            case Utils.PLUS:
                res.add(y);
                break;
            case Utils.MINUS:
                res.sub(y);
                break;
            case Utils.MULTIPLICATION:
                res.mul(y);
                break;
            case Utils.DIVISION:
                res.div(y);
                break;
            default:
                throw new AssertionError("Unknown operations " + res);
        }
        return res;
    }

}