package com.personal.project.weatheranalysis;

import com.personal.project.weatheranalysis.models.Date;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

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

    @Bean("dates")
    public List<Date> getDates() throws IOException {
        return Files.lines(ResourceUtils.getFile("classpath:store/dates.txt")
                                        .toPath())
                    .map(line -> line.split("\\s"))
                    .map(splitLint -> new Date(Integer.parseInt(splitLint[0]),
                            Integer.parseInt(splitLint[1]),
                            Integer.parseInt(splitLint[2])))
                    .collect(Collectors.toList());
    }
}
