package com.infoshareacademy.usersengine.statistics;

public enum UserActivity {

    LOG_IN("the user has logged in"),
    LOG_OUT("the user has logged out"),
    DISPLAYING_ADVERTS("the user has displayed the adverts"),
    ADDING_ADVERT("the user added the advert"),
    ADDING_DRIVER("the user added the driver");

    private String desctription;

    UserActivity(String desctription) {
        this.desctription = desctription;
    }

    public String getDesctription() {
        return desctription;
    }
}
