package com.weatherapp.demo.service;

import com.weatherapp.demo.model.WeatherSummary;

public interface WeatherService {
    WeatherSummary getWeatherSummary(String city);
}

