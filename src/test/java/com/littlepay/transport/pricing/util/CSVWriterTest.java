package com.littlepay.transport.pricing.util;

import com.littlepay.transport.pricing.exceptions.InvalidDataException;
import com.littlepay.transport.pricing.exceptions.InvalidFileNameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static com.littlepay.transport.pricing.util.Constant.INVALID_DATA;
import static com.littlepay.transport.pricing.util.Constant.INVALID_FILE;
import static org.junit.jupiter.api.Assertions.*;

class CSVWriterTest {

    private List<String> data;
    private final String fileName = "test.csv";
    private CSVWriter csvWriter;

    @BeforeEach
    public void setup(){
        data = Arrays.asList("Stop1", "Stop2", "Stop3");
        csvWriter = new CSVWriter();
    }

    @Test
    public void testInvalidFileName(){
        InvalidFileNameException thrown = Assertions.assertThrows(InvalidFileNameException.class, () -> {
            csvWriter.createCSVFile(data, "");
        }, "InvalidFileNameException was expected");

        Assertions.assertEquals(INVALID_FILE, thrown.getMessage());
    }

    @Test
    public void testInvalidData(){
        InvalidDataException thrown = Assertions.assertThrows(InvalidDataException.class, () -> {
            csvWriter.createCSVFile(null, fileName);
        }, "InvalidDataException was expected");

        Assertions.assertEquals(INVALID_DATA, thrown.getMessage());
    }



}