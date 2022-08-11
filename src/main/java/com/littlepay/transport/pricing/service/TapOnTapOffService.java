package com.littlepay.transport.pricing.service;

import com.littlepay.transport.pricing.model.RideInputData;
import com.littlepay.transport.pricing.model.RideOutputData;
import com.littlepay.transport.pricing.util.CSVReader;
import com.littlepay.transport.pricing.util.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TapOnTapOffService {

    private final String inputFileName = "rides.csv";
    private final String outputFileName = "output.csv";


    @Autowired
    private RideOutputDataService rideOutputDataService;

    @Autowired
    private RideInputDataService rideInputDataService;

    @Autowired
    private CSVWriter csvWriter;

    public void start(){
        CSVReader csvReader = new CSVReader();
        List<RideInputData> rideInputs = csvReader.readRidesFile(inputFileName);
        //Assumption one passenger(pan) will take only 1 complete ride

        List<RideOutputData> rideOutputs = rideOutputDataService.generateOutPutList(rideInputDataService.individualTravelMap(rideInputs));
        rideOutputs.forEach(i -> System.out.println(i.toString()));
        csvWriter.createCSVFile(rideOutputDataService.createOutputRecords(rideOutputs), outputFileName);
    }


}
