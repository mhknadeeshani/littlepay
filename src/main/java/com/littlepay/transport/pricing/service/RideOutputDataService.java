package com.littlepay.transport.pricing.service;

import com.littlepay.transport.pricing.model.RideInputData;
import com.littlepay.transport.pricing.model.RideOutputData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RideOutputDataService {

    @Autowired
    private PriceEngine priceEngine;

    @Autowired
    private RideCompleteChecker rideCompleteChecker;

    private static Comparator<RideInputData> compareByDateAndPan = Comparator.comparing(RideInputData::getDateTime).thenComparing(RideInputData::getPan);

    public List<RideOutputData> generateOutPutList(Map<String, List<RideInputData>> individualTravelMap) {
        List<RideOutputData> outputRides = new ArrayList<>();
        individualTravelMap.entrySet().forEach(r -> {
            List<RideInputData> individualRides = new ArrayList<>();
            RideOutputData rideOutputData = new RideOutputData();
            Long duration = 0L;
            individualRides = r.getValue().stream().sorted(compareByDateAndPan).collect(Collectors.toList());
            List<String> stopIds = individualRides.stream().map(RideInputData::getStopId).sorted().collect(Collectors.toList());

            rideOutputData.setStartDateTime(individualRides.get(0).getDateTime().toString());
            rideOutputData.setFinishDateTime(individualRides.size() == 2 ? individualRides.get(1).getDateTime().toString() : "");
            rideOutputData.setCompanyId(individualRides.get(0).getCompanyId());
            duration = individualRides.size() == 2 ? Duration.between(individualRides.get(0).getDateTime(), individualRides.get(1).getDateTime()).getSeconds() : 0L;
            rideOutputData.setDuration(duration.toString());
            rideOutputData.setFromStopId(individualRides.get(0).getStopId());
            rideOutputData.setToStopId(individualRides.size() == 2 ? individualRides.get(1).getStopId() : "");
            rideOutputData.setChargeAmount(priceEngine.calculateBusFare(stopIds).toString());
            rideOutputData.setBusId(individualRides.get(0).getBusId());
            rideOutputData.setPan(individualRides.get(0).getPan());
            rideOutputData.setStatus(rideCompleteChecker.isCompletedRide(individualRides));
            outputRides.add(rideOutputData);
        });
        return outputRides;
    }

    public List<String> createOutputRecords(List<RideOutputData> rideOutputs) {
        List<String> outPutList = new ArrayList<>();

        String comma = ", ";
        String header = "Started, Finished, DurationSecs, FromStopId, ToStopId, ChargeAmount, CompanyId, BusID, PAN, Status";
        outPutList.add(header);
        rideOutputs.forEach(l -> {
            StringBuilder sb = new StringBuilder();
            sb.append(l.getStartDateTime());
            sb.append(comma);
            sb.append(l.getFinishDateTime());
            sb.append(comma);
            sb.append(l.getDuration());
            sb.append(comma);
            sb.append(l.getFromStopId());
            sb.append(comma);
            sb.append(l.getToStopId());
            sb.append(comma);
            sb.append(l.getChargeAmount());
            sb.append(comma);
            sb.append(l.getCompanyId());
            sb.append(comma);
            sb.append(l.getBusId());
            sb.append(comma);
            sb.append(l.getPan());
            sb.append(comma);
            sb.append(l.getStatus());
            outPutList.add(sb.toString());
        });
        return outPutList;
    }

}
