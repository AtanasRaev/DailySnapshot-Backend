package com.dailysnapshotbackend.web;

import com.dailysnapshotbackend.dto.CityDataDTO;
import com.dailysnapshotbackend.dto.WeatherDataDTO;
import com.dailysnapshotbackend.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/search")
    public ResponseEntity<List<CityDataDTO>> search(@RequestParam("name") String input) {
        return ResponseEntity.ok(this.weatherService.getCity(input));
    }

    @GetMapping()
    public ResponseEntity<WeatherDataDTO> getWeather(@RequestParam("latitude") double latitude,
                                                     @RequestParam("longitude") double longitude,
                                                     @RequestParam("city") String city) {
        return ResponseEntity.ok(this.weatherService.getWeatherData(latitude, longitude, city));
    }
}
