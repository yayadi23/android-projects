package com.example.renaissance.renaissance;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

/**
 * Created by Diak on 2017/7/9.
 */

public class TestLoopAdapter extends LoopPagerAdapter {
    private int[] images = {R.drawable.florence1,R.drawable.florence2,R.drawable.florence3,R.drawable.florence4,R.drawable.florence5,R.drawable.florence6};
    private String[] titles = {"500年后的佛罗伦萨\n ——florence——","500年后的佛罗伦萨","500年后的佛罗伦萨","500年后的佛罗伦萨","500年后的佛罗伦萨","500年后的佛罗伦萨"};

    public TestLoopAdapter(RollPagerView viewPager) {
        super(viewPager);
    }

    @Override
    public View getView(ViewGroup container, int position) {
        //如何将图片和文字组合在一起呢？
//        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ImageView imageView = new ImageView(container.getContext());
        imageView.setImageResource(images[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        TextView textView = new TextView(container.getContext());
//        textView.setText(titles[position]);
//        textView.setTextColor(Color.WHITE);
//        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return imageView;
    }

    @Override
    public int getRealCount() {
        return images.length;
    }
}
