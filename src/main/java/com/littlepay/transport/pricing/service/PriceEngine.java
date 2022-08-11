package com.littlepay.transport.pricing.service;

import com.littlepay.transport.pricing.repository.BusFareRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PriceEngine {

    @Autowired
    private BusFareRepository busFareRepository;

    public BigDecimal calculateBusFare(List<String> stopIds) {
        String key = "";
        BigDecimal individualCharges = BigDecimal.ZERO;
        if (stopIds == null || stopIds.size() == 0) {

        } else if (stopIds.size() == 2) {
            key = stopIds.get(0) + stopIds.get(1);
            individualCharges = busFareRepository.getCharges().get(key);
        } else if (stopIds.size() == 1) {
            key = stopIds.get(0);
            String finalKey = key;
            List<String> keys = busFareRepository.getCharges().keySet().stream().filter(i -> i.contains(finalKey)).collect(Collectors.toList());
            individualCharges = getHighestFare(keys);
        } else {
            log.error("Invalid number of stops : {} observed", stopIds.size());
        }

        return individualCharges;
    }

    public BigDecimal getHighestFare(List<String> keys) {
        List<BigDecimal> chargeList = new ArrayList<>();
        keys.forEach(l -> {
            chargeList.add(busFareRepository.getCharges().get(l));
        });
        return Collections.max(chargeList);
    }
}
