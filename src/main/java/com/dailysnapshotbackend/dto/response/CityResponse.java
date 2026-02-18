package com.dailysnapshotbackend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponse {
    private String name;
    private Double latitude;
    private Double longitude;
    private String country;
}
