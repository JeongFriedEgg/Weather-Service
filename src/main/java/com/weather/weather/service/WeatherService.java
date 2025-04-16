package com.weather.weather.service;

import com.weather.weather.domain.DateWeather;
import com.weather.weather.infrastructure.client.OpenWeatherMapClient;
import com.weather.weather.repository.DateWeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final OpenWeatherMapClient openWeatherMapClient;
    private final DateWeatherRepository dateWeatherRepository;

    public DateWeather getDateWeather(LocalDate date) {
        List<DateWeather> dateWeatherListFromDB = dateWeatherRepository.findAllByDate(date);
        if (dateWeatherListFromDB.isEmpty()) {
            return openWeatherMapClient.getWeatherFromApi();
        } else {
            return dateWeatherListFromDB.get(0);
        }
    }
}