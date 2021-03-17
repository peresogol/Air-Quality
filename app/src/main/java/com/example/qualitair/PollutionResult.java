package com.example.qualitair;

import java.io.Serializable;

public class PollutionResult implements Serializable {

    private String date;
    private String hour;
    private String mainPollutant;
    private String airQualityIndexUS;

    public PollutionResult(String hour, String date, String mainPollutant, String airQualityIndexUS) {
        this.hour = hour;
        this.date = date;
        this.mainPollutant = mainPollutant;
        this.airQualityIndexUS = airQualityIndexUS;
    }

    public String getDate() {
        return this.date;
    }

    public String getHour() {
        return this.hour;
    }

    public String getMainPollutant() {
        return this.mainPollutant;
    }

    public String getAirQualityIndexUS() {
        return this.airQualityIndexUS;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setMainPollutant(String mainPollutant) {
        this.mainPollutant = mainPollutant;
    }

    public void setAirQualityIndexUS(String airQualityIndexUS) {
        this.airQualityIndexUS = airQualityIndexUS;
    }

    @Override
    public String toString() {
        return "PollutionResult{" +
                "date='" + this.date + '\'' +
                ", hour='" + this.hour + '\'' +
                ", mainPollutant='" + this.mainPollutant + '\'' +
                ", AirQualityIndexUS='" + this.airQualityIndexUS + '\'' +
                '}';
    }
}
