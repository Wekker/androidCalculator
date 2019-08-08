package com.example.androidcalculator;

import android.content.Context;

import android.databinding.ViewDataBinding;
import android.renderscript.ScriptGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.androidcalculator.databinding.ActivityMainBinding;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Stack;

public class Calculator {
    private CalculatorModel calculatorModel;

    private ActivityMainBinding binding;

    private StringBuilder inputValue;

    private final String        notANumber;
    private final String        multiplicationSign;
    private final String        divisionSign;
    private final String        additionSign;
    private final String        subtractionSign;
    private       String        operator;
    private       String        secondOperator;
    private       double        firstValue             = 0;
    private       double        secondValue            = 0;
    private       StringBuilder secondTextValue;
    private       boolean       hasOperatorJustBeenSet = false;
    private       boolean       isSecondValueSet       = false;
    private       DecimalFormat decimalFormat;

    Calculator(final Context context, ViewDataBinding binding) {
        this.inputValue = new StringBuilder();
        this.notANumber = context.getString(R.string.display_text_not_a_number);
        this.multiplicationSign = context.getString(R.string.button_multiply);
        this.divisionSign = context.getString(R.string.button_divide);
        this.additionSign = context.getString(R.string.button_add);
        this.subtractionSign = context.getString(R.string.button_subtract);
        this.decimalFormat = new DecimalFormat("#.###############");
        this.secondTextValue = new StringBuilder("");

        if (binding instanceof ActivityMainBinding) {
            initSimpleMode((ActivityMainBinding) binding);
        } else {
            // TO-DO change to advanced mode
            initSimpleMode((ActivityMainBinding) binding);
        }
    }

    void initSimpleMode(ActivityMainBinding binding) {
        CalculatorModel calculatorModel = new CalculatorModel(binding.displayPanel, binding.buttonZero, binding.buttonOne, binding.buttonTwo, binding.buttonThree, binding.buttonFour, binding.buttonFive, binding.buttonSix, binding.buttonSeven, binding.buttonEight, binding.buttonNine, binding.buttonComma, binding.buttonMultiply, binding.buttonDivide, binding.buttonAdd, binding.buttonSubtract, binding.buttonPercentage, binding.buttonClear, binding.buttonPlusMinus, binding.buttonEqual);
        this.setNumericButtonOnClickListener(calculatorModel.getZero());
        this.setNumericButtonOnClickListener(calculatorModel.getOne());
        this.setNumericButtonOnClickListener(calculatorModel.getTwo());
        this.setNumericButtonOnClickListener(calculatorModel.getThree());
        this.setNumericButtonOnClickListener(calculatorModel.getFour());
        this.setNumericButtonOnClickListener(calculatorModel.getFive());
        this.setNumericButtonOnClickListener(calculatorModel.getSix());
        this.setNumericButtonOnClickListener(calculatorModel.getSeven());
        this.setNumericButtonOnClickListener(calculatorModel.getEight());
        this.setNumericButtonOnClickListener(calculatorModel.getNine());
        this.setNumericButtonOnClickListener(calculatorModel.getComma());

        this.setBasicOperatorButtonOnClcikListener(calculatorModel.getMultiply());
        this.setBasicOperatorButtonOnClcikListener(calculatorModel.getDivide());
        this.setBasicOperatorButtonOnClcikListener(calculatorModel.getAdd());
        this.setBasicOperatorButtonOnClcikListener(calculatorModel.getSubtract());
    }

    private void setNumericButtonOnClickListener(Button button) {
        button.setOnClickListener(new NumericButtonOnClickListener(this, button));
    }

    void pressNumericButton(String buttonName) {
        if (isAllowedInput(buttonName)) {
//            this.inputValue.append(buttonName);
        }
    }

    boolean isAllowedInput(String buttonName) {
        this.inputValue.append(this.calculatorModel.getPlusMinus().getText().toString());
        String lastChar = this.inputValue.substring(this.inputValue.length() - 1);
        switch (lastChar) {
            case "test":
                break;
        }
        return true;
    }

    private void setBasicOperatorButtonOnClcikListener(Button button) {
        button.setOnClickListener(new BasicOperatorButtonOnClcikListener(button));
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