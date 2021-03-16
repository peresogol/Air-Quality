package com.example.qualitair;

public class Place {

    private String placeName;
    private String longitude;
    private String latitude;
    private Boolean isFavourite;

    public Place(String placeName, String longitude, String latitude, boolean isFavourite) {
        this.placeName = placeName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.isFavourite = isFavourite;
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

    @Override
    public String toString() {
        return '(' + this.longitude + " , " + this.latitude + ')';
    }
}
