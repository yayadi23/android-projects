package com.example.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Diak on 2017/2/20.
 */

public abstract class GetWholeJsonAsyncTask extends MyAsyncTask {

    @Override
    protected String doInBackground(String... urlStr) {
        HttpURLConnection urlConnection = null;
        StringBuilder resultStr = new StringBuilder();
        try {
            URL url = new URL(urlStr[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            if (urlConnection.getResponseCode() == 200) {
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferdReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferdReader.readLine()) != null) {
                    resultStr.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        if(s.endsWith("\"OK\"}")){
            onSuccess(s);
        }else {
            onFail();
        }
    }
}
