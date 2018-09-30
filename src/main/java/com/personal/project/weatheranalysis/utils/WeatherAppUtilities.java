package com.personal.project.weatheranalysis.utils;

import com.personal.project.weatheranalysis.models.Weather;

import java.util.List;

/**
 * Utility class to hold all the commonly accessed methods.
 */
public class WeatherAppUtilities {
    /**
     * Computes the average of the temperature from the List provided
     * @param weatherData from which average temperature is to be calculated
     * @return average temperature
     */
    public static Double computeAverageTemperature(List<Weather> weatherData) {
        return weatherData.parallelStream()
                          .mapToDouble(Weather::getThe_temp)
                          .average()
                          .orElse(Double.NaN);
    }

    /**
     * Converts year, month and day to a String format
     * @param year
     * @param month
     * @param day
     * @return formatted day
     */
    public static String convertToDateString(int year, int month, int day) {
        StringBuilder finalDate = new StringBuilder();

        finalDate.append(year)
                 .append("/")
                 .append(month)
                 .append("/")
                 .append(day);
        return finalDate.toString();
    }

}
