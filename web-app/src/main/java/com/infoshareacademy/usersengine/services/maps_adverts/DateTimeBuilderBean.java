package com.infoshareacademy.usersengine.services.maps_adverts;

import com.infoshareacademy.usersengine.adverts.AdvertsConstants;
import com.infoshareacademy.usersengine.services.ParametersService;

import javax.ejb.Stateless;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Stateless
public class DateTimeBuilderBean implements DateTimeBuilder {

    public LocalTime buildStartTime(Map<String, String[]> parameters) {
        return LocalTime.parse(ParametersService.getSpecificParameter(parameters,
                AdvertsConstants.PARAMETER_START_TIME));
    }

    public LocalTime buildEndTime(Map<String, String[]> parameters) {
        return LocalTime.parse(ParametersService.getSpecificParameter(parameters,
                AdvertsConstants.PARAMETER_END_TIME));
    }

    public LocalDate buildDate(Map<String, String[]> parameters) {
        return LocalDate.parse(ParametersService.getSpecificParameter(parameters,
                AdvertsConstants.PARAMETER_DATE));
    }

}
