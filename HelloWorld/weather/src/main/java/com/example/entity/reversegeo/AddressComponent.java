package com.example.entity.reversegeo;

/**
 * Created by Diak on 2017/8/1.
 */

public class AddressComponent {
    private String country;

    private int country_code;

    private String province;

    private String city;

    private String district;

    private String adcode;

    private String street;

    private String street_number;

    private String direction;

    private String distance;

    public void setCountry(String country){
        this.country = country;
    }
    public String getCountry(){
        return this.country;
    }
    public void setCountry_code(int country_code){
        this.country_code = country_code;
    }
    public int getCountry_code(){
        return this.country_code;
    }
    public void setProvince(String province){
        this.province = province;
    }
    public String getProvince(){
        return this.province;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return this.city;
    }
    public void setDistrict(String district){
        this.district = district;
    }
    public String getDistrict(){
        return this.district;
    }
    public void setAdcode(String adcode){
        this.adcode = adcode;
    }
    public String getAdcode(){
        return this.adcode;
    }
    public void setStreet(String street){
        this.street = street;
    }
    public String getStreet(){
        return this.street;
    }
    public void setStreet_number(String street_number){
        this.street_number = street_number;
    }
    public String getStreet_number(){
        return this.street_number;
    }
    public void setDirection(String direction){
        this.direction = direction;
    }
    public String getDirection(){
        return this.direction;
    }
    public void setDistance(String distance){
        this.distance = distance;
    }
    public String getDistance(){
        return this.distance;
    }
}
