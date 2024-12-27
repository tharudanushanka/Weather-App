package com.weatherapp.demo.service.Impl;

import com.weatherapp.demo.client.ExternalWeatherClient;
import com.weatherapp.demo.model.WeatherData;
import com.weatherapp.demo.model.WeatherSummary;
import com.weatherapp.demo.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final ExternalWeatherClient externalWeatherClient;

    @Cacheable(value = "weatherCache", key = "#city", cacheManager = "cacheManager")
    public WeatherSummary getWeatherSummary(String city) {
        List<WeatherData> weatherData;
        try {
            weatherData = externalWeatherClient.fetchWeatherData(city).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error fetching weather data", e);
        }

        if (weatherData == null || weatherData.isEmpty()) {
            throw new RuntimeException("No weather data available for the city: " + city);
        }

        double avgTemp = weatherData.stream()
                .mapToDouble(WeatherData::getTemperature)
                .average()
                .orElseThrow(() -> new RuntimeException("Unable to calculate average temperature"));

        WeatherData hottestDay = weatherData.stream()
                .max(Comparator.comparingDouble(WeatherData::getTemperature))
                .orElseThrow(() -> new RuntimeException("Unable to determine the hottest day"));

        WeatherData coldestDay = weatherData.stream()
                .min(Comparator.comparingDouble(WeatherData::getTemperature))
                .orElseThrow(() -> new RuntimeException("Unable to determine the coldest day"));

        return new WeatherSummary(city, avgTemp, hottestDay.getDate(), coldestDay.getDate());
    }
}
