package com.example.renaissance.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.renaissance.activity.ColumnFragment;
import com.example.renaissance.activity.HomeFragment;
import com.example.renaissance.activity.LiveFragment;
import com.example.renaissance.activity.MineFragment;

/**
 * Created by Diak on 2017/7/9.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = {"首页","话题","游记","智库"};

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 1){
            return new ColumnFragment();
        } else if(position == 2){
            return new LiveFragment();
        } else if(position == 3){
            return new MineFragment();
        }
        return new HomeFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
