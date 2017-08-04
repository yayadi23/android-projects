package com.example.entity;

import java.util.List;

/**
 * Created by Diak on 2017/2/15.
 */

public class CityEntity {
    private String id;

    private String cityEn;

    private String cityZh;

    private String countryCode;

    private String countryEn;

    private String countryZh;

    private String provinceEn;

    private String provinceZh;

    private String leaderEn;

    private String leaderZh;

    private String lat;

    private String lon;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setCityEn(String cityEn){
        this.cityEn = cityEn;
    }
    public String getCityEn(){
        return this.cityEn;
    }
    public void setCityZh(String cityZh){
        this.cityZh = cityZh;
    }
    public String getCityZh(){
        return this.cityZh;
    }
    public void setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }
    public String getCountryCode(){
        return this.countryCode;
    }
    public void setCountryEn(String countryEn){
        this.countryEn = countryEn;
    }
    public String getCountryEn(){
        return this.countryEn;
    }
    public void setCountryZh(String countryZh){
        this.countryZh = countryZh;
    }
    public String getCountryZh(){
        return this.countryZh;
    }
    public void setProvinceEn(String provinceEn){
        this.provinceEn = provinceEn;
    }
    public String getProvinceEn(){
        return this.provinceEn;
    }
    public void setProvinceZh(String provinceZh){
        this.provinceZh = provinceZh;
    }
    public String getProvinceZh(){
        return this.provinceZh;
    }
    public void setLeaderEn(String leaderEn){
        this.leaderEn = leaderEn;
    }
    public String getLeaderEn(){
        return this.leaderEn;
    }
    public void setLeaderZh(String leaderZh){
        this.leaderZh = leaderZh;
    }
    public String getLeaderZh(){
        return this.leaderZh;
    }
    public void setLat(String lat){
        this.lat = lat;
    }
    public String getLat(){
        return this.lat;
    }
    public void setLon(String lon){
        this.lon = lon;
    }
    public String getLon(){
        return this.lon;
    }
}
