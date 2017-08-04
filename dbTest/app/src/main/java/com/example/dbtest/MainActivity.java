package com.example.dbtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //建立一个weatherdb，写入一条city数据；不需要什么控件；

    private WeatherDB weatherDB;
    private CityEntity cityEntity;
    private List<CityEntity> cityEntityList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityEntity = new CityEntity();
        cityEntity.setId1("CN10010101");
        cityEntity.setCityZh("朝阳");
        cityEntity.setLeaderZh("北京");
        cityEntity.setProvinceZh("北京");
        weatherDB = WeatherDB.getInstance(this);
        weatherDB.saveCity(cityEntity);
        cityEntityList = weatherDB.loadCity();
        int i  = 0;
        for(CityEntity city: cityEntityList){
            Toast.makeText(this, city.getCityZh() + (i++), Toast.LENGTH_SHORT).show();
        }

    }
}
