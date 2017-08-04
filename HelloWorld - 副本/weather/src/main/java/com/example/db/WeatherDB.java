package com.example.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.entity.CityEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diak on 2017/2/15.
 */

public class WeatherDB {
    public static final String DB_NAME = "weather";
    public static final int VERSION = 1;
    private static WeatherDB weatherDB;
    private SQLiteDatabase db;

    private WeatherDB(Context context){
        WeatherOpenHelper dbOpenHelper = new WeatherOpenHelper(context,DB_NAME,null,VERSION);
        db = dbOpenHelper.getWritableDatabase();

    }

    public synchronized static WeatherDB getInstance(Context context){
        if(weatherDB == null){
            weatherDB = new WeatherDB(context);
        }
        return weatherDB;
    }

    public void saveCity(CityEntity cityEntity){
        if(cityEntity != null){
            ContentValues values = new ContentValues();
            values.put("id1",cityEntity.getId());
            values.put("cityEn",cityEntity.getCityEn());
            values.put("cityZh",cityEntity.getCityZh());
            values.put("countryCode",cityEntity.getCountryCode());
            values.put("countryEn",cityEntity.getCountryEn());
            values.put("countryZh",cityEntity.getCountryZh());
            values.put("provinceEn",cityEntity.getProvinceEn());
            values.put("provinceZh",cityEntity.getProvinceZh());
            values.put("leaderEn",cityEntity.getLeaderEn());
            values.put("leaderZh",cityEntity.getLeaderZh());
            values.put("lat",cityEntity.getLat());
            values.put("lon",cityEntity.getLon());
            db.insert("City",null,values);
        }
    }
    public List<CityEntity> loadCitys(){
        List<CityEntity> list = new ArrayList<CityEntity>();//因为这里父类其实指向的是一个子类，这个返回的父类也可以强制转化为子类
        Cursor cursor = db.query("City",null,null,null,null,null,null);
//        boolean b = cursor.moveToFirst();
        if(cursor.moveToFirst()){
            do{
                CityEntity cityEntity = new CityEntity();
                cityEntity.setCityZh(cursor.getString(cursor.getColumnIndex("cityZh")));
//                Log.d("cityZh:",cursor.getString(cursor.getColumnIndex("cityZh")));
                cityEntity.setLeaderZh(cursor.getString(cursor.getColumnIndex("leaderZh")));
                cityEntity.setProvinceZh(cursor.getString(cursor.getColumnIndex("provinceZh")));
                cityEntity.setCountryZh(cursor.getString(cursor.getColumnIndex("countryZh")));
                list .add(cityEntity);
            }while(cursor.moveToNext());
        }
        return list;
    }

    public void beginTransaction(){
        db.beginTransaction();
    }

    public void endTransaction(){
        db.endTransaction();
    }

    public void setTransactionSuccessful(){
        db.setTransactionSuccessful();
    }

    public void close(){
        db.close();
    }
}
