package com.littlepay.transport.pricing.enums;

import lombok.Getter;

@Getter
public enum RideStatus {

    INVALID("INVALID"),
    COMPLETED("COMPLETED"),
    INCOMPLETE("INCOMPLETE"),
    CANCELLED("CANCELLED");

    private final String value;

    RideStatus(String value){
        this.value = value;
    }
}
