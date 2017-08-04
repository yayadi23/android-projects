package com.example.dbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diak on 2017/2/17.
 */

public class WeatherDB {
    public static final String DB_NAME = "weather";
    private SQLiteDatabase sqLiteDatabase;
    private static WeatherDB weatherDB;//因为创建的这个数据库是context里唯一的；所以必须是static

    //构造方法私有化
    private WeatherDB(Context context){
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context, DB_NAME, null, 1);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    //获取唯一的实例的方法
    public synchronized  static WeatherDB getInstance(Context context){
        if(weatherDB == null){
            weatherDB = new WeatherDB(context);
        }
        return weatherDB;
    }

    //weatherDB的saveCity()
    public void saveCity(CityEntity cityEntity){
        if(cityEntity != null){
            ContentValues values = new ContentValues();
            values.put("id1",cityEntity.getId1());
            values.put("cityZh",cityEntity.getCityZh());
            values.put("leaderZh",cityEntity.getLeaderZh());
            values.put("provinceZh",cityEntity.getProvinceZh());
            sqLiteDatabase.insert("city", null, values);
        }
    }

    //weatherDB的loadCity()
    public List<CityEntity> loadCity(){
        List<CityEntity> cityEntityList = new ArrayList<CityEntity>();
        CityEntity cityEntity = new CityEntity();
        Cursor cursor = sqLiteDatabase.query("city", null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                cityEntity.setId1(cursor.getString(cursor.getColumnIndex("id1")));
                cityEntity.setCityZh(cursor.getString(cursor.getColumnIndex("cityZh")));
                cityEntity.setLeaderZh(cursor.getString(cursor.getColumnIndex("leaderZh")));
                cityEntity.setProvinceZh(cursor.getString(cursor.getColumnIndex("provinceZh")));
                cityEntityList.add(cityEntity);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return cityEntityList;
    }



}
