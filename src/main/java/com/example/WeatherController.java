package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final String WEATHER_API  = "https://api.openweathermap.org/data/2.5/weather";
    private final String GEO_API     = "http://api.openweathermap.org/geo/1.0/direct";  
    private final String API_KEY      = "5a7876a58dcdee34f7cfd242e8126b33";

    
    
    
    @GetMapping()
    public String getMethodName(@RequestParam String latitude, @RequestParam String longitude) {    
        RestTemplate restTemplate = new RestTemplate();
        String url = WEATHER_API + "?lat=" + latitude + "&lon=" + longitude + "&units=metric" + "&appid=" + API_KEY; 
        String result = restTemplate.getForObject(url, String.class);
        if (result != null) {
            return result;
        }
        
        return "Error retrieving weather data";
    }

    
    @GetMapping("/{city}")
    public String getCity(@PathVariable String city){ 
        try {
            RestTemplate restTemplate = new RestTemplate();
            String geoUrl = GEO_API + "?q=" + city + "&limit=1&appid=" + API_KEY;
            Geo[] geoResponse = restTemplate.getForObject(geoUrl, Geo[].class);

            if (geoResponse == null || geoResponse.length == 0) {
                return "City not found: " + city;
            }

            String url = WEATHER_API + "?lat=" + geoResponse[0].getLat() + "&lon=" + geoResponse[0].getLon() + "&units=metric&appid=" + API_KEY; 

            Weather weatherResponse = restTemplate.getForObject(url, Weather.class);
        
            if (weatherResponse != null && weatherResponse.getMain() != null) {

                return weatherResponse.getName() + ": " + weatherResponse.getMain().getTemp() + "°C";
        }

            return "Error retrieving weather data for " + city;
        
            } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
}

 