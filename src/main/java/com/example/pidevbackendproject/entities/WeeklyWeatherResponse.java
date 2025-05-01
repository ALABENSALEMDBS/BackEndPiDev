package com.example.pidevbackendproject.entities;

import java.util.ArrayList;
import java.util.List;

public class WeeklyWeatherResponse {

    private List<DailyWeather> dailyWeather = new ArrayList<>();

    public List<DailyWeather> getDailyWeather() {
        return dailyWeather;
    }

    public void addDailyWeather(String date, String description, double temperature) {
        this.dailyWeather.add(new DailyWeather(date, description, temperature));
    }

    public static class DailyWeather {
        private String date;
        private String description;
        private double temperature;

        public DailyWeather(String date, String description, double temperature) {
            this.date = date;
            this.description = description;
            this.temperature = temperature;
        }

        public String getDate() {
            return date;
        }

        public String getDescription() {
            return description;
        }

        public double getTemperature() {
            return temperature;
        }
    }
}