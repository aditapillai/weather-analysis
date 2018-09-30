package com.personal.project.weatheranalysis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class City {
    private String title;
    private Integer woeid;
    @JsonIgnore
    private List<Weather> weatherData;
    private List<AggregateTemperature> temperatureData;

    public City() {
        this.temperatureData = Collections.synchronizedList(new ArrayList<>());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getWoeid() {
        return woeid;
    }

    public void setWoeid(Integer woeid) {
        this.woeid = woeid;
    }

    public List<Weather> getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(List<Weather> weatherData) {
        this.weatherData = weatherData;
    }

    public List<AggregateTemperature> getTemperatureData() {
        return temperatureData;
    }
}
