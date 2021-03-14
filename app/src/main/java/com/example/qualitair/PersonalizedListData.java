package com.example.qualitair;

public class PersonalizedListData {

    private String placeName;
    private String gpsCoordinates;
    private Boolean isFavourite;

    public PersonalizedListData(String placeName, String gpsCoordinates, boolean b) {
        this.placeName = placeName;
        this.gpsCoordinates = gpsCoordinates;
        this.isFavourite = false;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public String getGpsCoordinates() {
        return this.gpsCoordinates;
    }

    public Boolean getIsFavourite() {
        return this.isFavourite;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setGpsCoordinates(String gpsCoordinates) {
        this.gpsCoordinates = gpsCoordinates;
    }

    public void setIsFavourite(Boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    @Override
    public String toString() {
        return this.placeName + " : " + this.gpsCoordinates +
                "\n Favourite : " + this.isFavourite;
    }
}
