package com.example.diak.asynctasktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ProgressBar progressBar;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void init(){}

    public void initView(){
        textView = (TextView)findViewById(R.id.textview01);
        progressBar = (ProgressBar)findViewById(R.id.progressbar02);
        button = (Button)findViewById(R.id.button03);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressBarAsyncTask progressBarAsyncTask = new ProgressBarAsyncTask(textView,progressBar);
                progressBarAsyncTask.execute(1000);
            }
        });
    }


}
