package com.maximmesh.androidintrocalculator.model;

import androidx.annotation.StyleRes;

import com.maximmesh.androidintrocalculator.R;

public enum Theme {
    ONE(R.style.Theme_AndroidIntroCalculator, R.string.theme_1, "themeone"),
    TWO(R.style.Theme_AndroidIntroCalculator_ChangeTheme,R.string.theme_2, "themetwo");

    Theme(int themeRes, int title, String key) {
        this.themeRes = themeRes;
        this.title = title;
        this.key = key;
    }

    @StyleRes
    private int themeRes;

    @StyleRes
    private int title;

    private String key;

    public int getThemeRes() {
        return themeRes;
    }

    public int getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }
}
