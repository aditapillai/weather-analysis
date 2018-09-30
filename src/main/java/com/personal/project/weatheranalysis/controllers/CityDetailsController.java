package com.personal.project.weatheranalysis.controllers;

import com.personal.project.weatheranalysis.models.City;
import com.personal.project.weatheranalysis.services.WeatherAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityDetailsController {
    private WeatherAppService service;

    @GetMapping("/cities")
    public List<City> getCityDetails() {
        return this.service.getCityDetails();
    }

    @GetMapping("/cities/{city}")
    public List<City> getCityDetails(@PathVariable String city) {
        return this.service.getCityDetails(city);
    }

    @Autowired
    public void setService(WeatherAppService service) {
        this.service = service;
    }
}
