package com.example.renaissance.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renaissance.adapter.HotTopicAdapter;
import com.example.renaissance.adapter.TestLoopAdapter;
import com.example.renaissance.net.OkHttpUtil;
import com.example.renaissance.renaissance.R;
import com.example.renaissance.widget.CustomLinearLayoutManager;
import com.jude.rollviewpager.RollPagerView;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class HomeFragment extends Fragment {
    private  RollPagerView mRollPagerView;
    private Request request;
    private Response response;
    private RecyclerView mRecyclerView;
//    private String url = "http://10.0.2.2:3000/users?name=zhangyadi";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRollPagerView = (RollPagerView) view.findViewById(R.id.rollPagerView);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mRollPagerView.setAdapter(new TestLoopAdapter(mRollPagerView));
        mRecyclerView = (RecyclerView) view.findViewById(R.id.homeRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new HotTopicAdapter());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);


//        request = new Request.Builder().url(url).build();

//        OkHttpUtil.enqueue(request, new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                Log.e("request failure", e.getMessage());
//            }
//
//            @Override
//            public void onResponse(final Response response) throws IOException {
//                if(getActivity() != null){
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            //do nothing yet
//                        }
//                    });
//                }
//            }
//        });


        return view;
    }
}
