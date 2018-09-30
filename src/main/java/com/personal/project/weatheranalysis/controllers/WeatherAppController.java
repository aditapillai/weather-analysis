package com.personal.project.weatheranalysis.controllers;

import com.personal.project.weatheranalysis.models.City;
import com.personal.project.weatheranalysis.services.WeatherAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherAppController {
    private WeatherAppService service;

    @GetMapping("/cities/{city}")
    public List<City> getCityDetails(@PathVariable String city) {
        return this.service.getCityDetails(city);
    }

    @GetMapping("/cities")
    public List<City> getCityDetails() {
        return this.service.getCityDetails();
    }

    @GetMapping("/weather/cities/{city}/{year}/{month}/{date}")
    public List<City> getWeatherDetails(@PathVariable String city,
            @PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer date) {
        return this.service.populateWeatherDetails(city, year, month, date);
    }

    @GetMapping("/weather/cities/{year}/{month}/{date}")
    public List<City> getWeatherDetails(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer date) {
        return this.service.populateWeatherDetails(year, month, date);
    }

    @Autowired
    public void setService(WeatherAppService service) {
        this.service = service;
    }
}
