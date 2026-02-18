package com.dailysnapshotbackend.service;

import com.dailysnapshotbackend.dto.response.RatesResponse;

public interface RatesService {
    RatesResponse getRates(String baseRate);
}
