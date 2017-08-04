package com.example.renaissance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.renaissance.entity.Journey;
import com.example.renaissance.renaissance.R;

/**
 * Created by Diak on 2017/7/28.
 */

public class JourneyDetailActivity extends AppCompatActivity {

    private Toolbar journeyToolbar;
    private Intent intent;
    private Journey journey;
    private ImageView profile_image_view;
    private TextView author_name_tv;
    private TextView time_tv;
    private TextView comment_count_tv;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_detail);
        journeyToolbar = (Toolbar) findViewById(R.id.journeyToolbar);
        setSupportActionBar(journeyToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        journey = (Journey) intent.getSerializableExtra("journey");

        profile_image_view = (ImageView) findViewById(R.id.item_profile_image3);
        profile_image_view.setImageResource(journey.getProfile_image());

        author_name_tv = (TextView) findViewById(R.id.item_author_name3);
        author_name_tv.setText(journey.getAuthor_name());

        time_tv = (TextView) findViewById(R.id.item_time3);
        time_tv.setText(journey.getTime());

        comment_count_tv = (TextView) findViewById(R.id.comment_count3);
        comment_count_tv.setText(journey.getComment_count());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
