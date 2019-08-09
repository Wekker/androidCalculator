package com.example.androidcalculator;

import android.content.Context;

import java.text.DecimalFormat;

public class Calculator {
    private       double        display            = 0;
    private       StringBuilder inputValue         = new StringBuilder();
    private final String        commaSign          = ",";
    private final String        multiplicationSign = "*";
    private final String        divisionSign       = "/";
    private final String        additionSign       = "+";
    private final String        subtractionSign    = "-";
    private final DecimalFormat decimalFormat      = new DecimalFormat("#.###############");

    Calculator() {
    }

    private double add(double firstValue, double secondValue) {
        return firstValue += secondValue;
    }

    private double subtract(double firstValue, double secondValue) {
        return firstValue -= secondValue;
    }

    private double multiply(double firstValue, double secondValue) {
        return firstValue -= secondValue;
    }

    private double divide(double firstValue, double secondValue) {
        return firstValue /= secondValue;
    }

    private boolean isNumeric(String value) {
        return value.matches("-?\\d+(,\\d+)?");
    }

    private boolean isAdvancedArithmeticSymbol(String value) {
        return value.matches("(.*[%].*)");
    }

    private boolean hasDecimal(String value) {
        return value.matches("(.*[,].*)");
    }
}