package com.infoshareacademy.usersengine.statistics;

public enum UserActivity {

    LOG_IN("user has logged in"),
    LOG_OUT("user has logged out"),
    DISPLAYING_ADVERTS("user has displayed adverts"),
    ADDING_ADVERT("user added advert"),
    ADDING_DRIVER("user added driver");

    private String desctription;

    UserActivity(String desctription) {
        this.desctription = desctription;
    }

    public String getDesctription() {
        return desctription;
    }
}
