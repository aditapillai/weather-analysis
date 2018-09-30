package com.personal.project.weatheranalysis.utils;

import com.personal.project.weatheranalysis.models.Weather;

import java.util.List;

public class WeatherAppUtilities {

    public static Double computeAverageTemperature(List<Weather> weatherData) {
        return weatherData.parallelStream()
                          .mapToDouble(Weather::getThe_temp)
                          .average()
                          .orElse(Double.NaN);
    }
}
