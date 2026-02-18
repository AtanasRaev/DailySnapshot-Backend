package com.dailysnapshotbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CityDataDTO {
    private String name;

    @JsonIgnore
    private Double latitude;

    @JsonIgnore
    private Double longitude;

    private String country;
}
