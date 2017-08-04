package com.example.renaissance.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.renaissance.renaissance.R;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

/**
 * Created by Diak on 2017/7/9.
 */

public class TestLoopAdapter extends LoopPagerAdapter {
    private int[] images = { R.drawable.florence1,R.drawable.florence2,R.drawable.florence3,R.drawable.florence4};
    private String[] titles = {"500年后的佛罗伦萨\n ——florence——","邂逅","500年前的风景",""};

    public TestLoopAdapter(RollPagerView viewPager) {
        super(viewPager);
    }

    @Override
    public View getView(ViewGroup container, int position) {
        //如何将图片和文字组合在一起呢？
//        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_loop_page,container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.loop_image);
        TextView textView = (TextView) view.findViewById(R.id.loop_text);
        imageView.setImageResource(images[position]);
        textView.setText(titles[position]);
        return view;

//        ImageView imageView = new ImageView(container.getContext());
//        imageView.setImageResource(images[position]);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        TextView textView = new TextView(container.getContext());
//        textView.setText(titles[position]);
//        textView.setTextColor(Color.WHITE);
//        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        return imageView;
    }

    @Override
    public int getRealCount() {
        return images.length;
    }
}
