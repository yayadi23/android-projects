package com.example.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.activity.MainActivity;
import com.example.entity.Data;
import com.example.entity.WeatherEntity;
import com.example.net.GetWholeJsonAsyncTask;
import com.example.receiver.AlarmReceiver;
import com.example.weather.R;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Diak on 2017/2/25.
 */

public class UpdateWeatherService extends Service {
    private String url = "http://wthrcdn.etouch.cn/weather_mini?city=";
    private Intent in;
    private Set<String> dataSet = new HashSet<String>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                updateWeather();
            }
        }).start();
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int sec = 20*1000;
        long triggerTime = SystemClock.elapsedRealtime() + sec;
        Intent intent1 = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent1, 0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateWeather() {
        //先读取citys变量里面存储的citySet；
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        Set<String> citySet = sharedPreferences.getStringSet("citys", null);
        String url1;
        if (citySet != null) {
            for (String city : citySet) {
                url1 = url + city;
                loadWeatherData(url1);
            }
        }
    }

    private void loadWeatherData(String url){

        GetWholeJsonAsyncTask getWholeJsonAsyncTask = new GetWholeJsonAsyncTask() {
            @Override
            public void onFail() {
                Log.d("UpdateWeatherService","failed!");
            }

            @Override
            public void onSuccess(String s) {
//                WeatherEntity weatherEntity = JSON.parseObject(s, WeatherEntity.class);
//                Data data = weatherEntity.getData();//先把返回的字符串直接放在Sharedpreferences处理；
                Log.d("自动更新的天气", s);
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                dataSet.add(s);
                editor.putStringSet("weather",dataSet);
                editor.commit();
            }
        };
        getWholeJsonAsyncTask.execute(url);
    }
}
