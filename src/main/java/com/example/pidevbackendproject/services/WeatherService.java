package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.WeatherResponse;
import lombok.AllArgsConstructor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {
    @Value("${openweathermap.api.key}")
    private String apiKey;

    public WeatherResponse getWeather(double lat, double lon) {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" +
                lat + "&lon=" + lon + "&appid=" + apiKey + "&units=metric&lang=fr";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        JSONObject json = new JSONObject(result);
        String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
        double temperature = json.getJSONObject("main").getDouble("temp");
        String city = json.getString("name");

        WeatherResponse response = new WeatherResponse();
        response.setDescription(description);
        response.setTemperature(temperature);
        response.setCity(city);
        return response;
        }
    }
