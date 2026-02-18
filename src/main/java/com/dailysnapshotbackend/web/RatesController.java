package com.dailysnapshotbackend.web;

import com.dailysnapshotbackend.dto.response.RatesResponse;
import com.dailysnapshotbackend.service.RatesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/rates")
public class RatesController {
    private final RatesService ratesService;

    @RequestMapping("/latest")
    public ResponseEntity<RatesResponse> getLatestRates(@RequestParam String baseRate) {
        return ResponseEntity.ok(this.ratesService.getRates(baseRate));
    }
}
