package com.example;

public class Geo { 
    private String name;
    private Object local_names;
    private Double lat;
    private Double lon;
    private String country;
    private String state;

    public Geo() {}  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getLocal_names() {
        return local_names;
    }

    public void setLocal_names(Object local_names) {
        this.local_names = local_names;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
