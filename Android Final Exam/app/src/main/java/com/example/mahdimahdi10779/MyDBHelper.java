package com.example.mahdimahdi10779;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(@Nullable Context context) {
        super(context, "Mahdi",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not exists data (date varchar(50),place varchar(20),initial float,spent float)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS data");
        onCreate(db);
    }

    public void insert(Date date , String place,double initial,double spent)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date",date.toString());
        values.put("place",place);
        values.put("initial",initial);
        values.put("spent",spent);
        db.insert("data",null,values);
        db.close();
    }
    public ArrayList<String> getAll()
    {
        ArrayList<String> res = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from data",null);
        if(cursor.moveToFirst())
        {
            do {
                String string = cursor.getString(0)+"\n"+cursor.getString(1)
                        +"\nInitial amount: "+cursor.getFloat(2)+"\nYou spent: "+cursor.getFloat(3);
                res.add(string);
            }while (cursor.moveToNext());
        }
        return  res;
    }
}
