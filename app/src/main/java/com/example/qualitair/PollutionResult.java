package com.example.qualitair;

public class PollutionResult {

    private String date;
    private String hour;
    private String mainPollutant;
    private int AirQualityIndexUS;

    public String getDate() {
        return this.date;
    }

    public String getHour() {
        return this.hour;
    }

    public String getMainPollutant() {
        return this.mainPollutant;
    }

    public int getAirQualityIndexUS() {
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

    public void setAirQualityIndexUS(int airQualityIndexUS) {
        AirQualityIndexUS = airQualityIndexUS;
    }
}
