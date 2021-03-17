package com.example.qualitair;

import java.io.Serializable;

public class WeatherResult implements Serializable {

    private String hour;
    private String date;
    private String icon;
    private String temperature;
    private String pressure;
    private String humidity;
    private String windSpeed;
    private String windDirection;

    public WeatherResult(String hour, String date, String icon, String temperature, String pressure, String humidity, String windSpeed, String windDirection) {
        this.hour = hour;
        this.date = date;
        this.icon = icon;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public String getHour() {
        return this.hour;
    }

    public String getDate() {
        return this.date;
    }

    public String getIcon() {
        return this.icon;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public String getPressure() {
        return this.pressure;
    }

    public String getHumidity() {
        return this.humidity;
    }

    public String getWindSpeed() {
        return this.windSpeed;
    }

    public String getWindDirection() {
        return this.windDirection;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    @Override
    public String toString() {
        return "WeatherResult{" +
                "hour='" + this.hour + '\'' +
                ", date='" + this.date + '\'' +
                ", icon='" + this.icon + '\'' +
                ", temperature='" + this.temperature + '\'' +
                ", pressure='" + this.pressure + '\'' +
                ", humidity='" + this.humidity + '\'' +
                ", windSpeed='" + this.windSpeed + '\'' +
                ", windDirection='" + this.windDirection + '\'' +
                '}';
    }
}
