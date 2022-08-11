package com.littlepay.transport.pricing.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RideOutputData {

    private String Id;
    private String startDateTime;
    private String finishDateTime;
    private String duration;
    private String fromStopId;
    private String toStopId;
    private String chargeAmount;
    private String companyId;
    private String busId;
    private String pan;
    private String status;

    @Override
    public String toString() {
        return "RideOutputData{" +
                "Id='" + Id + '\'' +
                ", startDateTime='" + startDateTime + '\'' +
                ", finishDateTime='" + finishDateTime + '\'' +
                ", duration='" + duration + '\'' +
                ", fromStopId='" + fromStopId + '\'' +
                ", toStopId='" + toStopId + '\'' +
                ", chargeAmount='" + chargeAmount + '\'' +
                ", companyId='" + companyId + '\'' +
                ", busId='" + busId + '\'' +
                ", pan='" + pan + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
