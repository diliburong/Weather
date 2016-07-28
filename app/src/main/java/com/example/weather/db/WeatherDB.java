package com.example.weather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.weather.model.City;
import com.example.weather.model.Country;
import com.example.weather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dilib on 2016/7/28.
 */
public class WeatherDB {

    public static final String DB_NAME="cool_weather";



    public static final int VERSION=1;
    private static WeatherDB coolWeatherDB;

    private SQLiteDatabase db;


    private WeatherDB(Context context){
        WeatherOpenHelper dbHelper=new WeatherOpenHelper(context,DB_NAME,null,VERSION);
        db=dbHelper.getWritableDatabase();
    }

    public synchronized static WeatherDB getInstance(Context context){
        if(coolWeatherDB==null){
            coolWeatherDB=new WeatherDB(context);
        }
        return coolWeatherDB;
    }

    //将Province 实例存储到数据库
    public void saveProvince(Province province){
        if(province!=null)
        {
            ContentValues values=new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);

        }
    }

    //从数据库读取全国所有的省份信息
    public List<Province> loadProvinces(){
        List<Province> list=new ArrayList<Province>();
        Cursor cursor=db.query("Province",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Province province=new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("Province_code")));
                list.add(province);
            }while (cursor.moveToNext());
        }
        if(cursor!=null){
            cursor.close();
        }
        return list;
    }

    //将City实例存储到数据库

    public void saveCity(City city){
        if(city!=null){
            ContentValues values=new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("province_id",city.getProvinceID());
            db.insert("City",null,values);
        }
    }


    //从数据库读取某省下所有的城市信息

    public List<City> loadCities(int provinceID){
        List<City> list=new ArrayList<City>();
        Cursor cursor=db.query("City",null,"province_id= ?",new String[] {String.valueOf(provinceID)},null,null,null);

        if(cursor.moveToFirst()){
            do{
                City city=new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                list.add(city);
            }while (cursor.moveToNext());
        }
        if(cursor!=null)
        {
            cursor.close();
        }
        return list;
    }

    //将County实例存储到数据库

    public void saveCounty(Country country){
        if(country!=null){
            ContentValues values=new ContentValues();
            values.put("county_name",country.getCountyName());
            values.put("county_code",country.getCountyCode());
            values.put("city_id",country.getCityId());
            db.insert("County",null,values);
        }
    }

    //从数据库读取某城市下所有县的信息
    public List<Country> loadCounties(int cityID){
        List<Country> list=new ArrayList<Country>();
        Cursor cursor=db.query("County",null,"city_id= ?",new String[] {String.valueOf(cityID)},null,null,null);

        if(cursor.moveToFirst()){
            do{
                Country country=new Country();
                country.setId(cursor.getInt(cursor.getColumnIndex("id")));
                country.setCountyName(cursor.getString(cursor.getColumnIndex("city_name")));
                country.setCountyCode(cursor.getString(cursor.getColumnIndex("city_code")));
                list.add(country);
            }while (cursor.moveToNext());
        }
        if(cursor!=null)
        {
            cursor.close();
        }
        return list;
    }
}
