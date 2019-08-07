package com.example.androidcalculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class BasicOperatorButtonOnClcikListener implements View.OnClickListener {
    private TextView displayPanel;
    private Button button;

    BasicOperatorButtonOnClcikListener(TextView displayPanel, Button button) {
        this.displayPanel = displayPanel;
        this.button = button;
    }

    @Override
    public void onClick(View v) {
        String newValue = Calculator.getCalculator().getDisplayValue(this.button.getText().toString());
        this.displayPanel.setText(newValue);
    }
}
