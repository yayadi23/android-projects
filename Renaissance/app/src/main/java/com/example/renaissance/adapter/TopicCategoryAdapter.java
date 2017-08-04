package com.example.renaissance.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.renaissance.activity.ArchitectureFragment;
import com.example.renaissance.activity.DrawFragment;
import com.example.renaissance.activity.FigureFragment;
import com.example.renaissance.activity.HistoryFragment;
import com.example.renaissance.activity.PhilosophyFragment;
import com.example.renaissance.activity.WarFragment;

/**
 * Created by Diak on 2017/7/15.
 */

public class TopicCategoryAdapter extends FragmentPagerAdapter {

    private String[] titles = {"人物","野史","建筑","战争","绘画","哲学"};

    public TopicCategoryAdapter (FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 1){
            return new HistoryFragment();
        } else if (position == 2){
            return new ArchitectureFragment();
        } else if(position == 3){
            return new WarFragment();
        } else if(position == 4){
            return new DrawFragment();
        } else if(position == 5){
            return new PhilosophyFragment();
        }
        return new FigureFragment();
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
