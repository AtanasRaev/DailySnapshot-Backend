package com.dailysnapshotbackend.service.impl;

import com.dailysnapshotbackend.config.OpenMeteoProperties;
import com.dailysnapshotbackend.dto.CityDataDTO;
import com.dailysnapshotbackend.dto.WeatherDataDTO;
import com.dailysnapshotbackend.dto.response.OpenMeteoForecastResponse;
import com.dailysnapshotbackend.dto.response.SearchResponse;
import com.dailysnapshotbackend.enums.WeatherCondition;
import com.dailysnapshotbackend.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@Service
public class WeatherServiceImpl implements WeatherService {
    private final RestClient restClient;
    private final OpenMeteoProperties openMeteoProperties;

    @Override
    public WeatherDataDTO getWeatherData(double latitude, double longitude) {
        String uri = UriComponentsBuilder.fromUriString(this.openMeteoProperties.getBaseUrl())
                .path("/forecast")
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("current", "temperature_2m,apparent_temperature,weather_code")
                .queryParam("daily", "temperature_2m_max,temperature_2m_min")
                .queryParam("timezone", "auto")
                .toUriString();

        OpenMeteoForecastResponse response = this.restClient.get()
                .uri(uri)
                .retrieve()
                .body(OpenMeteoForecastResponse.class);

        if (response == null || response.getCurrent() == null || response.getDaily() == null) {
            throw new IllegalStateException("Open-Meteo response is missing required sections");
        }

        return new WeatherDataDTO(
                Math.round(response.getCurrent().getTemperature_2m()),
                Math.round(response.getCurrent().getApparent_temperature()),
                Math.round(response.getDaily().getTemperature_2m_min()[0]),
                Math.round(response.getDaily().getTemperature_2m_max()[0]),
                WeatherCondition.fromCode(response.getCurrent().getWeather_code())
        );
    }

    @Override
    public List<CityDataDTO> getCity(String input) {
        String uri = UriComponentsBuilder.fromUriString(this.openMeteoProperties.getGeocodingBaseUrl())
                .path("/search")
                .queryParam("name", input)
                .toUriString();

        SearchResponse response = this.restClient.get()
                .uri(uri)
                .retrieve()
                .body(SearchResponse.class);

        if (response == null || response.getResults() == null) {
            throw new IllegalStateException();
        }

        return response.getResults().stream()
                .map(city -> new CityDataDTO(city.getName(), city.getLatitude(), city.getLongitude(), city.getCountry()))
                .toList();
    }
}
