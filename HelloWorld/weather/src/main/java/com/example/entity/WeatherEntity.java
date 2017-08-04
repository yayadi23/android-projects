package com.example.entity;

/**
 * Created by Diak on 2017/2/7.
 */

public class WeatherEntity {
    private String desc;
    private int status;
    private Data data;

    public void setDesc(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return desc;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public int getStatus(){
        return status;
    }

    public void setData(Data data){
        this.data = data;
    }

    public Data getData(){
        return data;
    }
}
