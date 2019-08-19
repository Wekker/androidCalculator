package com.example.androidcalculator;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

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

        calculator.getDisplayPanelLive().observe(this, displayPanelObserver);

        setNumericButtonOnClickListener(calculator, binding.buttonZero);
        setNumericButtonOnClickListener(calculator, binding.buttonOne);
        setNumericButtonOnClickListener(calculator, binding.buttonTwo);
        setNumericButtonOnClickListener(calculator, binding.buttonThree);
        setNumericButtonOnClickListener(calculator, binding.buttonFour);
        setNumericButtonOnClickListener(calculator, binding.buttonFive);
        setNumericButtonOnClickListener(calculator, binding.buttonSix);
        setNumericButtonOnClickListener(calculator, binding.buttonSeven);
        setNumericButtonOnClickListener(calculator, binding.buttonEight);
        setNumericButtonOnClickListener(calculator, binding.buttonNine);
        setNumericButtonOnClickListener(calculator, binding.buttonComma);

        this.setBasicOperatorButtonOnClcikListener(calculator, binding.buttonMultiply);
        this.setBasicOperatorButtonOnClcikListener(calculator, binding.buttonDivide);
        this.setBasicOperatorButtonOnClcikListener(calculator, binding.buttonAdd);
        this.setBasicOperatorButtonOnClcikListener(calculator, binding.buttonSubtract);

        this.setEqualOperatorButtonOnClcikListener(calculator, binding.buttonEqual);
        this.setClearButtonOnClcikListener(calculator, binding.buttonClear);
    }

    private void setNumericButtonOnClickListener(Calculator calculator, Button button) {
        button.setOnClickListener(new NumericButtonOnClickListener(calculator, button));
    }

    private void setBasicOperatorButtonOnClcikListener(Calculator calculator, Button button) {
        button.setOnClickListener(new BasicOperatorButtonOnClcikListener(calculator, button));
    }

    private void setEqualOperatorButtonOnClcikListener(Calculator calculator, Button button) {
        button.setOnClickListener(new EqualOperatorButtonOnClickListener(calculator));
    }

    private void setClearButtonOnClcikListener(Calculator calculator, Button button) {
        button.setOnClickListener(new ClearButtonButtonOnClickListener(calculator));
    }
}