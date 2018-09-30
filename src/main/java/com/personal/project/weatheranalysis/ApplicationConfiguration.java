package com.personal.project.weatheranalysis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Configuration
public class ApplicationConfiguration {

    @Bean("httpClient")
    public RestTemplate getHttpClient() {
        return new RestTemplate();
    }

    @Bean("locations")
    public List<String> getLocations() throws IOException {

        return Files.readAllLines(ResourceUtils.getFile("classpath:store/locations.txt")
                                               .toPath());
    }
}
