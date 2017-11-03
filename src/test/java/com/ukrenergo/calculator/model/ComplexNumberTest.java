package com.ukrenergo.calculator.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComplexNumberTest {
    private ComplexNumber a;
    private ComplexNumber b;

    @Before
    public void setUp() throws Exception {
        a = new ComplexNumber("1+0.25i");
        b = new ComplexNumber("-5-5.25i");
    }

    @Test
    public void add() throws Exception {
        ComplexNumber res = new ComplexNumber(a);
        res.add(b);
        assertEquals(res, new ComplexNumber("-4.0-5.0i"));
    }

    @Test
    public void sub() throws Exception {
        ComplexNumber res = new ComplexNumber(a);
        res.sub(b);
        assertEquals(res, new ComplexNumber("6+5.5i"));
    }

    @Test
    public void mul() throws Exception {
        ComplexNumber res = new ComplexNumber(a);
        res.mul(b);
        assertEquals(res, new ComplexNumber("-3.6875-6.5i"));
    }

    @Test
    public void div() throws Exception {
        ComplexNumber a = new ComplexNumber("8+2i");
        ComplexNumber b = new ComplexNumber("2+i");
        a.div(b);
        assertEquals(a, new ComplexNumber("3.6-0.8i"));
    }

    @Test(expected = NumberFormatException.class)
    public void parseComplexNumber() throws Exception {
        new ComplexNumber("1-2.5a");
    }

}