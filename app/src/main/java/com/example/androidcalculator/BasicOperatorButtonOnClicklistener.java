package com.example.androidcalculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class BasicOperatorButtonOnClcikListener implements View.OnClickListener {
    private TextView   displayPanel;
    private Button     button;
    private Calculator calculator;

    BasicOperatorButtonOnClcikListener(TextView displayPanel, Calculator calculator, Button button) {
        this.displayPanel = displayPanel;
        this.button = button;
        this.calculator = calculator;
    }

    @Override
    public void onClick(View v) {
        this.calculator.pressBasicOperatorButton(this.button.getTag().toString());
    }
}
