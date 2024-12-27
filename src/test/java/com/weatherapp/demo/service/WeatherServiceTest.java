package com.weatherapp.demo.service;



import com.weatherapp.demo.client.ExternalWeatherClient;
import com.weatherapp.demo.model.WeatherData;
import com.weatherapp.demo.model.WeatherSummary;
import com.weatherapp.demo.service.Impl.WeatherServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@SpringBootTest
public class WeatherServiceTest {

    @MockBean
    private ExternalWeatherClient externalWeatherClient;

    @Autowired
    private WeatherServiceImpl weatherService;

    @Test
    public void testGetWeatherSummary() {
        List<WeatherData> data = Arrays.asList(
                new WeatherData("2024-11-18", 10.0),
                new WeatherData("2024-11-20", 20.0)
        );

        Mockito.when(externalWeatherClient.fetchWeatherData("London"))
                .thenReturn(CompletableFuture.completedFuture(data));

        WeatherSummary summary = weatherService.getWeatherSummary("London");

        Assertions.assertEquals(15.0, summary.getAverageTemperature());
        Assertions.assertEquals("2024-11-20", summary.getHottestDay());
        Assertions.assertEquals("2024-11-18", summary.getColdestDay());
    }
}
