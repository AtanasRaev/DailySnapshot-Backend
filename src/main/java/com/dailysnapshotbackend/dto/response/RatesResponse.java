package com.dailysnapshotbackend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
public class RatesResponse {
    private String base;
    private LocalDate date;
    private Map<String, BigDecimal> rates;
}
