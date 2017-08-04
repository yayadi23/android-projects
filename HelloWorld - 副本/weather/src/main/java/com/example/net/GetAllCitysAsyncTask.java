package com.example.net;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Diak on 2017/2/20.
 */

public abstract class GetAllCitysAsyncTask extends MyAsyncTask {

    @Override
    protected String doInBackground(String... urlStr) {
        HttpURLConnection urlConnection = null;
        StringBuilder resultStr = new StringBuilder();
        try{
            URL url = new URL(urlStr[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            if(urlConnection.getResponseCode() == 200){
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferdReader = new BufferedReader(new InputStreamReader(inputStream));
                bufferdReader.readLine();//从第二行开始读
                String line;
                while((line = bufferdReader.readLine()) != null){
                    resultStr.append(line);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultStr.toString();
    }
}
