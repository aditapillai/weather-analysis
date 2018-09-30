package com.personal.project.weatheranalysis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Bean("httpClient")
    public RestTemplate getHttpClient() {
        return new RestTemplate();
    }
}
