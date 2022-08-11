package com.littlepay.transport.pricing.service;

import com.littlepay.transport.pricing.model.RideInputData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RideInputDataService {


    public Map<String, List<RideInputData>> individualTravelMap(List<RideInputData> rideInputs) {
        Map<String, List<RideInputData>> inputMap = new HashMap<>();
        rideInputs.stream().forEach(ride -> {
            List<RideInputData> individualRides = new ArrayList<>();
            if (inputMap.containsKey(ride.getPan())) {
                individualRides = inputMap.get(ride.getPan());
                individualRides.add(ride);
                inputMap.put(ride.getPan(), individualRides);
            } else {
                individualRides.add(ride);
                inputMap.put(ride.getPan(), individualRides);
            }
        });
        return inputMap;
    }
}
