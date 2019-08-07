package com.example.androidcalculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class NumericButtonOnClickListener implements View.OnClickListener {
    private TextView   displayPanel;
    private Button     button;
    private Calculator calculator;

    NumericButtonOnClickListener(Calculator calculator, TextView displayPanel, Button button) {
        this.displayPanel = displayPanel;
        this.button = button;
        this.calculator = calculator;
    }

    @Override
    public void onClick(View v) {
        String newValue = this.calculator.getDisplayValue(this.button.getText().toString());
        this.displayPanel.setText(newValue);
    }
}