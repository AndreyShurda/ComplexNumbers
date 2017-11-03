package com.ukrenergo.calculator;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class UtilsTest {
    @Test
    public void isNumber() throws Exception {
        assertEquals(Utils.isNumber("12.01"), true);
        assertEquals(Utils.isNumber("12.01i"), true);
        assertEquals(Utils.isNumber("2.01a"), false);
        assertEquals(Utils.isNumber(""), false);
        assertEquals(Utils.isNumber("-2.2"), true);
    }

    @Test
    public void isOperator() throws Exception {
        assertEquals(Utils.isOperator("+"), true);
        assertEquals(Utils.isOperator('-'), true);
        assertEquals(Utils.isOperator("/"), true);
        assertEquals(Utils.isOperator('*'), true);
        assertEquals(Utils.isOperator('%'), false);
        assertEquals(Utils.isOperator(""), false);
    }

    @Test
    public void getPriority() throws Exception {
        assertEquals(Utils.getPriority('+'), 2);
        assertEquals(Utils.getPriority("-"), 3);
        Assert.assertEquals(Utils.getPriority(Utils.DIVISION), 4);
        assertEquals(Utils.getPriority("*"), 4);
        assertEquals(Utils.getPriority("%"), 6);
    }

}