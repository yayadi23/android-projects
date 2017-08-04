package com.example.renaissance.entity;

import java.io.Serializable;

public class Comment implements Serializable {

    private static final long serialVersionUID = 3L;
    private long comment_id;
    private long topicid;
    private long commenter_id;
    private long parent_comment_id;
    private String commenter_name;//commenter id更符合数据库规范
    private int commenter_profile_image;
    private String comment_time;
    private String comment_content;

    public long getComment_id() {
            return comment_id;
        }

    public void setComment_id(long comment_id) {
        this.comment_id = comment_id;
    }

    public long getTopicid() {
        return topicid;
    }

    public void setTopicid(long topicid) {
        this.topicid = topicid;
    }

    public long getCommenter_id() {
        return commenter_id;
    }

    public void setCommenter_id(long commenter_id) {
        this.commenter_id = commenter_id;
    }

    public long getParent_comment_id() {
        return parent_comment_id;
    }

    public void setParent_comment_id(long parent_comment_id) {
        this.parent_comment_id = parent_comment_id;
    }

    public String getCommenter_name() {
        return commenter_name;
    }

    public void setCommenter_name(String commenter_name) {
        this.commenter_name = commenter_name;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public int getCommenter_profile_image() {
        return commenter_profile_image;
    }

    public void setCommenter_profile_image(int commenter_profile_image) {
        this.commenter_profile_image = commenter_profile_image;
    }
}
