package com.example.androidcalculator;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.androidcalculator.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        this.setNumericButtonOnClickListener(binding.buttonZero);
        this.setNumericButtonOnClickListener(binding.buttonOne);
        this.setNumericButtonOnClickListener(binding.buttonTwo);
        this.setNumericButtonOnClickListener(binding.buttonThree);
        this.setNumericButtonOnClickListener(binding.buttonFour);
        this.setNumericButtonOnClickListener(binding.buttonFive);
        this.setNumericButtonOnClickListener(binding.buttonSix);
        this.setNumericButtonOnClickListener(binding.buttonSeven);
        this.setNumericButtonOnClickListener(binding.buttonEight);
        this.setNumericButtonOnClickListener(binding.buttonNine);
        this.setNumericButtonOnClickListener(binding.buttonComma);

        this.setBasicOperatorButtonOnClcikListener(binding.buttonDivide);
        this.setBasicOperatorButtonOnClcikListener(binding.buttonMultiply);
        this.setBasicOperatorButtonOnClcikListener(binding.buttonAdd);
        this.setBasicOperatorButtonOnClcikListener(binding.buttonSubtract);
    }

    private void setNumericButtonOnClickListener(Button button) {
        button.setOnClickListener(new NumericButtonOnClickListener(, binding.displayPanel, button));
    }

    private void setBasicOperatorButtonOnClcikListener(Button button) {
        button.setOnClickListener(new BasicOperatorButtonOnClcikListener(binding.displayPanel, button));
    }
}