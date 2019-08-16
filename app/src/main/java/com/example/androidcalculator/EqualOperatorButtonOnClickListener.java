package com.example.androidcalculator;

import android.view.View;

class EqualOperatorButtonOnClickListener implements View.OnClickListener {
    private Calculator calculator;

    EqualOperatorButtonOnClickListener(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void onClick(View v) {
        this.calculator.pressEqualOperatorButton();
    }
}