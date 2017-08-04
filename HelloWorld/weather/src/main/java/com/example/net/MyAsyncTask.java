package com.example.net;

import android.os.AsyncTask;

/**
 * Created by Diak on 2017/2/20.
 */

public abstract class MyAsyncTask extends AsyncTask<String, Void, String> {

    public abstract void onFail();

    public abstract void onSuccess(String s);

    @Override
    protected void onPostExecute(String s) {
        if(s == null){
            onFail();
        }else {
            onSuccess(s);
        }
    }
}
