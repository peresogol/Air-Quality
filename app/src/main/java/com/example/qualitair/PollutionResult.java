package com.example.qualitair;

import java.io.Serializable;

public class PollutionResult implements Serializable {

    private String date;
    private String hour;
    private String mainPollutant;
    private String AirQualityIndexUS;

    public PollutionResult(String date, String hour, String mainPollutant, String airQualityIndexUS) {
        this.date = date;
        this.hour = hour;
        this.mainPollutant = mainPollutant;
        AirQualityIndexUS = airQualityIndexUS;
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
        return this.AirQualityIndexUS;
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
        AirQualityIndexUS = airQualityIndexUS;
    }

    @Override
    public String toString() {
        return "PollutionResult{" +
                "date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                ", mainPollutant='" + mainPollutant + '\'' +
                ", AirQualityIndexUS='" + AirQualityIndexUS + '\'' +
                '}';
    }
}
