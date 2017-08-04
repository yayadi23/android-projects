package com.example.renaissance.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.renaissance.adapter.JourneyAdapter;
import com.example.renaissance.renaissance.R;

import java.util.ArrayList;
import java.util.List;

public class LiveFragment extends Fragment {
    private View view;
    private RecyclerView mRecyclerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_live, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.journeyRecyclerView);
        mRecyclerView.setAdapter(new JourneyAdapter());
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        return view;
    }
}
