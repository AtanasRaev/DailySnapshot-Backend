package com.dailysnapshotbackend.service;

import com.dailysnapshotbackend.dto.CityDataDTO;
import com.dailysnapshotbackend.dto.WeatherDataDTO;

import java.util.List;

public interface WeatherService {
    WeatherDataDTO getWeatherData(double latitude, double longitude, String city);

    List<CityDataDTO> getCity(String input);
}
