package com.maximmesh.androidintrocalculator.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.maximmesh.androidintrocalculator.R;
import com.maximmesh.androidintrocalculator.model.CalculatorImpl;
import com.maximmesh.androidintrocalculator.model.Operator;
import com.maximmesh.androidintrocalculator.model.Theme;
import com.maximmesh.androidintrocalculator.model.ThemeRepository;
import com.maximmesh.androidintrocalculator.model.ThemeRepositoryImpl;

import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView { //реализуем интерфейс

    private static final String KEY_PARCE = "KEY_PARCE";

    private TextView resultTxt; //Это TextView, куда мы запишем результат.

    private CalculatorPresenter presenter; //ссылка на Presenter

    private ThemeRepository themeRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        themeRepository = ThemeRepositoryImpl.getInstance(this); //получаем ссылку на репозиторий

        setTheme(themeRepository.getSavedTheme().getThemeRes());

        setContentView(R.layout.activity_calculator);

        resultTxt = findViewById(R.id.result_text_view); //Это TextView, таким образом нашли.

        if(getIntent().hasExtra("message")){

            resultTxt.setText(getIntent().getStringExtra("message"));

        }

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
                } catch (NullPointerException e) {
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
        operators.put(R.id.key_c, Operator.CLEAR);
        operators.put(R.id.key_percent, Operator.PERCENT);

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
        findViewById(R.id.key_c).setOnClickListener(operatorsClickListener);
        findViewById(R.id.key_percent).setOnClickListener(operatorsClickListener);

        findViewById(R.id.key_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.oneDotPressed();
            }
        });

        findViewById(R.id.key_equal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.equalPressed();
            }
        });

        ActivityResultLauncher<Intent> themeLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                if(result.getResultCode() == Activity.RESULT_OK){

                    Intent intent = result.getData();

                    Theme selectedTheme = (Theme) intent.getSerializableExtra(SelectThemeActivity.EXTRA_THEME);

                    themeRepository.saveTheme(selectedTheme);

                    recreate();

                }

            }
        });

        findViewById(R.id.theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CalculatorActivity.this, SelectThemeActivity.class);
                intent.putExtra(SelectThemeActivity.EXTRA_THEME, themeRepository.getSavedTheme()); //таким образом передаем данные в другое активити

                themeLauncher.launch(intent);

            }
        });

    }

    @Override
    public void showResult(String result) {

        resultTxt.setText(result); //Сюда запишется результат.

    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        presenter= savedInstanceState.getParcelable(KEY_PARCE);
        Log.d("onSave", "onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(KEY_PARCE, presenter);
        outState.putParcelable(KEY_PARCE, presenter);
        super.onSaveInstanceState(outState);
        Log.d("onSave", "onSaveInstanceState");

    }

}
