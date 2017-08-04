package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Diak on 2017/2/15.
 */

public class WeatherOpenHelper extends SQLiteOpenHelper {

    public static final String CREATE_CITY = "create table City ("
            + "id integer primary key autoincrement,"
            + "id1 text,"
            + "cityEn text,"//县区
            + "cityZh text,"
            + "countryCode text,"//中国
            + "countryEn text,"
            + "countryZh text,"
            + "provinceEn text,"//省名
            + "provinceZh text,"
            + "leaderEn text,"//市名
            + "leaderZh text,"
            + "lat text,"//经纬度
            + "lon text)";
    public static final String DROP_TABLE = "drop table if exists City";

    public WeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory,int version){
        super(context,name,cursorFactory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        sqLiteDatabase.execSQL(CREATE_CITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL(DROP_TABLE);
//        onCreate(sqLiteDatabase);
    }
}
