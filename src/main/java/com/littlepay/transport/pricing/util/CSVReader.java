package com.littlepay.transport.pricing.util;

import com.littlepay.transport.pricing.exceptions.InvalidFileNameException;
import com.littlepay.transport.pricing.model.RideInputData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.littlepay.transport.pricing.util.Constant.HEADER;
import static com.littlepay.transport.pricing.util.Constant.INVALID_FILE;

@Slf4j
@Component
public class CSVReader {

    @Autowired
    private RideInputDataMapper rideInputDataMapper;

    public List<RideInputData> readRidesFile(String inputFilePath){

        if(StringUtils.isEmpty(inputFilePath)){
            throw new InvalidFileNameException(INVALID_FILE);
        }

        List<RideInputData> inputRides = new ArrayList<>();
        try{
            File inputFile = new File(getClass().getClassLoader().getResource(inputFilePath).getFile());
            InputStream inputFs = new FileInputStream(inputFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFs));

            inputRides = br.lines().skip(HEADER).map(RideInputDataMapper::mapToRide).collect(Collectors.toList());

        }catch(FileNotFoundException e){
            log.error("Exception occurred while reading file ", e);
        }

        return inputRides;
    }

}

