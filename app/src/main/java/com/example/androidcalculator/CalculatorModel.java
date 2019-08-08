package com.example.androidcalculator;

import android.widget.Button;
import android.widget.TextView;

class CalculatorModel {
    private TextView displayPanel;
    private Button   zero;
    private Button   one;
    private Button   two;
    private Button   three;
    private Button   four;
    private Button   five;
    private Button   six;
    private Button   seven;
    private Button   eight;
    private Button   nine;
    private Button   comma;
    private Button   multiply;
    private Button   divide;
    private Button   add;
    private Button   subtract;
    private Button   percentage;
    private Button   clear;
    private Button   plusMinus;
    private Button   equal;

    CalculatorModel(TextView displayPanel, Button zero, Button one, Button two, Button three, Button four, Button five, Button six, Button seven, Button eight, Button nine, Button comma, Button multiply, Button divide, Button add, Button subtract, Button percentage, Button clear, Button plusMinus, Button equal) {
        this.displayPanel = displayPanel;
        this.zero = zero;
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
        this.six = six;
        this.seven = seven;
        this.eight = eight;
        this.nine = nine;
        this.comma = comma;
        this.multiply = multiply;
        this.divide = divide;
        this.add = add;
        this.subtract = subtract;
        this.percentage = percentage;
        this.clear = clear;
        this.plusMinus = plusMinus;
        this.equal = equal;
    }

    TextView getDisplayPanel() {
        return displayPanel;
    }

    Button getZero() {
        return zero;
    }

    Button getOne() {
        return one;
    }

    Button getTwo() {
        return two;
    }

    Button getThree() {
        return three;
    }

    Button getFour() {
        return four;
    }

    Button getFive() {
        return five;
    }

    Button getSix() {
        return six;
    }

    Button getSeven() {
        return seven;
    }

    Button getEight() {
        return eight;
    }

    Button getNine() {
        return nine;
    }

    Button getComma() {
        return comma;
    }

    Button getMultiply() {
        return multiply;
    }

    Button getDivide() {
        return divide;
    }

    Button getAdd() {
        return add;
    }

    Button getSubtract() {
        return subtract;
    }

    Button getPercentage() {
        return percentage;
    }

    Button getClear() {
        return clear;
    }

    Button getPlusMinus() {
        return plusMinus;
    }

    Button getEqual() {
        return equal;
    }
}