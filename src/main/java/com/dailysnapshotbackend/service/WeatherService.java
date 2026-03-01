package com.dailysnapshotbackend.service;

import com.dailysnapshotbackend.dto.CityDataDTO;
import com.dailysnapshotbackend.dto.WeatherDataDTO;

import java.util.List;

public interface WeatherService {
    List<CityDataDTO> getCity(String input);

    WeatherDataDTO getWeatherData(double latitude, double longitude);
}
