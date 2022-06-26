package com.maximmesh.androidintrocalculator.ui;

import com.maximmesh.androidintrocalculator.model.Calculator;
import com.maximmesh.androidintrocalculator.model.Operator;

import java.text.DecimalFormat;

public class CalculatorPresenter {

    private final DecimalFormat formatter = new DecimalFormat("#.##");

    private final CalculatorView view;
    private final Calculator calculator;

    private double argOne;
    private Double argTwo;

    private Operator selectedOperator;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }


    public void oneDigitPressed(int digit) {

        if (argTwo == null) {

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

}