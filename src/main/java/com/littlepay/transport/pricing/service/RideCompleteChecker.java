package com.littlepay.transport.pricing.service;

import com.littlepay.transport.pricing.enums.RideStatus;
import com.littlepay.transport.pricing.model.RideInputData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RideCompleteChecker {

    public String isCompletedRide(List<RideInputData> individualRides) {
        RideStatus completed = RideStatus.INVALID;
        List<String> tapTypes = individualRides.stream().map(RideInputData::getTapType).distinct().collect(Collectors.toList());
        List<String> stops = individualRides.stream().map(RideInputData::getStopId).distinct().collect(Collectors.toList());
        if (tapTypes.size() == 2) {
            completed = RideStatus.COMPLETED;
        } else if (tapTypes.size() == 2 && stops.size() == 1) {
            completed = RideStatus.CANCELLED;
        } else {
            completed = RideStatus.INCOMPLETE;
        }
        return completed.getValue();
    }
}
