package com.example.diak.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Diak on 2017/2/23.
 */

public class AddCityAcivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcity);
        Button btnPingdingshan = (Button) findViewById(R.id.btnPingdingshan);
        Button btnLushan = (Button) findViewById(R.id.btnLushan);
        Button btnBaofeng = (Button) findViewById(R.id.btnBaofeng);
        btnPingdingshan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("CityName","pingdingshan");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        btnLushan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("CityName","lushan");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        btnBaofeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("CityName","baofeng");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
