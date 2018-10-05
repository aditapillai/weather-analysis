# weather-analysis
REST based application to provide basic analysis of the weather for various cities

This repository would contain the code to satisfy the following requirements:
    
    1: Fetch woeid for a specific city.
    2: Compute the average temperature of a day on a specified date.

The application itself is written using spring-boot and has the following endpoints:
    
    1: /cities : to fetch details(woeid) for all the cities(mentioned below).
    2: /cities/{city} : to fetch the details for a specific city.
    3: /weather/cities : to fetch the average temperature for all the cities on all the dates.
    4: /weather/cities/{year}/{month}/{day} : to fetch the average temperature of all the cities on a specified date.
    5: /weather/cities/{city}/{year}/{month}/{day} : to fetch the average temperature of a specified city on a specified date.

The default cities and dates are picked up from resources/store/locations.txt and resources/store/dates.txt respectively when the application boots.

It's deployed to https://personal-weather-analysis.herokuapp.com
