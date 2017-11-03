package com.ukrenergo.calculator.model;

import java.util.StringTokenizer;

public class ComplexNumber {

    private double Re = 0;
    private double Im = 0;


    public ComplexNumber(String value) throws java.text.ParseException {
        String val = removeSpaces(value);
        parseComplexNumber(val);
    }

    public ComplexNumber(ComplexNumber value) {
        Re = value.getRe();
        Im = value.getIm();
    }

    public double getRe() {
        return Re;
    }

    public double getIm() {
        return Im;
    }

    public String toString() {
        String retValue;
        if (Re == 0)
            retValue = Im + "i";
        else if (Im > 0)
            retValue = Re + "+" + Im + "i";
        else
            retValue = Re + "" + Im + "i";
        if (Im == 0)
            retValue = "" + Re;
        return retValue;
    }


    private String removeSpaces(String str) {
        return str.replace(" ", "");
    }

    private void parseComplexNumber(String str) throws java.lang.NumberFormatException {
        StringTokenizer st = new StringTokenizer(str, "+-", true);
        String previousToken = "";
        String currentToken = "";
        String numb = "";
        while (st.hasMoreTokens()) {
            currentToken = st.nextToken();
            if (previousToken.equals("-"))
                numb = previousToken + currentToken;
            else
                numb = currentToken;
            if (numb.equals("+") == false && numb.equals("-") == false) {
                if (numb.indexOf('i') == -1)
                    Re = Double.valueOf(numb);
                else {
                    numb = numb.replace('i', ' ');
//                    numb = numb.replace(',', '.');
                    if (numb.equals(" "))
                        numb = "1";
                    if (numb.equals("- "))
                        numb = "-1";
                    Im = Double.valueOf(numb);
                }
            }
            previousToken = currentToken;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplexNumber that = (ComplexNumber) o;

        if (Double.compare(that.Re, Re) != 0) return false;
        return Double.compare(that.Im, Im) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(Re);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(Im);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public void add(ComplexNumber value) {
        Re += value.Re;
        Im += value.Im;
    }

    public void sub(ComplexNumber value) {
        Re -= value.Re;
        Im -= value.Im;
    }

    public void mul(ComplexNumber value) {
        double tempRe = Re * value.Re - Im * value.Im;
        double tempIm = Re * value.Im + Im * value.Re;
        Re = tempRe;
        Im = tempIm;
    }

    public void div(ComplexNumber value) {
        double denominator = value.Re * value.Re + value.Im * value.Im;
        if (denominator == 0) {
            Re = Im = 0;
            return;
        }
        double tempRe, tempIm;
        tempRe = (Re * value.Re + Im * value.Im) / denominator;
        tempIm = (Im * value.Re - Re * value.Im) / denominator;
        Re = tempRe;
        Im = tempIm;
    }
}
