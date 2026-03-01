package com.dailysnapshotbackend.dto;

import com.dailysnapshotbackend.enums.WeatherCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WeatherDataDTO {
    private Long temperature;
    private Long feelsLike;
    private Long todayMin;
    private Long todayMax;
    private WeatherCondition condition;
}
