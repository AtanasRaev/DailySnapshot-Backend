package com.dailysnapshotbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Daily {
    private Double[] temperature_2m_max;
    private Double[] temperature_2m_min;
}
