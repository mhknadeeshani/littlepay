package com.littlepay.transport.pricing.repository;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BusFareRepository {

    public static Map<String, BigDecimal> getCharges() {
        Map<String, BigDecimal> charges = new HashMap<>();
        charges.put("Stop1Stop2", new BigDecimal("3.25"));
        charges.put("Stop2Stop3", new BigDecimal("5.50"));
        charges.put("Stop1Stop3", new BigDecimal("7.30"));

        return charges;
    }
}
