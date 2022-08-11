package com.littlepay.transport.pricing.service;

import com.littlepay.transport.pricing.enums.RideStatus;
import com.littlepay.transport.pricing.model.RideInputData;
import com.littlepay.transport.pricing.util.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RideCompleteCheckerTest {

    private final String fileName = "rides.csv";
    private CSVReader csvReader;
    private RideCompleteChecker rideCompleteChecker;

    @BeforeEach
    public void setup(){
        csvReader = new CSVReader();
        rideCompleteChecker = new RideCompleteChecker();
    }

    @Test
    public void testRideCompletedCheck(){
        List<RideInputData> rideInputData = csvReader.readRidesFile(fileName);
        Assertions.assertNotNull(rideInputData);
        String rideStatus = rideCompleteChecker.isCompletedRide(rideInputData);
        Assertions.assertNotNull(rideStatus);
        Assertions.assertEquals(rideStatus, RideStatus.COMPLETED.getValue());
    }
}