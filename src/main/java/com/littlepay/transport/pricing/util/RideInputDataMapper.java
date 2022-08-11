package com.littlepay.transport.pricing.util;

import com.littlepay.transport.pricing.model.RideInputData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import static com.littlepay.transport.pricing.util.Constant.*;

@Slf4j
@Component
public class RideInputDataMapper {

    private static final String TAB_OPERATOR = "\t";
    private static final String EMPTY_STRING = "";
    private static final String COMMA_SEPARATOR = ",";
    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

    public static RideInputData mapToRide(String line) {

        String[] data = line.replaceAll(TAB_OPERATOR, EMPTY_STRING).split(COMMA_SEPARATOR);

        RideInputData rideInputData = new RideInputData();
        rideInputData.setId(data[INDEX_ID].trim());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDateTime localDateTime = LocalDateTime.parse(data[INDEX_TIME].trim(), dateTimeFormatter);
        rideInputData.setDateTime(localDateTime);
        rideInputData.setTapType(data[INDEX_TAP_TYPE].trim());
        rideInputData.setStopId(data[INDEX_STOP_ID].trim());
        rideInputData.setCompanyId(data[INDEX_COMPANY_ID].trim());
        rideInputData.setBusId(data[INDEX_BUS_ID].trim());
        rideInputData.setPan(data[INDEX_PAN].trim());

        return rideInputData;
    };
}
