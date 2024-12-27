package com.weatherapp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class WeatherData {
    private String date;
    private double temperature;

    public static WeatherData fromApiResponse(long unixTimestamp, double temperatureKelvin) {
        // Convert Unix timestamp to a human-readable date
        String formattedDate = Instant.ofEpochSecond(unixTimestamp)
                .atZone(ZoneId.of("UTC"))
                .format(DateTimeFormatter.ISO_LOCAL_DATE);

        // Convert temperature from Kelvin to Celsius
        double temperatureCelsius = temperatureKelvin - 273.15;

        return new WeatherData(formattedDate, temperatureCelsius);
    }
}
