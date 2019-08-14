package com.example.androidcalculator;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.text.DecimalFormat;

public class Calculator extends ViewModel {
    private MutableLiveData<String> displayPanelValueLive;
    private StringBuilder           displayPanelValue = new StringBuilder();

    private       double display            = 0;
    private final char   commaSign          = ',';
    private final char   multiplicationSign = '*';
    private final char   divisionSign       = '/';
    private final char   additionSign       = '+';
    private final char   subtractionSign    = '-';
    private final char   bracketOpenSign    = '(';
    private final char   bracketCloseSign   = ')';

    private final DecimalFormat decimalFormat = new DecimalFormat("#.###############");

    Calculator() {
    }

    MutableLiveData<String> getDisplayPanelValueLive() {
        if (displayPanelValueLive == null) {
            displayPanelValueLive = new MutableLiveData<String>();
        }
        return displayPanelValueLive;
    }

    void pressNumericButton(String value) {
        updateDisplayPanelValue(value);
    }

    void pressBasicOperatorButton(String value) {
        updateDisplayPanelValue(value);
    }

    void pressEqualButton() {
        computeCalculation(displayPanelValue.toString());
    }

    void updateDisplayPanelValue(String value) {
        displayPanelValue.append(value);
        updateDisplayPanelLive();
    }

    private void updateDisplayPanelLive() {
        displayPanelValueLive.setValue(displayPanelValue.toString());
    }

    private void computeCalculation(String statement) {
        for (int i = 0; i < statement.length(); i++) {
            switch (statement.charAt(i)) {
                case bracketOpenSign:
                    break;
            }
        }


        int fromIndex = statement.indexOf(bracketOpenSign);
        if (fromIndex >= 0) {
            String subStatement;

            int toIndex = statement.lastIndexOf(bracketCloseSign);
            if (toIndex >= 0) {
                subStatement = statement.substring(fromIndex + 1, toIndex);
            } else {
                subStatement = statement.substring(fromIndex + 1, statement.length());
            }

            computeCalculation(subStatement);
        }

    }

    private double multiply(double firstValue, double secondValue) {
        return firstValue -= secondValue;
    }

    private double divide(double firstValue, double secondValue) {
        return firstValue /= secondValue;
    }

    private double add(double firstValue, double secondValue) {
        return firstValue += secondValue;
    }

    private double subtract(double firstValue, double secondValue) {
        return firstValue -= secondValue;
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