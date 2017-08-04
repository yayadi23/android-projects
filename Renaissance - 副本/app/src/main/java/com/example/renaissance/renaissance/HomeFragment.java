package com.example.renaissance.renaissance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.rollviewpager.RollPagerView;

/**
 * Created by Diak on 2017/7/9.
 */

public class HomeFragment extends Fragment {
    private  RollPagerView mRollPagerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRollPagerView = (RollPagerView) view.findViewById(R.id.rollPagerView);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mRollPagerView.setAdapter(new TestLoopAdapter(mRollPagerView));
        return view;
    }
}
