package com.maximmesh.androidintrocalculator.model;

import java.util.List;

public interface ThemeRepository {

   Theme getSavedTheme();  //сущность, которая предоставит текущую сохраненную тему

    void saveTheme(Theme theme); //сохраняем тему

    List<Theme> allTheme(); //возвращаем список доступных тем
}
