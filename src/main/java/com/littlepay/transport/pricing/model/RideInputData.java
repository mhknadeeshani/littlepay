package com.littlepay.transport.pricing.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Comparator;

@Getter
@Setter
public class RideInputData {

    private String id;
    private LocalDateTime dateTime;
    private String TapType;
    private String stopId;
    private String companyId;
    private String busId;
    private String pan;

    @Override
    public String toString() {
        return "RideInputData{" +
                "id='" + id + '\'' +
                ", dateTime=" + dateTime +
                ", TapType='" + TapType + '\'' +
                ", stopId='" + stopId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", busId='" + busId + '\'' +
                ", pan='" + pan + '\'' +
                '}';
    }
}
