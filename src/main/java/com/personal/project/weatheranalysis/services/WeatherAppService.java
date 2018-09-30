package com.personal.project.weatheranalysis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherAppService {

    private RestTemplate httpClient;
    private Environment environment;

    public String getWeatherDetails(String city) {

        ResponseEntity<String> response = this.httpClient.getForEntity(this.environment.getProperty("meta-weather-api.cityLookup"),
                String.class, city);
        return response.getBody();
    }

    @Autowired
    public void setHttpClient(RestTemplate httpClient) {
        this.httpClient = httpClient;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
