package com.ukrenergo;


import com.ukrenergo.calculator.Calculator;
import com.ukrenergo.calculator.exception.IncorrectDataType;
import com.ukrenergo.calculator.model.ComplexNumber;

import java.text.ParseException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws ParseException, IncorrectDataType {
        runProgram();
    }

    private static void runProgram() {
        Scanner scanner = new Scanner(System.in);
        String next = "";
        System.out.println("If you want to close program enter 'exit'");
        do {
            try {
                Thread.sleep(1000);

                System.out.print("Enter calculate string :");
                next = scanner.nextLine();
                if (!next.equals("exit")) {
                    getResult(next);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!next.equals("exit"));
    }

    private static void getResult(String input) {
        Calculator calculator = new Calculator(input);
        try {
            ComplexNumber calculate = calculator.calculate();
            System.out.println("Result is: " + calculate+"\n");
        } catch (IncorrectDataType e) {
            System.err.println(e);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
