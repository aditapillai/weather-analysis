package com.personal.project.weatheranalysis.services;

import com.personal.project.weatheranalysis.models.AggregateTemperature;
import com.personal.project.weatheranalysis.models.City;
import com.personal.project.weatheranalysis.models.Date;
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
    private List<Date> dates;

    public List<City> getCityDetails() {
        return this.locations.parallelStream()
                             .map(this::getCityDetails)
                             .map(Collection::stream)
                             .flatMap(Function.identity())
                             .collect(Collectors.toList());
    }

    public List<City> getCityDetails(String cityName) {

        ResponseEntity<List<City>> response = this.httpClient.exchange(this.environment.getProperty("meta-weather-api.cityLookup"),
                HttpMethod.GET,
                null,
                this.cityListResponseType, cityName);

        return response.getBody();
    }

    public List<City> getWeatherDetails() {
        return this.locations.parallelStream()
                             .map(this::getCityDetails)
                             .map(Collection::stream)
                             .flatMap(Function.identity())
                             .map(this::getWeatherDetails)
                             .collect(Collectors.toList());
    }

    private City getWeatherDetails(City city) {
        this.dates.forEach(date -> this.getWeatherDetails(city, date.getYear(), date.getMonth(), date.getDay()));
        return city;
    }

    public List<City> getWeatherDetails(int year, int month, int day) {
        String cityName = null;
        return this.getWeatherDetails(cityName, year, month, day);
    }

    public List<City> getWeatherDetails(String cityName, int year, int month, int day) {
        if (cityName == null) {
            return this.locations.parallelStream()
                          .map(this::getCityDetails)
                          .map(Collection::stream)
                          .flatMap(Function.identity())
                          .map(city -> this.getWeatherDetails(city, year, month, day))
                          .collect(Collectors.toList());
        } else {
           return this.getCityDetails(cityName)
                .parallelStream()
                .map(city -> this.getWeatherDetails(city, year, month, day))
                .collect(Collectors.toList());
        }
    }

    private City getWeatherDetails(City city, int year, int month, int day) {

        ResponseEntity<List<Weather>> response = this.httpClient.exchange(this.environment.getProperty(
                "meta-weather-api.cityWeather"),
                HttpMethod.GET,
                null,
                this.weatherListResponseType, city.getWoeid(), year, month, day);
        city.setWeatherData(response.getBody());
        city.getTemperatureData()
            .add(new AggregateTemperature(WeatherAppUtilities.convertToDateString(year, month, day),
                    WeatherAppUtilities.computeAverageTemperature(city.getWeatherData())));
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

    @Autowired
    public void setDates(List<Date> dates) {
        this.dates = dates;
    }
}
