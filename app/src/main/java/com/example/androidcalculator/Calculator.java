package com.example.androidcalculator;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class Calculator extends ViewModel {
    private MutableLiveData<String> displayPanelValueLive;
    private StringBuilder           displayPanelValue = new StringBuilder();

    private final static String defaultDisplayValue = "0";
    private final static char   multiplicationSign  = '*';
    private final static char   divisionSign        = '/';
    private final static char   additionSign        = '+';
    private final static char   subtractionSign     = '-';
    private final static char   bracketOpenSign     = '(';
    private final static char   bracketCloseSign    = ')';

    private static boolean isMathComputed = false;

//    private final DecimalFormat decimalFormat = new DecimalFormat("#.###############");

    Calculator() {
    }

    MutableLiveData<String> getDisplayPanelValueLive() {
        if (displayPanelValueLive == null) {
            displayPanelValueLive = new MutableLiveData<>();
        }
        return displayPanelValueLive;
    }

    void pressNumericButton(String value) {
        composeInput(value);
    }

    void pressBasicOperatorButton(String value) {
        composeInput(value);
    }

    void pressClearButton() {
        displayPanelValue.replace(0, displayPanelValue.length(), defaultDisplayValue);
        updateDisplayPanelLive();
    }

    void pressEqualOperatorButton() {
        String computedValue = String.valueOf(computeCalculation(displayPanelValue.toString().toCharArray(), 0).value);
        displayPanelValue.replace(0, displayPanelValue.length(), computedValue);
        updateDisplayPanelLive();
        isMathComputed = true;
    }

    private void composeInput(String inputValue) {
        if (isMathComputed) {
            displayPanelValue.replace(0, displayPanelValue.length(), inputValue);
        } else {
            displayPanelValue.append(inputValue);
        }

        validate(displayPanelValue.toString());
        updateDisplayPanelLive();
    }

    private void updateDisplayPanelLive() {
        displayPanelValueLive.setValue(displayPanelValue.toString());
    }

    private ComputedData computeCalculation(char[] math, int nextIndex) {
        String  currentNumber           = "";
        boolean isComputedValueComputed = false;
        double  computedValue           = 0;

        for (int i = nextIndex; i < math.length; i++) {
            ComputedData computedData;
            switch (math[i]) {
                case multiplicationSign:
                    computedData = computeCalculation(math, i + 1);
                    nextIndex = computedData.index;
                    isComputedValueComputed = true;
                    computedValue = multiply(Double.parseDouble(currentNumber), computedData.value);
                    break;
                case divisionSign:
                    computedData = computeCalculation(math, i + 1);
                    nextIndex = computedData.index;
                    isComputedValueComputed = true;
                    computedValue = divide(Double.parseDouble(currentNumber), computedData.value);
                    break;
                case additionSign:
                    computedData = computeCalculation(math, i + 1);
                    nextIndex = computedData.index;
                    isComputedValueComputed = true;
                    computedValue = add(Double.parseDouble(currentNumber), computedData.value);
                    break;
                case subtractionSign:
                    computedData = computeCalculation(math, i + 1);
                    nextIndex = computedData.index;
                    isComputedValueComputed = true;
                    computedValue = subtract(Double.parseDouble(currentNumber), computedData.value);
                    break;
                case bracketOpenSign:
                    break;
                case bracketCloseSign:
                    break;
                default:
                    currentNumber += math[i];
            }
        }

        if (!isComputedValueComputed) {
            computedValue = Double.parseDouble(currentNumber);
        }

        return new ComputedData(computedValue, (nextIndex + 1));
    }

    static class ComputedData {
        double value;
        int    index;

        ComputedData(double value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    private double multiply(double firstValue, double secondValue) {
        return firstValue * secondValue;
    }

    private double divide(double firstValue, double secondValue) {
        return firstValue / secondValue;
    }

    private double add(double firstValue, double secondValue) {
        return firstValue + secondValue;
    }

    private double subtract(double firstValue, double secondValue) {
        return firstValue - secondValue;
    }

    private void validate(String value) {
        displayPanelValue.replace(0, displayPanelValue.length(), removeStartingZeroWithDigit(value));
    }

    private String removeStartingZeroWithDigit(String value) {
        if (value.length() >= 2 && value.charAt(0) == 0) {
            value = value.substring(1);
        }

        return value;
    }
}