package com.example.androidcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.androidcalculator.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main);

        Calculator.getCalculator().composeCalculation();
    }
}