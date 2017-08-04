package com.example.dbtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Diak on 2017/2/17.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    //初始化、创建数据库；
    private Context context;

    public static final String CREATE_WEATHER = "create table city("
            + "id integer primary key autoincrement,"
            + "id1 text,"
            + "cityZh text,"
            + "leaderZh text,"
            + "provinceZh text)";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version){
        super(context, name, cursorFactory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(CREATE_WEATHER);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

    }
}
