package com.dailysnapshotbackend.web;

import com.dailysnapshotbackend.dto.WeatherDataDTO;
import com.dailysnapshotbackend.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class WebController {
    private final WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<WeatherDataDTO> getWeather() {
        return ResponseEntity.ok(this.weatherService.getWeatherData(42.1354, 24.7453, "Plovdiv"));
    }
}
