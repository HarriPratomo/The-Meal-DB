package com.example.themeal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


import com.example.themeal.model.meal.Meal;

import java.util.ArrayList;

import static com.example.themeal.database.DatabaseContract.TABLE_MEAL;
import static com.example.themeal.database.DatabaseContract.TableColumns.STR_MEAL;
import static com.example.themeal.database.DatabaseContract.TableColumns.STR_MEAL_THUMB;


public class MealHelper {
    private static final String DATABASE_TABLE = TABLE_MEAL;
    private static DatabaseHelper dataBaseHelper;
    private static MealHelper INSTANCE;

    private static SQLiteDatabase database;

    private MealHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static MealHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MealHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public ArrayList<Meal> getAllMeal() {
        ArrayList<Meal> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                BaseColumns._ID + " ASC",
                null);
        cursor.moveToFirst();
        Meal meal;
        if (cursor.getCount() > 0) {
            do {
                meal = new Meal();
                meal.setIdMeal(cursor.getString(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
                meal.setStrMeal(cursor.getString(cursor.getColumnIndexOrThrow(STR_MEAL)));
                meal.setStrMealThumb(cursor.getString(cursor.getColumnIndexOrThrow(STR_MEAL_THUMB)));

                arrayList.add(meal);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Meal meal) {
        ContentValues args = new ContentValues();
        args.put(BaseColumns._ID, meal.getIdMeal());
        args.put(STR_MEAL, meal.getStrMeal());
        args.put(STR_MEAL_THUMB, meal.getStrMealThumb());
        return database.insert(DATABASE_TABLE, null, args);
    }

    public boolean isExist(int id) {
        String query = "SELECT * FROM " + DATABASE_TABLE + " WHERE " + BaseColumns._ID + " =?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id)});
        boolean exist = false;
        if (cursor.moveToFirst()) {
            exist = true;
        }
        cursor.close();
        return exist;
    }

//    public int update(Movies movie) {
//        ContentValues args = new ContentValues();
//        args.put(TITLE, movie.getTitle());
//        args.put(OVERVIEW, movie.getOverview());
//        args.put(RELEASE_DATE, movie.getReleaseDate());
//        args.put(LANGUAGE, movie.getOriginalLanguage());
//        args.put(POPULARITY, movie.getPopularity());
//        args.put(VOTE, movie.getVoteAverage());
//        args.put(POSTER, movie.getPosterPath());
//        args.put(BACKDROP, movie.getBackdropPath());
//        return database.update(DATABASE_TABLE, args, BaseColumns._ID + "= '" + movie.getId() + "'", null);
//    }

    public int delete(int id) {
        return database.delete(DATABASE_TABLE, BaseColumns._ID + " = '" + id + "'", null);
    }
}
