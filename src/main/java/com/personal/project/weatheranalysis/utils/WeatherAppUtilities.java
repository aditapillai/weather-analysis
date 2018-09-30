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

    public static String convertToDateString(int year, int month, int date) {
        StringBuilder finalDate = new StringBuilder();

        finalDate.append(year)
                 .append("/")
                 .append(month)
                 .append("/")
                 .append(date);
        return finalDate.toString();
    }

}
