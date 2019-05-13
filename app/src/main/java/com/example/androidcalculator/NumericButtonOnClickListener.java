package com.example.androidcalculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class NumericButtonOnClickListener implements View.OnClickListener {
    private TextView displayPanel;
    private Button button;

    NumericButtonOnClickListener(TextView displayPanel, Button button) {
        this.displayPanel = displayPanel;
        this.button = button;
    }

    @Override
    public void onClick(View v) {
        String displayValue = this.displayPanel.getText().toString();
        if (!displayValue.equals("0") && isNumeric(displayValue) && !Calculator.getCalculator().isOperatorSet()) {
            this.displayPanel.append(this.button.getText());
            Calculator.getCalculator().setValue(this.displayPanel.getText().toString());
        } else {
            Calculator.getCalculator().setValue(displayValue);
            this.displayPanel.setText(this.button.getText());
        }
    }

    private boolean isNumeric(String value) {
        return value.matches("-?\\d+(\\.\\d+)?");
    }
}
