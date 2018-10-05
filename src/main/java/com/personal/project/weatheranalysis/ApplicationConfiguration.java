package com.personal.project.weatheranalysis;

import com.personal.project.weatheranalysis.models.Date;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Configurations to be used across the application
 */
@Configuration
public class ApplicationConfiguration {
    /**
     * Http client for all of the REST calls
     * @return a bean of RestTemplate
     */
    @Bean("httpClient")
    public RestTemplate getHttpClient() {
        return new RestTemplate();
    }

    /**
     * List of locations which are read from locations.txt
     * These locations would be used if no specific city name is provided
     * @return List of locations
     * @throws IOException if there is an error while reading the file
     */
    @Bean("locations")
    public List<String> getLocations() throws IOException {

        return new BufferedReader(new InputStreamReader(new ClassPathResource("store/locations.txt")
                .getInputStream()))
                .lines()
                .collect(Collectors.toList());
    }

    /**
     * List of dates which are read from dates.txt
     * These dates would be used if no specific date is provided.
     * @return List of dates
     * @throws IOException if there is an error while reading the file
     */
    @Bean("dates")
    public List<Date> getDates() throws IOException {

        return new BufferedReader(new InputStreamReader(new ClassPathResource("store/dates.txt")
                .getInputStream()))
                .lines()
                .map(line -> line.split("\\s"))
                .map(splitLint -> new Date(Integer.parseInt(splitLint[0]),
                        Integer.parseInt(splitLint[1]),
                        Integer.parseInt(splitLint[2])))
                .collect(Collectors.toList());
    }
}
