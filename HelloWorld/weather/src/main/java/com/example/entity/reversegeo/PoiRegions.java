package com.example.entity.reversegeo;

/**
 * Created by Diak on 2017/8/1.
 */

public class PoiRegions {
    private String direction_desc;

    private String name;

    private String tag;

    public void setDirection_desc(String direction_desc){
        this.direction_desc = direction_desc;
    }
    public String getDirection_desc(){
        return this.direction_desc;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setTag(String tag){
        this.tag = tag;
    }
    public String getTag(){
        return this.tag;
    }
}
