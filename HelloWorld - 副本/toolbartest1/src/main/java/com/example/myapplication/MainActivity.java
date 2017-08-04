package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        toolbar.setTitle("Toolbar1");
        toolbar.setSubtitle("toolbar2");
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);//居然真的是因为将这句放在set语句前面而导致不显示的！

    }
}
