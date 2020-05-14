package com.example.themeal.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String TABLE_MEAL = "meal";

    public static final class TableColumns implements BaseColumns{
        //public static final String ID_MEAL = "idMeal";
        public static final String STR_MEAL = "strMeal";
        public static final String STR_MEAL_THUMB = "strMealThumb";
    }
}
