package com.example.renaissance.mock;

import com.example.renaissance.entity.Comment;
import com.example.renaissance.renaissance.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockCommentList {
    private static Map<Long, List> commentMap;
    private List<Comment> commentList;
    private Comment comment;

    private MockCommentList(){
        commentMap = new HashMap<>();
        commentList = new ArrayList<>();
        //一个一个添加
        comment = new Comment();

        //comment 0
        comment.setComment_id(0);
        comment.setTopicid(0);
        comment.setCommenter_id(0);
        comment.setCommenter_name("Rihanna");
        comment.setCommenter_profile_image(R.drawable.rihanna);
        comment.setComment_time("7分钟前");
        comment.setComment_content("hahaha");
    }
}
