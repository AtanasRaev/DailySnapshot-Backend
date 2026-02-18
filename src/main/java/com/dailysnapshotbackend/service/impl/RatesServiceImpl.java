package com.dailysnapshotbackend.service.impl;

import com.dailysnapshotbackend.config.FrankfurterProperties;
import com.dailysnapshotbackend.dto.response.RatesResponse;
import com.dailysnapshotbackend.service.RatesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@Service
public class RatesServiceImpl implements RatesService {
    private final RestClient restClient;
    private FrankfurterProperties frankfurterProperties;

    @Override
    public RatesResponse getRates(String baseRate) {
        String uri = UriComponentsBuilder.fromUriString(this.frankfurterProperties.getBaseUrl())
                .path("/latest")
                .queryParam("base", baseRate)
                .toUriString();

        return this.restClient.get()
                .uri(uri)
                .retrieve()
                .body(RatesResponse.class);
    }
}
