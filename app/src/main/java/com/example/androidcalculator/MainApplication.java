package com.example.androidcalculator;

import android.app.Application;
import android.os.Bundle;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Calculator.getCalculator(this.getApplicationContext());
    }
}