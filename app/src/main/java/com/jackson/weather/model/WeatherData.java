package com.jackson.weather.model;

/**
 * Created by zhishengliu on 10/24/15.
 */
public class WeatherData {
    private String tempCHi;
    private String tempFHi;
    private String tempCLo;
    private String tempFLo;
    private String date;
    private String description;
    private String location;

    public String getTempCHi() {
        return tempCHi;
    }

    public void setTempCHi(String tempCHi) {
        this.tempCHi = tempCHi;
    }

    public String getTempFHi() {
        return tempFHi;
    }

    public void setTempFHi(String tempFHi) {
        this.tempFHi = tempFHi;
    }

    public String getTempCLo() {
        return tempCLo;
    }

    public void setTempCLo(String tempCLo) {
        this.tempCLo = tempCLo;
    }

    public String getTempFLo() {
        return tempFLo;
    }

    public void setTempFLo(String tempFLo) {
        this.tempFLo = tempFLo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
