package com.example.renaissance.net;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Diak on 2017/7/11.
 */

public class OkHttpUtil {
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();
    static {
        mOkHttpClient.setConnectTimeout(20, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(20,TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(20,TimeUnit.SECONDS);
    }

    /**
     * 同步请求
     * @param request
     * @return
     * @throws IOException
     */
    public static Response execute(Request request) throws IOException{
        return mOkHttpClient.newCall(request).execute();
    }

    /**
     * 异步请求
     * @param request
     * @param callback
     */
    public static void enqueue(Request request, Callback callback){
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * 异步请求，无callback
     * @param request
     */
    public  static void enqueue(Request request){
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });
    }

    /**
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String getStringFromServer(String url) throws IOException{
        Request request = new Request.Builder().url(url).build();
        Response response = execute(request);
        if(response.isSuccessful()){
            String responseStr = response.body().string();
            return responseStr;
        } else {
            throw new IOException("Unexpected code" + response);
        }
    }
}
