package com.example.entity.reversegeo;

/**
 * Created by Diak on 2017/8/1.
 */

public class Location {
    private double lng;

    private double lat;

    public void setLng(double lng){
        this.lng = lng;
    }
    public double getLng(){
        return this.lng;
    }
    public void setLat(double lat){
        this.lat = lat;
    }
    public double getLat(){
        return this.lat;
    }
}
