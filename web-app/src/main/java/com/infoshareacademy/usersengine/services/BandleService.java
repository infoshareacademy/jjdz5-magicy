package com.infoshareacademy.usersengine.services;

import java.util.Locale;
import java.util.ResourceBundle;

public class BandleService {

    public ResourceBundle getBundle(Locale currentLocale){
        return ResourceBundle.getBundle("webapp/WEB-INF/properties/MessagesBundle", currentLocale);
    }
}
