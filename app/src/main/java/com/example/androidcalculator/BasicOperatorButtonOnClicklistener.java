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
        Calculator.getCalculator().setOperator(this.button.getText().toString());
        String result = Calculator.getCalculator().composeCalculation();
        this.displayPanel.setText(result);
    }
}
