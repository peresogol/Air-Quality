package com.example.qualitair;

public class WeatherResult {

    private String hour;
    private String date;
    private String icon;
    private int temperature;
    private int pressure;
    private int humidity;
    private int windSpeed;
    private int windDirection;

    public String getHour() {
        return this.hour;
    }

    public String getDate() {
        return this.date;
    }

    public String getIcon() {
        return this.icon;
    }

    public int getTemperature() {
        return this.temperature;
    }

    public int getPressure() {
        return this.pressure;
    }

    public int getHumidity() {
        return this.humidity;
    }

    public int getWindSpeed() {
        return this.windSpeed;
    }

    public int getWindDirection() {
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

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }
}
