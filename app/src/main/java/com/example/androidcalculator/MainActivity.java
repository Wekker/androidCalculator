package com.example.androidcalculator;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
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

        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        this.calculator = new Calculator();

        final Observer<String> displayPanelObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String value) {
                binding.displayPanel.setText(value);
            }
        };

        calculator.getDisplayPanelValueLive().observe(this, displayPanelObserver);

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
        this.setBasicOperatorButtonOnClcikListener(binding.displayPanel, calculator, binding.buttonDivide);
        this.setBasicOperatorButtonOnClcikListener(binding.displayPanel, calculator, binding.buttonAdd);
        this.setBasicOperatorButtonOnClcikListener(binding.displayPanel, calculator, binding.buttonSubtract);
    }

    private void setNumericButtonOnClickListener(TextView displayPanel, Calculator calculator, Button button) {
        button.setOnClickListener(new NumericButtonOnClickListener(calculator, button));
    }

    private void setBasicOperatorButtonOnClcikListener(TextView displayPanel, Calculator calculator, Button button) {
        button.setOnClickListener(new BasicOperatorButtonOnClcikListener(calculator, button));
    }
}