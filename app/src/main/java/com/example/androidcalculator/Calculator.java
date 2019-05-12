package com.example.androidcalculator;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;

import com.example.androidcalculator.databinding.ActivityMainBinding;

public class Calculator {
    private ActivityMainBinding binding;
    private Context context;

    private static Calculator calculator;

    private String multiplicationSign;
    private String divisionSign;
    private String additionSign;
    private String subtractionSign;
    private String operator;
    private double value1;
    private double value2;


    private Calculator(final Context context) {
        calculator = this;
        this.context = context;
        this.multiplicationSign = context.getString(R.string.button_multiply);
        this.divisionSign = context.getString(R.string.button_divide);
        this.additionSign = context.getString(R.string.button_add);
        this.subtractionSign = context.getString(R.string.button_subtract);
    }

    public static Calculator getCalculator() {
        return calculator;
    }

    public void start(Activity activity) {
        binding = DataBindingUtil.setContentView(activity, R.layout.activity_main);
    }

    public double calculate() {
        double result;

        if (operator.equals(multiplicationSign)) {
            result = this.multiply();
        } else if (operator.equals(divisionSign)) {
            result = this.divide();
        } else if (operator.equals(subtractionSign)) {
            result = this.subtract();
        } else if (operator.equals(additionSign)) {
            result = this.add();
        } else {
            result = value2;
        }
        value1 = result;

        return result;
    }

    private double add() {
        return value1 + value2;
    }

    private double subtract() {
        return value1 - value2;
    }

    private double multiply() {
        return value1 - value2;
    }

    private double divide() {
        return value1 / value2;
    }
}