package com.example.androidcalculator;

import android.content.Context;
import android.util.Log;

import com.example.androidcalculator.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class Calculator {
    private ActivityMainBinding binding;

    private static volatile Calculator calculator;
    private final String notANumber;
    private final String multiplicationSign;
    private final String divisionSign;
    private final String additionSign;
    private final String subtractionSign;
    private String operator;
    private double firstValue = 0;
    private double secondValue = 0;
    private boolean isSecondValueSet = false;
    private DecimalFormat decimalFormat;

    private Calculator(final Context context) {
        if (Calculator.calculator == null) {
            this.notANumber = context.getString(R.string.display_text_not_a_number);
            this.multiplicationSign = context.getString(R.string.button_multiply);
            this.divisionSign = context.getString(R.string.button_divide);
            this.additionSign = context.getString(R.string.button_add);
            this.subtractionSign = context.getString(R.string.button_subtract);
            this.decimalFormat = new DecimalFormat("#.###############");
        } else {
            throw new RuntimeException("Use getCalculator for singleton instantiation!");
        }
    }

    static Calculator getCalculator() {
        return Calculator.calculator;
    }

    static Calculator getCalculator(Context context) {
        if (Calculator.calculator == null) {
            synchronized (Calculator.class) {
                if (Calculator.calculator == null) {
                    Calculator.calculator = new Calculator(context);
                }
            }
        }
        return Calculator.calculator;
    }

    protected Calculator readResolve() {
        return Calculator.calculator;
    }

    String composeCalculation() {
        if (this.operator == null || this.operator.equals(this.divisionSign) && this.secondValue == 0) {
            return notANumber;
        }

        return decimalFormat.format(calculate());
    }

    void setValue(String value) {
        if (this.operator == null) {
            this.setFirstValue(value);
        } else {
            this.setSecondValue(value);
            this.isSecondValueSet = true;
        }
    }

    private void setFirstValue(String value) {
        try {
            this.firstValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            Log.e("NumberFormatException", e.getMessage());
        }
    }

    private void setSecondValue(String value) {
        try {
            this.secondValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            Log.e("NumberFormatException", e.getMessage());
        }
    }

    void setOperator(String operator) {
        this.operator = operator;
    }

    boolean isOperatorSet() {
        return this.operator != null;
    }

    private double calculate() {
        double result;
        if (!isSecondValueSet) {
            return this.firstValue;
        }

        if (this.operator.equals(this.multiplicationSign)) {
            result = this.multiply();
        } else if (this.operator.equals(this.divisionSign)) {
            result = this.divide();
        } else if (this.operator.equals(this.subtractionSign)) {
            result = this.subtract();
        } else if (this.operator.equals(this.additionSign)) {
            result = this.add();
        } else {
            result = this.secondValue;
        }

        return result;
    }

    private double add() {
        return this.firstValue += this.secondValue;
    }

    private double subtract() {
        return this.firstValue -= this.secondValue;
    }

    private double multiply() {
        return this.firstValue -= this.secondValue;
    }

    private double divide() {
        return this.firstValue /= this.secondValue;
    }
}