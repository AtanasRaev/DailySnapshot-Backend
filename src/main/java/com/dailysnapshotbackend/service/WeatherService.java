package com.dailysnapshotbackend.service;

import com.dailysnapshotbackend.dto.WeatherDataDTO;

public interface WeatherService {
    WeatherDataDTO getWeatherData(double latitude, double longitude, String city);
}
