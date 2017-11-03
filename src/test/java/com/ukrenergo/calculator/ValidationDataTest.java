package com.ukrenergo.calculator;

import com.ukrenergo.calculator.exception.IncorrectDataType;
import com.ukrenergo.calculator.model.ComplexNumber;
import org.junit.Test;

import java.text.ParseException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ValidationDataTest {

    public String testCheck(String inputValid) {
        try {
            Calculator calculator = new Calculator(inputValid);
            calculator.calculate();
        } catch (IncorrectDataType e) {
            return e.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void parseComplexNumber() throws Exception {
        String expected = testCheck("text");

        assertEquals(expected, "Incorrect operator or number:\n" +
                "Correct numbers form are example: \n" +
                "1+2i \n" +
                "0.3i\n" +
                "Correct operators are '+,-,*,/'");
    }

    @Test
    public void testBeginRow() {
        String inputValid = "+((2+2.11i)+(2+2.5i))*2i-(8+2i)";
        String expected = testCheck(inputValid);
        assertEquals(expected, "The row must be begin with left bracket");
    }

    @Test
    public void testCountNumberOrOperators() {
        String inputValid = "((2+2.11i)++(2+2.5i))*2i-(8+2i)";
        String expected = testCheck(inputValid);
        assertEquals(expected, "Count of number or operators incorrect");
    }

    @Test
    public void testMustLeftBracket() {
        String inputValid = "(+2.2(2+2.11i)+(2+2.5i))/2i";
        String expected = testCheck(inputValid);
        assertEquals(expected, "After left bracket must be '(' or ')'");
    }

    @Test
    public void testOperatorAfterBracket() {
        String inputValid = "((2+2.11i)2+(2+2.5i))*2i-(8+2i)";
        String expected = testCheck(inputValid);
        assertEquals(expected, "After right bracket must be operator");
    }

    @Test
    public void testLastElementIsRightBracket() {
        String inputValid = "((2+2.11i)+(2+2.5i))*2i-(8+2i)+";
        String expected = testCheck(inputValid);
        assertEquals(expected, "Last element must be number or right bracket");
    }

    @Test
    public void testCloseRightBracket() {
        String inputValid = "((2+2.11i)+(2+2.5i)";
        String expected = testCheck(inputValid);
        assertEquals(expected, "You must close \")\" bracket");
    }

    @Test
    public void testCloseLeftBracket() {
        String inputValid = "(2+2.11i)+(2+2.5i))";
        String expected = testCheck(inputValid);
        assertEquals(expected, "You must add \"(\" bracket");
    }

}

