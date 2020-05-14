package com.example.themeal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbmeal";
    private static final int DATABASE_VERSION = 2;
    private static final String SQL_CREATE_TABLE_MEAL = String.format("CREATE TABLE %s"
                    + " (%s STRING PRIMARY KEY," +
                    " %s TEXT ," +
                    " %s TEXT )",
            DatabaseContract.TABLE_MEAL,
            DatabaseContract.TableColumns._ID,
            DatabaseContract.TableColumns.STR_MEAL,
            DatabaseContract.TableColumns.STR_MEAL_THUMB
    );
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MEAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_MEAL);
        onCreate(db);
    }
}
