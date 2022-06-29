package com.maximmesh.androidintrocalculator.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.maximmesh.androidintrocalculator.R;
import com.maximmesh.androidintrocalculator.model.CalculatorImpl;
import com.maximmesh.androidintrocalculator.model.Operator;

import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private TextView resultTxt;

    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


        resultTxt = findViewById(R.id.result);

        presenter = new CalculatorPresenter(this, new CalculatorImpl());

        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.key_1, 1);
        digits.put(R.id.key_2, 2);
        digits.put(R.id.key_3, 3);
        digits.put(R.id.key_4, 4);
        digits.put(R.id.key_5, 5);
        digits.put(R.id.key_6, 6);
        digits.put(R.id.key_7, 7);
        digits.put(R.id.key_8, 8);
        digits.put(R.id.key_9, 9);
        digits.put(R.id.key_0, 0);

        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    presenter.oneDigitPressed(digits.get(view.getId()));
                }catch (NullPointerException e){
                    e.printStackTrace();
                }

            }
        };

        findViewById(R.id.key_1).setOnClickListener(digitClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitClickListener);
        findViewById(R.id.key_0).setOnClickListener(digitClickListener);

        Map<Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.key_addition, Operator.ADD);
        operators.put(R.id.key_division, Operator.DIV);
        operators.put(R.id.key_multiply, Operator.MULT);
        operators.put(R.id.key_subtract, Operator.SUB);

        View.OnClickListener operatorsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.oneOperatorPressed(operators.get(view.getId()));

            }
        };

        findViewById(R.id.key_addition).setOnClickListener(operatorsClickListener);
        findViewById(R.id.key_division).setOnClickListener(operatorsClickListener);
        findViewById(R.id.key_multiply).setOnClickListener(operatorsClickListener);
        findViewById(R.id.key_subtract).setOnClickListener(operatorsClickListener);

        findViewById(R.id.key_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.oneDotPressed();
            }
        });

    }

    @Override
    public void showResult(String result) {

        resultTxt.setText(result);

    }
}
