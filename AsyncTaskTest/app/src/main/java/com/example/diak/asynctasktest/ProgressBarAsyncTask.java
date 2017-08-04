package com.example.diak.asynctasktest;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Diak on 2017/1/18.
 */

public class ProgressBarAsyncTask extends AsyncTask<Integer,Integer,String>{

    private TextView textView;
    private ProgressBar progressBar;

    public ProgressBarAsyncTask(TextView textView,ProgressBar progressBar){
        super();
        this.textView = textView;
        this.progressBar = progressBar;
    }

    @Override
    protected String doInBackground(Integer... integers) {
        NetOperator netOperator = new NetOperator();
        int i = 0;
        for(i = 10; i <= 100; i+=10){
            netOperator.operator();
            publishProgress(i);
        }
        return i + integers[0].intValue() + "";
    }

    @Override
    protected void onPostExecute(String s) {
        textView.setText("asynctask over");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int value = values[0];
        progressBar.setProgress(value);
    }
}
