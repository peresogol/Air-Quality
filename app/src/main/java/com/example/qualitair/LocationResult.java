package com.example.qualitair;

import com.google.gson.JsonElement;

import java.io.Serializable;

public class LocationResult implements Serializable {

    private String city;
    private String state;
    private String country;
    private String latitude;
    private String longitude;

    public LocationResult(String city, String state, String country, String lon, String lat) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.longitude = lon;
        this.latitude = lat;
    }

    @Override
    public String toString() {
        return "LocationResult{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
