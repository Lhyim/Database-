package com.example.animalbase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AnimalDBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "animal.db";
    private static int DATABASE_VERSION = 1;

    public AnimalDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCommand = "create table animal (_id integer primary key autoincrement, "
                + "species text not null, "
                + "habitat text, "
                + "color text);";
        sqLiteDatabase.execSQL(sqlCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS animal");
        onCreate(sqLiteDatabase);
    }
}

