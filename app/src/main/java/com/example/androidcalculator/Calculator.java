package com.example.androidcalculator;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.util.Pair;

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
        computeCalculation(displayPanelValue.toString().toCharArray());
    }

    void updateDisplayPanelValue(String value) {
        displayPanelValue.append(value);
        updateDisplayPanelLive();
    }

    private void updateDisplayPanelLive() {
        displayPanelValueLive.setValue(displayPanelValue.toString());
    }

    //    11*(5+6)-3
    private Object computeCalculation(char[] math, int index) {
        int    nextIndex     = 0;
        double currentNumber = 0;
        String firstNumber         = "";
        for (int i = index; i < math.length; i = nextIndex) {
            switch (math[i]) {
                case multiplicationSign:
                    nextIndex = computeCalculation(math, i).;
                    currentNumber = multiply(currentNumber, Double.parseDouble(firstNumber));
                    break;
                case divisionSign:
                    break;
                case additionSign:
                    break;
                case subtractionSign:
                    break;
                case bracketOpenSign:
                    break;
                case bracketCloseSign:
                    break;
                default:
                    firstNumber += math[i];
            }
        }
    }

//    private void computeBracketCalculation(String math) {
//        int fromIndex = math.indexOf(bracketOpenSign);
//        if (fromIndex >= 0) {
//            String subMath;
//
//            int toIndex = math.lastIndexOf(bracketCloseSign);
//            if (toIndex >= 0) {
//                subMath = math.substring(fromIndex + 1, toIndex);
//            } else {
//                subMath = math.substring(fromIndex + 1, math.length());
//            }
//
////            computeCalculation(subMath);
//        }
//    }

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