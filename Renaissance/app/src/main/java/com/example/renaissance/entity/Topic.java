package com.example.renaissance.entity;

import java.io.Serializable;

public class Topic implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private int profile_image;
    private String author_name;
    private String time;
    private String comment_count;
    private int image_flag;
    private String content_detail;

    public String getContent_detail() {
        return content_detail;
    }

    public void setContent_detail(String content_detail) {
        this.content_detail = content_detail;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(int profile_image) {
        this.profile_image = profile_image;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public int getImage_flag() {
        return image_flag;
    }

    public void setImage_flag(int image_flag) {
        this.image_flag = image_flag;
    }
}
