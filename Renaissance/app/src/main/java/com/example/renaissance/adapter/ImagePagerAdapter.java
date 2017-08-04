//package com.example.renaissance.adapter;
//
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.renaissance.renaissance.R;
//
//import java.util.List;
//
//public class ImagePagerAdapter extends PagerAdapter {
//    private List<View> views;
//    private ImageView imageView;
//    private TextView textView;
//    private int[] imagesId = {R.drawable.wubiernuo2,R.drawable.wubiernuo3,R.drawable.wubiernuo4, R.drawable.wubiernuo5};
//
//    public ImagePagerAdapter(List<View> views){
//        this.views = views;
//    }
//
//    @Override
//    public int getCount() {
//        return views.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {//这个container就是llcontainer
//        View currentView = views.get(position);
//        imageView = (ImageView) currentView.findViewById(R.id.pager_image);
//        textView = (TextView) currentView.findViewById(R.id.position);
//        imageView.setImageResource(imagesId[position]);
//        textView.setText("#" + position);
//        container.addView(currentView);
//        return views.get(position);
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(views.get(position));
//    }
//}
