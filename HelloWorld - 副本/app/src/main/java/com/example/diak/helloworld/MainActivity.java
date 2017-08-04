package com.example.diak.helloworld;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View view1, view2, view3;
    private ViewPager viewPager;
    private List<View> viewList;
    private Button btn;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Button goTo2 = (Button) findViewById(R.id.goTo2);
        goTo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddCityAcivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                LayoutInflater layoutInflater = getLayoutInflater().from(this);
                View newView = layoutInflater.inflate(R.layout.pingdingshan,null);
                TextView textView = (TextView) newView.findViewById(R.id.tvPingdingshan);
                textView.setText("点击平顶山之后又增加的页面");
                viewList.add(newView);
                pagerAdapter.notifyDataSetChanged();
                viewPager.setCurrentItem(viewList.size()-1);
                break;
            default:
                break;
        }
    }

    public void initView(){
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        LayoutInflater layoutInflater = getLayoutInflater().from(this);
        view1 = layoutInflater.inflate(R.layout.pingdingshan,null);
        view2 = layoutInflater.inflate(R.layout.lushan,null);
        view3 = layoutInflater.inflate(R.layout.baofeng,null);

        viewList = new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }
        };
        viewPager.setAdapter(pagerAdapter);
    }
}
