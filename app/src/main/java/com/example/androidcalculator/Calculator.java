package com.example.androidcalculator;

import android.content.Context;

import android.widget.Button;
import com.example.androidcalculator.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class Calculator {
    private ActivityMainBinding binding;
    private Button              buttonZero;
    private Button              buttonOne;
    private Button              buttonTwo;
    private Button              buttonThree;
    private Button              buttonFour;
    private Button              buttonFive;
    private Button              buttonSix;
    private Button              buttonSeven;
    private Button              buttonEight;
    private Button              buttonNine;
    private Button              buttonComma;

    private static volatile Calculator    calculator;
    private final           String        notANumber;
    private final           String        multiplicationSign;
    private final           String        divisionSign;
    private final           String        additionSign;
    private final           String        subtractionSign;
    private                 String        operator;
    private                 String        secondOperator;
    private                 double        firstValue             = 0;
    private                 double        secondValue            = 0;
    private                 StringBuilder secondTextValue;
    private                 boolean       hasOperatorJustBeenSet = false;
    private                 boolean       isSecondValueSet       = false;
    private                 DecimalFormat decimalFormat;

    private Calculator(final Context context) {
        if (Calculator.calculator == null) {
            this.notANumber = context.getString(R.string.display_text_not_a_number);
            this.multiplicationSign = context.getString(R.string.button_multiply);
            this.divisionSign = context.getString(R.string.button_divide);
            this.additionSign = context.getString(R.string.button_add);
            this.subtractionSign = context.getString(R.string.button_subtract);
            this.decimalFormat = new DecimalFormat("#.###############");
            this.secondTextValue = new StringBuilder("");
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

    String computeCalculation() {
        if (this.operator == null || this.operator.equals(this.divisionSign) && this.secondValue == 0) {
            return notANumber;
        }

        return decimalFormat.format(calculate());
    }


    String getDisplayValue(String inputValue) {
        String newValue = "";
        if (this.isNumeric(inputValue)) {
            newValue = this.getValueByNumericInput(inputValue);
        } else {

        }
        return newValue;
    }

    private String getValueByNumericInput(String inputValue) {
        String newValue;
        if (this.hasOperatorJustBeenSet && !this.hasDecimal(inputValue)) {
            if (this.secondValue == 0) {
                if (this.hasDecimal(this.secondTextValue.toString())) {
                    newValue = this.secondTextValue.append(inputValue).toString();
                } else {
                    this.secondTextValue = new StringBuilder(inputValue);
                    newValue = this.secondTextValue.toString();
                }
            }
            this.secondTextValue = new StringBuilder(inputValue);
            newValue = this.secondTextValue.toString();
        } else if (this.hasDecimal(inputValue)) {
            newValue = this.secondTextValue.append(inputValue).toString();
        } else {
            newValue = this.secondTextValue.append(inputValue).toString();
        }

        if (this.secondValue == 0) {
            if (this.hasDecimal(this.secondTextValue.toString())) {
                newValue = this.secondTextValue.append(inputValue).toString();
            } else {
                this.secondTextValue = new StringBuilder(inputValue);
                newValue = this.secondTextValue.toString();
            }
        } else if (!this.hasDecimal(inputValue) && !this.hasDecimal(this.secondTextValue.toString())) {
            newValue = this.secondTextValue.append(inputValue).toString();
        } else {
            newValue = this.secondTextValue.toString();
        }
        this.secondValue = Double.parseDouble(newValue);

        return newValue;
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