package com.example.pidevbackendproject.entities;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {
    String description;
    double temperature;
    String city;
}
