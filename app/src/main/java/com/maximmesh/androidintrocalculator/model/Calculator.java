package com.maximmesh.androidintrocalculator.model;

import android.os.Parcelable;

public interface Calculator  {

    double perform(double arg1, double arg2, Operator operator); //тут пишем что будет происходить
    //те пользователь выполняет операцию с числами arg1 и arg2 и получает назад double
}

//p.s коменты пишу для себя, чтобы не теряться