package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.service.UpdateWeatherService;

/**
 * Created by Diak on 2017/2/25.
 */

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, UpdateWeatherService.class);
        context.startService(intent1);
    }
}
