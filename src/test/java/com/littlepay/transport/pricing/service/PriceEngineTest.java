package com.littlepay.transport.pricing.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;

public class PriceEngineTest {

    private List<String> data;
    private List<String> incompleteRideData;

    private PriceEngine priceEngine;

    @BeforeEach
    public void setup(){
        data = Arrays.asList("Stop1", "Stop2");
        incompleteRideData = Arrays.asList("Stop1Stop2","Stop1Stop3");
    }

    @Test
    public void calculateBusFareTest(){
        priceEngine = new PriceEngine();
        BigDecimal busFare = priceEngine.calculateBusFare(data);
        Assertions.assertEquals(busFare,new BigDecimal(3.25));

    }

    @Test
    public void highestFareForIncompleteTest(){
        priceEngine = new PriceEngine();
        BigDecimal busFare = priceEngine.getHighestFare(incompleteRideData);
        MathContext m = new MathContext(3);
        Assertions.assertEquals(busFare,new BigDecimal(7.30).round(m));
    }
}
