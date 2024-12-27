package com.weatherapp.demo.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class WeatherApiResponse {
    private List<WeatherDetail> list;

    public List<WeatherData> toWeatherDataList() {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        return list.stream()
                .map(detail -> new WeatherData(
                        detail.getDt_txt(),
                        detail.getMain().getTemp() - 273.15))
                .collect(Collectors.toList());
    }
}
