package com.example.androidcalculator;

import android.view.View;
import android.widget.Button;

class NumericButtonOnClickListener implements View.OnClickListener {
    private Button     button;
    private Calculator calculator;

    NumericButtonOnClickListener(Calculator calculator, Button button) {
        this.button = button;
        this.calculator = calculator;
    }

    @Override
    public void onClick(View v) {
        this.calculator.pressNumericButton(this.button.getText().toString());
    }
}