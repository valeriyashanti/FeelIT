package com.login.valeriya.feelit.Utils;

import java.util.ArrayList;

public class Questions  {

    public static ArrayList<String> text = new ArrayList<String>() {
        {
            add("Какой у вас образ жизни ?");
            add("Какой у вас тип питания ?");
            add("Какой у вас пол ?");
            add("Укажите ваш год рождения:");
            add("Укажите ваш вес:");
            add("Укажите ваш рост");
        }
    };

    public static ArrayList<String[]> answers = new ArrayList<String[]>() {
        {
            add(new String[] {"Активный", "Средне активный", "Пассивный"});
            add(new String[] {"Мясоед", "Вегетарианец", "Веган"});
            add(new String[] {"Мужской", "Женский", "Не определился"});

        }
    };
}

