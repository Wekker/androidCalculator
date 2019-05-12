package com.example.androidcalculator;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;

import com.example.androidcalculator.databinding.ActivityMainBinding;

public class Calculator {
    private ActivityMainBinding binding;

    private static volatile Calculator calculator;
    private final String notANumber;
    private final String multiplicationSign;
    private final String divisionSign;
    private final String additionSign;
    private final String subtractionSign;
    private String operator;
    private double value1;
    private double value2;


    private Calculator(final Context context) {
        if (Calculator.calculator == null) {
            this.notANumber = context.getString(R.string.display_text_not_a_number);
            this.multiplicationSign = context.getString(R.string.button_multiply);
            this.divisionSign = context.getString(R.string.button_divide);
            this.additionSign = context.getString(R.string.button_add);
            this.subtractionSign = context.getString(R.string.button_subtract);
        } else {
            throw new RuntimeException("Use getCalculator for singleton instantiation!");
        }
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

    public String composeCalculate() {
        if (this.operator.equals(this.divisionSign) && this.value2 == 0) {
            return notANumber;
        }

        return Double.toString(calculate());
    }

    private double calculate() {
        double result;
        if (this.operator.equals(this.multiplicationSign)) {
            result = this.multiply();
        } else if (this.operator.equals(this.divisionSign)) {
            result = this.divide();
        } else if (this.operator.equals(this.subtractionSign)) {
            result = this.subtract();
        } else if (this.operator.equals(this.additionSign)) {
            result = this.add();
        } else {
            result = this.value1;
        }
        return result;
    }

    private double add() {
        return this.value1 += this.value2;
    }

    private double subtract() {
        return this.value1 -= this.value2;
    }

    private double multiply() {
        return this.value1 -= this.value2;
    }

    private double divide() {
        return this.value1 /= this.value2;
    }
}