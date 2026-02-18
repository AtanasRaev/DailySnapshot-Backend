package com.dailysnapshotbackend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentTemperature {
    private Double temperature_2m;
    private Double apparent_temperature;
    private Integer weather_code;
}
