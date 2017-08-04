package com.example.renaissance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.renaissance.entity.Topic;
import com.example.renaissance.renaissance.R;

public class TopicDetailActivity extends AppCompatActivity {

    private Toolbar topicToolbar;
    private Intent intent;
    private Topic topic;


    private ImageView profile_image;
    private TextView author_name;
    private TextView time;
    private TextView content_detail;
    //HorizontalScrollView动态赋图片 // TODO: 2017/7/27
    private TextView comment_count;
    //comment list; // TODO: 2017/7/27
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_topic_detail);
            topicToolbar = (Toolbar) findViewById(R.id.topicToolbar);
            setSupportActionBar(topicToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            intent = getIntent();
            topic = (Topic) intent.getSerializableExtra("topic");

            profile_image = (ImageView) findViewById(R.id.item_profile_image1);
            profile_image.setImageResource(topic.getProfile_image());
            author_name = (TextView) findViewById(R.id.item_author_name1);
            author_name.setText(topic.getAuthor_name());
            time = (TextView) findViewById(R.id.item_time1);
            time.setText(topic.getTime());
            content_detail = (TextView) findViewById(R.id.item_content_detail1);
            content_detail.setText(topic.getContent_detail());
            comment_count = (TextView) findViewById(R.id.comment_count2);
            comment_count.setText(topic.getComment_count());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
