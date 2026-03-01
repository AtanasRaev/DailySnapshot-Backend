package com.dailysnapshotbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CityDataDTO {
    private String name;

    private Double latitude;

    private Double longitude;

    private String country;
}
