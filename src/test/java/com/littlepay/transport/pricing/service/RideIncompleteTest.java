package com.littlepay.transport.pricing.service;

import com.littlepay.transport.pricing.enums.RideStatus;
import com.littlepay.transport.pricing.model.RideInputData;
import com.littlepay.transport.pricing.util.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RideIncompleteTest {

    private final String fileNameIncomplete = "IncompleteRide.csv";
    private final String fileName = "rides.csv";
    private CSVReader csvReader;
    private RideCompleteChecker rideCompleteChecker;

    @BeforeEach
    public void setup(){
        csvReader = new CSVReader();
        rideCompleteChecker = new RideCompleteChecker();
    }

    @Test
    public void testRideIncompleteCheck(){
        List<RideInputData> rideInputData = csvReader.readRidesFile(fileNameIncomplete);
        Assertions.assertNotNull(rideInputData);
        String rideStatus = rideCompleteChecker.isCompletedRide(rideInputData);
        Assertions.assertNotNull(rideStatus);
        Assertions.assertEquals(rideStatus, RideStatus.INCOMPLETE.getValue());
    }
}
