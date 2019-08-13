package com.example.androidcalculator;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.text.DecimalFormat;

public class Calculator extends ViewModel {
    private MutableLiveData<String> displayPanelValueLive;
    private StringBuilder           displayPanelValue = new StringBuilder();

    private       double        display            = 0;
    private final String        commaSign          = ",";
    private final String        multiplicationSign = "*";
    private final String        divisionSign       = "/";
    private final String        additionSign       = "+";
    private final String        subtractionSign    = "-";
    private final DecimalFormat decimalFormat      = new DecimalFormat("#.###############");

    Calculator() {
    }

    MutableLiveData<String> getDisplayPanelValue() {
        if (displayPanelValueLive == null) {
            displayPanelValueLive = new MutableLiveData<String>();
        }
        return displayPanelValueLive;
    }

    void pressNumericButton(String value) {
        displayPanelValue.append(value);
        updateDisplay();
    }

    void pressBasicOperatorButton(String value) {
        displayPanelValue.append(value);
        updateDisplay();
    }

    private void updateDisplay() {
        displayPanelValueLive.setValue(displayPanelValue.toString());
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