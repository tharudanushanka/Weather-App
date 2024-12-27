package com.weatherapp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherSummary {
    private String city;
    private double averageTemperature;
    private String hottestDay;
    private String coldestDay;
}

