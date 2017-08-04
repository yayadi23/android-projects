package com.example.renaissance.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.renaissance.adapter.FigureAdapter;
import com.example.renaissance.renaissance.R;

public class DrawFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private FigureAdapter figureAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_draw, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.drawRecyclerView);
        figureAdapter = new FigureAdapter();
        mRecyclerView.setAdapter(figureAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        return view;
    }

    @Override
    public RecyclerView.Adapter getRecyclerViewAdapter() {
        return figureAdapter;
    }
}
