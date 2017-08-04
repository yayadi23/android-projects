package com.example.renaissance.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.renaissance.entity.Topic;
import com.example.renaissance.renaissance.R;

public class TopicDetailFragment extends Fragment{
    private Topic topic;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        topic = (Topic)getArguments().get("topicdetail");
        String string = topic.getAuthor_name() + " " + topic.getTime();
        view = inflater.inflate(R.layout.fragment_topic_detail,container,false);
        return view;
    }
}
