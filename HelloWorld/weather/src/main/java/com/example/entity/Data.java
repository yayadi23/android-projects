package com.example.entity;

import java.util.List;

/**
 * Created by Diak on 2017/2/7.
 */

public class Data {
    private String wendu;
    private String ganmao;
    private List<Forecast> forecast;
    private Yesterday yesterday;
    private String aqi;
    private String city;

    public void setWendu(String wendu){
        this.wendu = wendu;
    }

    public String getWendu(){
        return wendu;
    }

    public void setGanmao(String ganmao){
        this.ganmao = ganmao;
    }

    public String getGanmao(){
        return ganmao;
    }

    public void setForecast(List<Forecast> forecast){
        this.forecast = forecast;
    }

    public List<Forecast> getForecast(){
        return forecast;
    }

    public void setYesterday(Yesterday yesterday){
        this.yesterday = yesterday;
    }

    public Yesterday getYesterday(){
        return yesterday;
    }

    public void setAqi(String aqi){
        this.aqi = aqi;
    }

    public String getAqi(){
        return aqi;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getCity(){
        return city;
    }
}
