package com.ukrenergo.calculator;


import com.ukrenergo.calculator.exception.IncorrectDataType;

import java.util.Iterator;
import java.util.List;

import static com.ukrenergo.calculator.Utils.*;

public class ValidationData {

    public static void check(List<String> elements) throws IncorrectDataType {
        if (elements == null) {
            return;
        }
        int rightBracketsCount = 0;
        int leftBracketsCount = 0;
        int operatorsCount = 0;
        int numbersCount = 0;
        String curElem = null;
        String prevElem = null;
        boolean firstElem = true;

        Iterator<String> iterator = elements.iterator();
        if (iterator == null)
            return;

        while (iterator.hasNext()) {
            curElem = iterator.next();

            if (!isOperator(curElem) && !isNumber(curElem)){
                throw new IncorrectDataType("Incorrect operator or number:\n" +
                        "Correct numbers form are example: \n1+2i \n0.3i\n" +
                        "Correct operators are '+,-,*,/'");
            }

            if (firstElem) {
                if (isOperator(curElem) && (curElem.charAt(0) != LEFT_BRACKET))
                    throw new IncorrectDataType("The row must be begin with left bracket");
                firstElem = false;
            }

            if (isOperator(curElem)) {
                numbersCount = 0;
                if (curElem.charAt(0) == RIGHT_BRACKET)
                    rightBracketsCount++;
                else if (curElem.charAt(0) == LEFT_BRACKET)
                    leftBracketsCount++;
                else
                    operatorsCount++;
            }
            if (isNumber(curElem)) {
                numbersCount++;
                operatorsCount = 0;
            }

            if (numbersCount > 1 || operatorsCount > 1)
                throw new IncorrectDataType("Count of number or operators incorrect");

            if (prevElem != null) {
                if (isOperator(prevElem) && (prevElem.charAt(0) == LEFT_BRACKET)) {
                    if (isOperator(curElem))
                        if ((curElem.charAt(0) != LEFT_BRACKET) && curElem.charAt(0) != RIGHT_BRACKET)
                            throw new IncorrectDataType("After left bracket must be '(' or ')'");
                }

                if (isOperator(prevElem) && (prevElem.charAt(0) == RIGHT_BRACKET)) {
                    if (isNumber(curElem) || curElem.charAt(0) == LEFT_BRACKET)
                        throw new IncorrectDataType("After right bracket must be operator");
                }
            }
            prevElem = curElem;
        }

        if (isOperator(curElem) && (curElem.charAt(0) != RIGHT_BRACKET))
            throw new IncorrectDataType("Last element must be number or right bracket");

        if (leftBracketsCount > rightBracketsCount)
            throw new IncorrectDataType("You must close \")\" bracket");
        if (leftBracketsCount < rightBracketsCount)
            throw new IncorrectDataType("You must add \"(\" bracket");

    }
}
