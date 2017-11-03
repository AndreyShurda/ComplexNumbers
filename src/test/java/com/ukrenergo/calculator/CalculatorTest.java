package com.ukrenergo.calculator;

import com.ukrenergo.calculator.model.ComplexNumber;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CalculatorTest {
    Calculator calc;

    @Before
    public void setUp() throws Exception {
        calc = new Calculator();
    }

    @Test
    public void calculate() throws Exception {
        ComplexNumber calculate = calc.calculate();
        assertEquals(calculate,null);
    }

    @Test
    public void getPostfixExpression() throws Exception {
        String input = "((2+2.11i)+(2+2.5i))*2i-(8+2i)";
        String validString = calc.getValidString(input);
        String postfixExpression = calc.getPostfixExpression(validString);

        assertEquals(postfixExpression, "2 2.11i + 2 2.5i + + 2i * 8 2i + - ");
    }

    @Test
    public void getValidString() throws Exception {
        String input = "((2+2.11i)+(2+2.5i))*2i-(8+2i)";
        String validString = calc.getValidString(input);
        System.out.println(validString);
        assertEquals(validString, "( ( 2 + 2.11i ) + ( 2 + 2.5i ) ) * 2i - ( 8 + 2i ) ");
    }

    @Test
    public void countingComplex() throws Exception {
        String input = "((2+2.11i)+(2+2.5i))*2i-(8+2i)";
        String validString = calc.getValidString(input);
        String postfixExpression = calc.getPostfixExpression(validString);

        ComplexNumber expected = calc.countingComplex(postfixExpression);

        ComplexNumber actual = new ComplexNumber("-17.220+6i");

        assertEquals(expected, actual);
    }

}