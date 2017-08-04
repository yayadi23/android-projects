package com.example.renaissance.adapter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.renaissance.activity.JourneyDetailActivity;
import com.example.renaissance.entity.Journey;
import com.example.renaissance.renaissance.R;

import java.io.Serializable;

public class JourneyAdapter extends RecyclerView.Adapter<JourneyAdapter.JourneyViewHolder> implements View.OnClickListener{
    private Journey journey;
    private JourneyViewHolder journeyVH;

    private int[] profile_images = {R.drawable.ladygaga, R.drawable.taylor, R.drawable.demilovato, R.drawable.aliciakeys};
    private String[] author_names = {"Lady Gaga", "Taylor Swift", "Demi Lovato", "Alicia Keys"};
    private String[] times = {"2017.7.10 14:22","2017.7.10 14:05","2017.7.10 13:42","2017.7.10 12:18"};
    private String[] comment_counts = {"132","29","30","106"};
    private String[] image_counts = {"共6张照片","共6张照片","共6张照片","共6张照片"};
    private String[] commenter_names = {"Rihanna","Adele", "Katy Perry", "Bruno Mars"};
    private String comments = "话说楼主有在公爵公里看到拉斐尔的肖像画吗？";

    @Override
    public void onClick(View view) {
        journeyVH = (JourneyViewHolder) view.getTag();
        journey = new Journey();
        journey.setAuthor_name(journeyVH.author_name.getText().toString());
        journey.setProfile_image(profile_images[journeyVH.getLayoutPosition()]);
        journey.setTime(journeyVH.time.getText().toString());
        journey.setComment_count(journeyVH.comment_count.getText().toString());

        Intent intent = new Intent(view.getContext(), JourneyDetailActivity.class);
        intent.putExtra("journey", journey);
        view.getContext().startActivity(intent);
    }

    public static class JourneyViewHolder extends RecyclerView.ViewHolder{

                private ImageView profile_image;
                private TextView author_name;
                private TextView time;
                private TextView comment_count;
                private TextView image_count;
                private TextView commenter_name;
                private TextView comment;
                public JourneyViewHolder(View itemView) {
                    super(itemView);
                    profile_image = (ImageView) itemView.findViewById(R.id.item_profile_image1);
                    author_name = (TextView) itemView.findViewById(R.id.item_author_name1);
                    time = (TextView) itemView.findViewById(R.id.item_time1);
                    comment_count = (TextView)itemView.findViewById(R.id.item_comment_count1);
                    image_count = (TextView) itemView.findViewById(R.id.item_image_count);
                    commenter_name = (TextView) itemView.findViewById(R.id.item_commenter_name1);
                    comment = (TextView) itemView.findViewById(R.id.item_comment1);
        }
    }

    @Override
    public JourneyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_journey, parent, false);
        v.setOnClickListener(this);
        JourneyViewHolder journeyViewHolder = new JourneyViewHolder(v);
        v.setTag(journeyViewHolder);
        return journeyViewHolder;
    }

    @Override
    public void onBindViewHolder(JourneyViewHolder holder, int position) {
        holder.profile_image.setImageResource(profile_images[position]);
        holder.author_name.setText(author_names[position]);
        holder.time.setText(times[position]);
        holder.comment_count.setText(comment_counts[position]);
        holder.image_count.setText(image_counts[position]);
        holder.commenter_name.setText(commenter_names[position]);
        holder.comment.setText(comments);
    }

    @Override
    public int getItemCount() {
        return profile_images.length;
    }



}
