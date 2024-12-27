package com.weatherapp.demo.client;

import com.weatherapp.demo.model.WeatherApiResponse;
import com.weatherapp.demo.model.WeatherData;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class ExternalWeatherClient {

    private final WebClient webClient = WebClient.create();
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String API_KEY = "67223e54010e41da129f286215d65e4b";

    @Async
    public CompletableFuture<List<WeatherData>> fetchWeatherData(String city) {
        String url = String.format("%s?q=%s&APPID=%s", API_URL, city, API_KEY);
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(WeatherApiResponse.class)
                .map(WeatherApiResponse::toWeatherDataList)
                .toFuture();
    }
}

