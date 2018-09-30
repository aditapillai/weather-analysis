package com.personal.project.weatheranalysis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class City {
    private String title;
    private String location_type;
    private Integer woeid;
    private Integer distance;
    private String latt_long;
    private Double average_temperature;

    @JsonIgnore
    private List<Weather> weatherData;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    public Integer getWoeid() {
        return woeid;
    }

    public void setWoeid(Integer woeid) {
        this.woeid = woeid;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getLatt_long() {
        return latt_long;
    }

    public void setLatt_long(String latt_long) {
        this.latt_long = latt_long;
    }

    public Double getAverage_temperature() {
        return average_temperature;
    }

    public void setAverage_temperature(Double average_temperature) {
        this.average_temperature = average_temperature;
    }

    public void setWeatherData(List<Weather> weatherData) {
        this.weatherData = weatherData;
    }

    public List<Weather> getWeatherData() {
        return weatherData;
    }
}
