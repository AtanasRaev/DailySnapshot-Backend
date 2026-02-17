package com.dailysnapshotbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenMeteoForecastResponse {
    private CurrentTemperature current;
    private Daily daily;
    private String timezone;
}
