package com.example.weather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dilib on 2016/7/28.
 */
public class WeatherOpenHelper extends SQLiteOpenHelper {


    public static final String CREATE_PROVINCE="create table Province ("+"id integer primary key autoincrement, "
                                                    +"porvince_name text，"+"province_code text)";
    public static final String CREATE_CITY="create table City("+"id integer primary key autoincrement,"
                                                +"city_name text,"+"city_code text,"
                                                +"province_id integer)";

    public static final String CREATE_COUNTY="create table County("+"id integer primary key autoincrement,"
            +"county_name text,"+"county_code text,"
            +"city_id integer)";



    public WeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
