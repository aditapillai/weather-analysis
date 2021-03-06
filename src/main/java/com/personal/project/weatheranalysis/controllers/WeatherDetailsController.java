package com.personal.project.weatheranalysis.controllers;

import com.personal.project.weatheranalysis.models.City;
import com.personal.project.weatheranalysis.services.WeatherAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherDetailsController {
    private WeatherAppService service;

    @GetMapping("/weather/cities")
    public List<City> getWeatherDetails() {
        return this.service.getWeatherDetails();
    }

    @GetMapping("/weather/cities/{year}/{month}/{day}")
    public List<City> getWeatherDetails(@PathVariable int year, @PathVariable int month, @PathVariable int day) {
        return this.service.getWeatherDetails(year, month, day);
    }

    @GetMapping("/weather/cities/{city}/{year}/{month}/{day}")
    public List<City> getWeatherDetails(@PathVariable String city,
            @PathVariable int year, @PathVariable int month, @PathVariable int day) {
        return this.service.getWeatherDetails(city, year, month, day);
    }

    @Autowired
    public void setService(WeatherAppService service) {
        this.service = service;
    }
}
