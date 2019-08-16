package com.example.androidcalculator;

import android.view.View;

class ClearButtonButtonOnClickListener implements View.OnClickListener {
    private Calculator calculator;

    ClearButtonButtonOnClickListener(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void onClick(View v) {
        this.calculator.pressClearButton();
    }
}
