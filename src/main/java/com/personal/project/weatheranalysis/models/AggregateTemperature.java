package com.personal.project.weatheranalysis.models;

public class AggregateTemperature {
    private String date;
    private Double averageTemperature;

    public AggregateTemperature(String date, Double averageTemperature) {
        this.date = date;
        this.averageTemperature = averageTemperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(Double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }
}
