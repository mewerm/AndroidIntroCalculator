package com.maximmesh.androidintrocalculator.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.maximmesh.androidintrocalculator.R;
import com.maximmesh.androidintrocalculator.model.Theme;
import com.maximmesh.androidintrocalculator.model.ThemeRepository;
import com.maximmesh.androidintrocalculator.model.ThemeRepositoryImpl;

import java.util.List;

public class SelectThemeActivity extends AppCompatActivity {

    public static final String EXTRA_THEME = "EXTRA_THEME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_theme);

        findViewById(R.id.how_to).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://yandex.ru"));   //так провоцируем открытие браузера по нажатию на конпку "How to change theme"
                // Intent.ACTION_VIEW - как сообщение "хочу посмотреть". с помощью Uri.parce - парсим ресурс
                // Парсинг – это метод индексирования информации с последующей конвертацией ее в иной формат или даже иной тип данных.
                // Парсинг позволяет взять файл
                // в одном формате и преобразовать его данные в более удобоваримую форму, которую можно использовать в своих целях.
            startActivity(Intent.createChooser(browserIntent, null));
            }
        });

        ThemeRepository themeRepository = ThemeRepositoryImpl.getInstance(this);

        List<Theme> themes = themeRepository.allTheme();

        LinearLayout conteiner = findViewById(R.id.conteiner);

        Intent intent = getIntent();//так активити сюда передали

        Theme selectedThem = (Theme) intent.getSerializableExtra(EXTRA_THEME); //так активити сюда передали

        for (Theme theme : themes) {

            View itemView = getLayoutInflater().inflate(R.layout.item_theme, conteiner, false);

            TextView title = itemView.findViewById(R.id.title);

            title.setText(theme.getTitle());

            CardView cardView = itemView.findViewById(R.id.theme_card);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent data = new Intent();
                    data.putExtra(EXTRA_THEME, theme);


                    setResult(Activity.RESULT_OK, data);
                    finish();
                }
            });


            ImageView checked = itemView.findViewById(R.id.checked);

            if (theme.equals(selectedThem)) {
                checked.setVisibility(View.VISIBLE);
            } else {
                checked.setVisibility(View.GONE);
            }

            conteiner.addView(itemView);

        }
    }
}