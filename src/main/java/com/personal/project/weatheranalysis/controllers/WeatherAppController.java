package com.personal.project.weatheranalysis.controllers;

import com.personal.project.weatheranalysis.services.WeatherAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
public class WeatherAppController {
    private WeatherAppService service;

    @GetMapping("/cities/{city}")
    public String getWeatherDetails(@NotNull @PathVariable String city) {
        return this.service.getWeatherDetails(city);
    }

    @Autowired
    public void setService(WeatherAppService service) {
        this.service = service;
    }
}
