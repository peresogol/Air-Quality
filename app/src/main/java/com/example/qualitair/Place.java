package com.example.qualitair;

import java.io.Serializable;

public class Place implements Serializable {

    private String city;
    private String state;
    private String country;
    private String placeName;
    private String longitude;
    private String latitude;
    private Boolean isFavourite;

    public Place(String city, String state, String country, String placeName, String longitude, String latitude, Boolean isFavourite) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.placeName = placeName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.isFavourite = isFavourite;
    }

    public Place(String placeName, String longitude, String latitude, Boolean isFavourite) {
        this.placeName = placeName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.isFavourite = isFavourite;
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

    public String getPlaceName() {
        return this.placeName;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public Boolean getIsFavourite() {
        return this.isFavourite;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setIsFavourite(Boolean isFavourite) {
        this.isFavourite = isFavourite;
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

    @Override
    public String toString() {
        return '(' + this.longitude + " , " + this.latitude + ')';
    }


    public String afficher() {
        return "Place{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", placeName='" + placeName + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", isFavourite=" + isFavourite +
                '}';
    }
}
