package com.littlepay.transport.pricing.util;

import com.littlepay.transport.pricing.exceptions.InvalidDataException;
import com.littlepay.transport.pricing.exceptions.InvalidFileNameException;
import com.littlepay.transport.pricing.model.RideInputData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.littlepay.transport.pricing.util.Constant.INVALID_DATA;
import static com.littlepay.transport.pricing.util.Constant.INVALID_FILE;
import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {

    private final String fileName = "rides.csv";
    private CSVReader csvReader;

    @BeforeEach
    public void setup(){
        csvReader = new CSVReader();
    }

    @Test
    public void testInvalidFileName(){
        InvalidFileNameException thrown = Assertions.assertThrows(InvalidFileNameException.class, () -> {
            csvReader.readRidesFile("");
        }, "InvalidFileNameException was expected");

        Assertions.assertEquals(INVALID_FILE, thrown.getMessage());
    }

    @Test
    public void testReadValidFile(){
        List<RideInputData> rideInputData = csvReader.readRidesFile(fileName);
        Assertions.assertNotNull(rideInputData);
        Assertions.assertEquals(rideInputData.size(), 2);
    }


}