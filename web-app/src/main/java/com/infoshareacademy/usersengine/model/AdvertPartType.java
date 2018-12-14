package com.infoshareacademy.usersengine.model;

public enum AdvertPartType {

    START("departure", "startAddress"),
    VIA("indirect", "viaAddress"),
    END("destination", "endAddress");

    private final String messageType;
    private final String addressVariable;

    AdvertPartType(String messageType, String addressVariable) {
        this.messageType = messageType;
        this.addressVariable = addressVariable;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getAddressVariable() {
        return addressVariable;
    }
}
