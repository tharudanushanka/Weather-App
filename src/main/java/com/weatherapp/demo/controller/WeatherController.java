package com.weatherapp.demo.controller;

import com.weatherapp.demo.model.WeatherSummary;
import com.weatherapp.demo.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<WeatherSummary> getWeather(@RequestParam String city) {
        return ResponseEntity.ok(weatherService.getWeatherSummary(city));
    }
}
