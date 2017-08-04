package com.example.net;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.entity.CityEntity;
import com.example.weather.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diak on 2017/7/28.
 */

public abstract class ReadCityTxtAsyncTask extends AsyncTask<String, Void, List<CityEntity>> {

    private Context context;
    private CityEntity cityEntity;
    private List<CityEntity> cityList;

    public ReadCityTxtAsyncTask(Context context){
        this.context = context;
    }

    public abstract void onFail();


    public abstract void onSuccess(List<CityEntity> list);

    @Override
    protected List<CityEntity> doInBackground(String... strings) {
        readCities();
        return cityList;
    }

    public void readCities(){
        InputStream inputStream = context.getResources().openRawResource(R.raw.citydata);
        InputStreamReader inputStreamReader = null;
        try{
            inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        BufferedReader bf = new BufferedReader(inputStreamReader);
        String str;
        String[] strs;
        cityList = new ArrayList<>();
        try{
            while((str = bf.readLine()) != null){ // str就是一行城市数据 直接转换成cityEntity
                strs = str.split("\t");
                cityEntity = new CityEntity();
                cityEntity.setId(strs[0]);
                cityEntity.setCityEn(strs[1]);
                cityEntity.setCityZh(strs[2]);
                cityEntity.setCountryCode(strs[3]);
                cityEntity.setCountryEn(strs[4]);
                cityEntity.setCountryZh(strs[5]);
                cityEntity.setProvinceEn(strs[6]);
                cityEntity.setProvinceZh(strs[7]);
                cityEntity.setLeaderEn(strs[8]);
                cityEntity.setLeaderZh(strs[9]);
                cityEntity.setLat(strs[10]);
                cityEntity.setLon(strs[11]);
                Log.d("city id", strs[0]);
                Log.d("city zh", strs[2]);
                cityList.add(cityEntity);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(List<CityEntity> cityList) {
        if(cityList == null){
            onFail();
        } else{
            onSuccess(cityList);
        }
    }
}

