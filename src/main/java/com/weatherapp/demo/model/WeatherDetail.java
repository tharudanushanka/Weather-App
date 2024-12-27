package com.weatherapp.demo.model;

import lombok.Data;

@Data
public class WeatherDetail {
    private Main main;
    private String dt_txt;
}

