package com.example.androidcalculator;

import android.view.View;
import android.widget.Button;

class BasicOperatorButtonOnClcikListener implements View.OnClickListener {
    private Button     button;
    private Calculator calculator;

    BasicOperatorButtonOnClcikListener(Calculator calculator, Button button) {
        this.button = button;
        this.calculator = calculator;
    }

    @Override
    public void onClick(View v) {
        this.calculator.pressBasicOperatorButton(this.button.getTag().toString());
    }
}
