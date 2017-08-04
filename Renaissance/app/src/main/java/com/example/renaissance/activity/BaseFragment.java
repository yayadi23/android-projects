package com.example.renaissance.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


public abstract class BaseFragment extends Fragment {
    public abstract RecyclerView.Adapter getRecyclerViewAdapter();
}
