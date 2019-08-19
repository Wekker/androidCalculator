package com.example.androidcalculator;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class Calculator extends ViewModel {
    private MutableLiveData<String> displayPanelLive;
    private StringBuilder           displayPanelValue = new StringBuilder();

    private final static String defaultDisplayValue = "0";
    private final static String commaSign           = ",";
    private final static char   multiplicationSign  = '*';
    private final static char   divisionSign        = '/';
    private final static char   additionSign        = '+';
    private final static char   subtractionSign     = '-';
    private final static char   bracketOpenSign     = '(';
    private final static char   bracketCloseSign    = ')';

    private boolean isDisplayReset = true;
    private boolean hasBracket     = false;
    private boolean isMathComputed = false;

//    private final DecimalFormat decimalFormat = new DecimalFormat("#.###############");

    Calculator() {
        updateDisplayPanelValue(defaultDisplayValue);
    }

    MutableLiveData<String> getDisplayPanelLive() {
        if (displayPanelLive == null) {
            displayPanelLive = new MutableLiveData<>();
        }

        updateDisplayPanelLive();

        return displayPanelLive;
    }

    void pressNumericButton(String value) {
        composeInput(value);
    }

    void pressBasicOperatorButton(String value) {
        composeInput(value);
    }

    void pressClearButton() {
        resetDisplay();
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
        isMathComputed = false;

        updateDisplayPanelValue(validate(inputValue, commaSign));
        updateDisplayPanelLive();
    }

    private void resetDisplay() {
        isDisplayReset = true;
        updateDisplayPanelValue(defaultDisplayValue);
        updateDisplayPanelLive();
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
                    hasBracket = true;
                    break;
                case bracketCloseSign:
                    hasBracket = false;

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

    private void updateDisplayPanelValue(String value) {
        displayPanelValue.replace(0, displayPanelValue.length(), value);
    }

    private void updateDisplayPanelLive() {
        displayPanelLive.setValue(displayPanelValue.toString());
    }

    private static double multiply(double firstValue, double secondValue) {
        return firstValue * secondValue;
    }

    private static double divide(double firstValue, double secondValue) {
        return firstValue / secondValue;
    }

    private static double add(double firstValue, double secondValue) {
        return firstValue + secondValue;
    }

    private static double subtract(double firstValue, double secondValue) {
        return firstValue - secondValue;
    }

    private class ComputedData {
        double value;
        int    index;

        ComputedData(double value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    private String validate(String value, String commaValue) {
        return removeZeroDecimal(removeStartingZeroWithDigit(value), commaValue);
    }

    private String removeStartingZeroWithDigit(String value) {
        if (value.length() >= 2 && value.charAt(0) == '0') {
            value = value.substring(1);
        }
        return value;
    }

    private String removeZeroDecimal(String value, String commaValue) {
        String[] stringArray = value.split(".");
        if (stringArray.length > 2) {
            // Throw error:
        } else if (stringArray.length == 2) {
            value = stringArray[0] + commaValue + stringArray[1];
        }
        return value;
    }
}