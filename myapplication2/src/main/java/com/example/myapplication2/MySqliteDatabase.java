package com.example.myapplication2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MySqliteDatabase extends SQLiteOpenHelper {
    private static final String  Book1   =   "create table book1("
            +" id integer primary key autoincrement ,"
            +" author text, "
            +" price real )";

    private static final String Book2   =   "create table book2("
            +" id integer primary key autoincrement, "
            +" author text ,"
            +" price real )";
    public MySqliteDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Book1);
        db.execSQL(Book2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists book1 ");
        db.execSQL("drop table if exists book2 ");
        onCreate(db);
    }
}
