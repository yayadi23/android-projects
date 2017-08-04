package com.example.renaissance.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.renaissance.adapter.HotTopicAdapter;
import com.example.renaissance.adapter.TopicCategoryAdapter;
import com.example.renaissance.renaissance.R;


public class ColumnFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TopicCategoryAdapter topicCategoryAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_column, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.column_viewpager);
        topicCategoryAdapter = new TopicCategoryAdapter(getChildFragmentManager());
        viewPager.setAdapter(topicCategoryAdapter);
        tabLayout = (TabLayout) view.findViewById(R.id.columnTabLayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    public ViewPager getViewPager(){
        return viewPager;
    }
}
