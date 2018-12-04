package com.infoshareacademy.usersengine.model;

public enum AdvertPartType {

    START("departure"),
    VIA("indirect"),
    END("destination");

    private String messageType;

    AdvertPartType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageType() {
        return messageType;
    }
}
