package com.example.dbtest;

/**
 * Created by Diak on 2017/2/17.
 */

public class CityEntity {
    private String id1;
    private String cityZh;
    private String leaderZh;
    private String provinceZh;

    public void setId1(String id1){
        this.id1 = id1;
    }
    public String getId1(){
        return id1;
    }

    public void setCityZh(String cityZh){
        this.cityZh = cityZh;
    }
    public String getCityZh(){
        return cityZh;
    }
    public void setLeaderZh(String leaderZh){
        this.leaderZh = leaderZh;
    }
    public String getLeaderZh(){
        return leaderZh;
    }
    public void setProvinceZh(String provinceZh){
        this.provinceZh = provinceZh;
    }
    public String getProvinceZh(){
        return provinceZh;
    }
}
