package com.example.renaissance.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.renaissance.renaissance.R;



public class HotTopicAdapter extends RecyclerView.Adapter<HotTopicAdapter.HotTopicViewHolder> {

    // TODO: 2017/7/14 换成url请求数据
    private int[] profile_images = {R.drawable.beyonce,R.drawable.adele,R.drawable.katyperry};
    private String[] author_names = {"Beyonce","Adele", "Katy Perry"};
    private String[] times = {"2017.7.10 8:00","2017.7.10 9:00","2017.7.10 10:00"};
    private String[] comment_counts = {"999","899","799"};
    private String[] contents = {"伦勃朗在绘画史——不独是荷兰的而是全欧的绘画史上所占的地位，是与意大利文艺复兴诸巨匠不相上下的。他所代表的是北欧的民族性与民族天才。造成伦勃朗的伟大的面目的，是表现他的特殊心魂的一种特殊技术",
            "拉斐尔学习佩鲁吉诺的绘画风格时，在每一细节的模仿上都异常逼真，以至他绘制的肖像与老师的手迹如出一辙，真假难辨。如在佩鲁贾的圣弗朗西斯科教堂为曼达勒纳·德格里·奥迪夫人绘制的一幅木板画中",
            "看过《达·芬奇密码》的人大概都知道达·芬奇密码筒，按照故事情节，密码筒里藏匿着关于郇山隐修会乃至整个基督教最大秘密的莎草纸。达·芬奇设计的密码筒内有一个装着醋液的..."
    };

    private String[] commenter_names = {"Michael Jackson","Whitney Huston","Pharell Williams"};
    private String[] comments = {"这个排列组合数现在破解起来","这个排列组合数现在破解起来","这个排列组合数现在破解起来"};

    public static class HotTopicViewHolder extends RecyclerView.ViewHolder{
        public ImageView item_profile_image;
        public TextView item_author_name;
        public TextView item_time;
        public TextView fa_commenting;
        public TextView item_comment_count;
        public TextView item_image_flag;
        public TextView item_content;
        public TextView item_commenter_name;
        public TextView item_fixed_text;
        public TextView item_comment;
        public HotTopicViewHolder(View v){
            super(v);
            item_profile_image = (ImageView) v.findViewById(R.id.item_profile_image);
            item_author_name = (TextView) v.findViewById(R.id.item_author_name);
            item_time = (TextView) v.findViewById(R.id.item_time);
            fa_commenting = (TextView) v.findViewById(R.id.fa_commenting);
            item_comment_count = (TextView) v.findViewById(R.id.item_comment_count);
            item_image_flag = (TextView) v.findViewById(R.id.item_image_flag);
            item_content = (TextView) v.findViewById(R.id.item_content);
            item_commenter_name = (TextView) v.findViewById(R.id.item_commenter_name);
            item_fixed_text = (TextView) v.findViewById(R.id.item_fixed_text);
            item_comment = (TextView) v.findViewById(R.id.item_comment);
        }
    }
    
    @Override
    public HotTopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO: 2017/7/14
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic_1,parent,false);
        HotTopicViewHolder hotTopicVH = new HotTopicViewHolder(v);
        return hotTopicVH;
    }

    @Override
    public void onBindViewHolder(HotTopicViewHolder holder, int position) {
        // TODO: 2017/7/14
        holder.item_profile_image.setImageResource(profile_images[position]);
        holder.item_author_name.setText(author_names[position]);
        holder.item_time.setText(times[position]);
        holder.item_comment_count.setText(comment_counts[position]);
        holder.item_content.setText(contents[position]);
        holder.item_commenter_name.setText(commenter_names[position]);
        holder.item_comment.setText(comments[position]);
    }

    @Override
    public int getItemCount() {
        // TODO: 2017/7/14  
        return profile_images.length;
    }
}
