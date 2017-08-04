package com.example.renaissance.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.renaissance.activity.TopicDetailActivity;
import com.example.renaissance.activity.TopicDetailFragment;
import com.example.renaissance.entity.Topic;
import com.example.renaissance.mock.MockContentList;
import com.example.renaissance.renaissance.R;
import com.example.renaissance.utils.CallBack;


public class FigureAdapter extends RecyclerView.Adapter<FigureAdapter.FigureViewHolder> implements View.OnClickListener{
    private View itemView;
    public Topic topic;

    private int[] topicId = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    private int[] profile_images = {R.drawable.rihanna,R.drawable.adele,R.drawable.katyperry,R.drawable.demilovato,R.drawable.beyonce, R.drawable.brunomars,
            R.drawable.taylor,R.drawable.ladygaga,R.drawable.jessiej,R.drawable.mileycyrus,R.drawable.kellyclarkson,
            R.drawable.whitney};
    private String[] author_names = {"Rihanna","Adele", "Katy Perry", "Demi Lovato", "Beyonce", "Bruno Mars",
            "Taylor Swift","Lady Gaga","Jessie J","Miley Cyrus","Kelly Clarkson","Whitney Huston"};
    private String[] times = {"2017.7.10 14:22","2017.7.10 14:05","2017.7.10 13:42","2017.7.10 12:18","2017.7.10 11:36",
            "2017.7.10 11:22","2017.7.10 10:47","2017.7.10 10:33","2017.7.10 9:51","2017.7.10 8:40","2017.7.10 8:03","2017.7.10 7:58"};
    private String[] comment_counts = {"132","29","30","106","280","366","126","322","295","120","496","39"};
    private String[] contents = {"看过《达·芬奇密码》的人大概都知道达·芬奇密码筒。按照故事情节，密码筒里藏匿着关于郇山隐修会乃至整个基督教最大秘密的莎草纸。达·芬奇设计的密码筒内有一个装着醋液的容器,",
            "在文艺复兴这么一个讲究容貌漂亮和仪容的时代,米开朗基罗是个最不受人喜欢的人物.他中等身材,双肩宽阔,躯体瘦削,头大,眉高,两耳突出面颊,脸孔长而忧郁,鼻子低扁,眼睛虽锐利却很小.可以说,他的长相非常糟糕,不讨人喜欢.",
            "10岁的乔托(1270-1337年,意大利文艺复兴时期的大画家)是韦斯皮尼亚诺村里有名的聪明孩子,他能说会道,什么东西都一学就会,在他的字典里, 似乎从来没有“不懂”二字。他尤其爱好的是画画儿。",
            "米开朗基罗的脾气也和他的雕塑与画作一样出名。当年轻英俊的拉斐尔第一次出现在罗马的舞台上，并迅速受到教皇利奥十世的任用时，米开朗基罗就把他视作了一个劲敌。之后，米开朗基罗还一再指责拉斐尔抄袭剽窃。",
            "16 世纪晚期，科学社会形成后，重大的科学竞争开始崭露头角。早期爆发的竞争中最知名的或许还要数牛顿和莱布尼茨之间的激烈之争。这两位伟人都声称自己是第一个创立微积分的人——如今公认的看法是，他们两人是各自独立创立了微积分。",
            "拉斐尔学习佩鲁吉诺的绘画风格时，在每一细节的模仿上都异常逼真，以至他绘制的肖像与老师的手迹如出一辙，真假难辨。如在佩鲁贾的圣弗朗西斯科教堂为曼达勒纳·德格里·奥迪夫人绘制的一幅木板画中",
            "伦勃朗在绘画史——不独是荷兰的而是全欧的绘画史上所占的地位，是与意大利文艺复兴诸巨匠不相上下的。他所代表的是北欧的民族性与民族天才。造成伦勃朗的伟大的面目的，是表现他的特殊心魂的一种特殊技术",
            "在文艺复兴这么一个讲究容貌漂亮和仪容的时代,米开朗基罗是个最不受人喜欢的人物.他中等身材,双肩宽阔,躯体瘦削,头大,眉高,两耳突出面颊,脸孔长而忧郁,鼻子低扁,眼睛虽锐利却很小.可以说,他的长相非常糟糕,不讨人喜欢.",
            "米开朗基罗的脾气也和他的雕塑与画作一样出名。当年轻英俊的拉斐尔第一次出现在罗马的舞台上，并迅速受到教皇利奥十世的任用时，米开朗基罗就把他视作了一个劲敌。之后，米开朗基罗还一再指责拉斐尔抄袭剽窃。",
            "拉斐尔学习佩鲁吉诺的绘画风格时，在每一细节的模仿上都异常逼真，以至他绘制的肖像与老师的手迹如出一辙，真假难辨。如在佩鲁贾的圣弗朗西斯科教堂为曼达勒纳·德格里·奥迪夫人绘制的一幅木板画中",
            "伦勃朗在绘画史——不独是荷兰的而是全欧的绘画史上所占的地位，是与意大利文艺复兴诸巨匠不相上下的。他所代表的是北欧的民族性与民族天才。造成伦勃朗的伟大的面目的，是表现他的特殊心魂的一种特殊技术",
            "乔托(1270-1337年,意大利文艺复兴时期的大画家)是韦斯皮尼亚诺村里有名的聪明孩子,他能说会道,什么东西都一学就会,在他的字典里, 似乎从来没有“不懂”二字。他尤其爱好的是画画儿。"};

    private String[] commenter_names = {"Michael Jackson","Whitney Huston","Pharell Williams","Bruno Mars","Jessie J","Rihanna","Justin Bieber","Christina","Selena Gomez","Gwen Stefani","Blake Shelton","Ed Sheeran"};
    private String[] comments = {"这个排列组合数现在破解起来容易吗",
            "在罗曼罗兰写的《名人传》中对他的描述是比较丑的",
            "励志故事，但不一定是真的",
            "如果他们没有在公众面前发生争端的话",
            "不精通绘画风格的人都会误认为这是佩鲁吉诺的手迹",
            "伦勃朗的明暗和文艺复兴期意大利作家的明暗是有着截然不同的意义的",
            "卢浮宫中藏有两幅被认为代表作的画《木匠家庭》",
            "牛顿和莱布尼茨的长期斗争引发了英国和欧洲数学界之间的裂痕",
            "这些阴谋诡计的存在，这两位艺术巨匠之间的竞争",
            "这可没什么好惊讶的。米开朗基罗的脾气也和他的雕塑与画作一样出名",
            "23 岁的吉贝尔蒂打败了比他更有名的",
            "要是他生在佛罗伦萨 城里,而不是生在这个小村庄,"};

    private FigureViewHolder figureViewHolder;


    public static class FigureViewHolder extends RecyclerView.ViewHolder {
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


        public FigureViewHolder(View v){
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



//        public void setResult(CallBack callBack){
//            if(topic != null){
//                callBack.getTopicDetail(topic);
//            } else {
//                System.out.println("topic is null!");
//            }
//        }

    }

    @Override
    public FigureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic_1,parent,false);
        itemView.setOnClickListener(this);
        figureViewHolder = new FigureViewHolder(itemView);
        itemView.setTag(figureViewHolder);
        return figureViewHolder;
    }

    @Override
    public void onBindViewHolder(FigureViewHolder holder, int position) {
        holder.item_profile_image.setImageResource(profile_images[position]);
        holder.item_author_name.setText(author_names[position]);
        holder.item_time.setText(times[position]);
        holder.item_comment_count.setText(comment_counts[position]);
        holder.item_content.setText(contents[position]);
        holder.item_commenter_name.setText(commenter_names[position]);
        holder.item_comment.setText(comments[position]);
    }

    @Override
    public void onClick(View view) {
        FigureViewHolder vh = (FigureViewHolder) view.getTag();
        topic = new Topic();
        topic.setProfile_image(profile_images[vh.getLayoutPosition()]);
        Log.d("item_profile_image",String.valueOf(profile_images[vh.getLayoutPosition()]));

        topic.setAuthor_name(vh.item_author_name.getText().toString());
        Log.d("author_name",vh.item_author_name.getText().toString());
        topic.setTime(vh.item_time.getText().toString());
        Log.d("item_time",vh.item_time.getText().toString());

        topic.setComment_count(vh.item_comment_count.getText().toString());
        Log.d("item_comment_count",vh.item_comment_count.getText().toString());

        topic.setId(vh.getLayoutPosition());
        Log.d("clickposition", String.valueOf(vh.getLayoutPosition()));
        topic.setContent_detail(MockContentList.getContent(vh.getLayoutPosition()));
//        Activity mainActivity = (Activity)view.getContext();
//        Log.d("FigureAdapter",mainActivity.toString());
//        android.app.FragmentManager fm = mainActivity.getFragmentManager();
        Intent intent = new Intent(view.getContext(), TopicDetailActivity.class);
        intent.putExtra("topic",topic);
        view.getContext().startActivity(intent);
        //获取vh的数据，然后传递给activity
        //使用回调方法。当前fragment的item被选中后通知activity，然后activity启动另一个fragment，通知这个fragment显示相应的内容。
    }

    @Override
    public int getItemCount() {
        return profile_images.length;
    }

    public FigureViewHolder getFigureViewHolder(){
        return figureViewHolder;
    }

    public View getItemView() {
        return itemView;
    }
}
