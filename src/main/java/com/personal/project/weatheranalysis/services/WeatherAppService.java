package com.personal.project.weatheranalysis.services;

import com.personal.project.weatheranalysis.models.City;
import com.personal.project.weatheranalysis.models.Weather;
import com.personal.project.weatheranalysis.utils.WeatherAppUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class WeatherAppService {

    private final ParameterizedTypeReference<List<Weather>> weatherListResponseType =
            new ParameterizedTypeReference<List<Weather>>() {
            };
    private final ParameterizedTypeReference<List<City>> cityListResponseType =
            new ParameterizedTypeReference<List<City>>() {
            };
    private RestTemplate httpClient;
    private Environment environment;
    private List<String> locations;

    public List<City> getCityDetails(String cityName) {

        ResponseEntity<List<City>> response = this.httpClient.exchange(this.environment.getProperty("meta-weather-api.cityLookup"),
                HttpMethod.GET,
                null,
                this.cityListResponseType, cityName);

        return response.getBody();
    }

    public List<City> getCityDetails() {
        return this.locations.parallelStream()
                             .map(this::getCityDetails)
                             .map(Collection::stream)
                             .flatMap(Function.identity())
                             .collect(Collectors.toList());
    }

    public List<City> populateWeatherDetails(String cityName, int year, int month, int date) {
        List<City> cityDetails = this.getCityDetails(cityName);
        cityDetails.parallelStream()
                   .map(city -> this.populateWeatherDetails(city, year, month, date))
                   .forEach(city -> city.setAverage_temperature(WeatherAppUtilities.computeAverageTemperature(city.getWeatherData())));

        return cityDetails;
    }

    public List<City> populateWeatherDetails(int year, int month, int date) {
        List<City> cityDetails = this.getCityDetails();
        cityDetails.parallelStream()
                   .map(city -> this.populateWeatherDetails(city, year, month, date))
                   .forEach(city -> city.setAverage_temperature(WeatherAppUtilities.computeAverageTemperature(city.getWeatherData())));

        return cityDetails;
    }

    private City populateWeatherDetails(City city, int year, int month, int date) {

        ResponseEntity<List<Weather>> response = this.httpClient.exchange(this.environment.getProperty(
                "meta-weather-api.cityWeather"),
                HttpMethod.GET,
                null,
                this.weatherListResponseType, city.getWoeid(), year, month, date);
        city.setWeatherData(response.getBody());
        return city;
    }

    @Autowired
    public void setHttpClient(RestTemplate httpClient) {
        this.httpClient = httpClient;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Autowired
    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

}
