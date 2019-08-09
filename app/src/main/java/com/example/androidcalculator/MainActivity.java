package com.example.androidcalculator;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidcalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        this.calculator = new Calculator();

        setNumericButtonOnClickListener(binding.displayPanel, calculator, binding.buttonZero);
        setNumericButtonOnClickListener(binding.displayPanel, calculator, binding.buttonOne);
        setNumericButtonOnClickListener(binding.displayPanel, calculator, binding.buttonTwo);
        setNumericButtonOnClickListener(binding.displayPanel, calculator, binding.buttonThree);
        setNumericButtonOnClickListener(binding.displayPanel, calculator, binding.buttonFour);
        setNumericButtonOnClickListener(binding.displayPanel, calculator, binding.buttonFive);
        setNumericButtonOnClickListener(binding.displayPanel, calculator, binding.buttonSix);
        setNumericButtonOnClickListener(binding.displayPanel, calculator, binding.buttonSeven);
        setNumericButtonOnClickListener(binding.displayPanel, calculator, binding.buttonEight);
        setNumericButtonOnClickListener(binding.displayPanel, calculator, binding.buttonNine);
        setNumericButtonOnClickListener(binding.displayPanel, calculator, binding.buttonComma);

        this.setBasicOperatorButtonOnClcikListener(binding.displayPanel, calculator, binding.buttonMultiply);

    }

    private void setNumericButtonOnClickListener(TextView displayPanel, Calculator calculator, Button button) {
        button.setOnClickListener(new NumericButtonOnClickListener(displayPanel, calculator, button));
    }

    private void setBasicOperatorButtonOnClcikListener(TextView displayPanel, Calculator calculator, Button button) {
        button.setOnClickListener(new BasicOperatorButtonOnClcikListener(displayPanel, calculator, button));
    }
}