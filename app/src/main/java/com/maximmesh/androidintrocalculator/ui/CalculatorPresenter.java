package com.maximmesh.androidintrocalculator.ui;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.maximmesh.androidintrocalculator.model.Calculator;
import com.maximmesh.androidintrocalculator.model.Operator;

import java.text.DecimalFormat;


public class CalculatorPresenter implements Parcelable {


    private final DecimalFormat formatter = new DecimalFormat("#.##");

    private CalculatorView view;
    private Calculator calculator;
    private double argOne;
    private double argTwo;


    private Operator selectedOperator;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }


    protected CalculatorPresenter(Parcel in) {
        argOne = in.readDouble();
        argTwo = in.readDouble();
    }

    public static final Creator<CalculatorPresenter> CREATOR = new Creator<CalculatorPresenter>() {
        @Override
        public CalculatorPresenter createFromParcel(Parcel in) {
            return new CalculatorPresenter(in);
        }

        @Override
        public CalculatorPresenter[] newArray(int size) {
            return new CalculatorPresenter[size];
        }
    };

    public void oneDigitPressed(int digit) {

        if (selectedOperator == null) {

            argOne = argOne * 10 + digit;

            showFormatted(argOne);

        } else {
            argTwo = argTwo * 10 + digit;

            showFormatted(argTwo);
        }


    }

    public void oneOperatorPressed(Operator operator) {

        if (selectedOperator != null) {

            argOne = calculator.perform(argOne, argTwo, selectedOperator);

            showFormatted(argOne);


        }
        argTwo = 0.0;


        selectedOperator = operator;
        }


    public void oneDotPressed() {


    }


    private void showFormatted(double value) {

        view.showResult(formatter.format(value));

    }

    public void equalPressed() {

        argOne = calculator.perform(argOne, argTwo, selectedOperator);
        showFormatted(argOne);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(argOne);
        dest.writeDouble(argTwo);
    }
}

