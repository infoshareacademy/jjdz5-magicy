package com.infoshareacademy.usersengine.services.maps_adverts;

import com.infoshareacademy.usersengine.adverts.AdvertsConstants;
import com.infoshareacademy.usersengine.dao.MapsAdvertDao;
import com.infoshareacademy.usersengine.dao.MapsDriverDao;
import com.infoshareacademy.usersengine.model.MapsAdvert;
import com.infoshareacademy.usersengine.model.MapsDriver;
import com.infoshareacademy.usersengine.services.ParametersService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Map;

@Stateless
public class MapsAdvertBuilderBean implements MapsAdvertBuilder{

    @Inject
    private MapsAddressBuilder addressBuilder;

    @Inject
    private DateTimeBuilder dateTimeBuilder;

    @Inject
    private MapsAdvertDao mapsAdvertDao;

    @Inject
    private MapsDriverDao mapsDriverDao;

    @Override
    public void buildMapsAdvert(Map<String, String[]> parameters) {
        MapsAdvert mapsAdvert = new MapsAdvert(
                getDriverFromDatabase(parameters),
                addressBuilder.buildStartMapsAddress(parameters),
                addressBuilder.buildEndMapsAddress(parameters),
                dateTimeBuilder.buildStartTime(parameters),
                dateTimeBuilder.buildEndTime(parameters),
                dateTimeBuilder.buildDate(parameters));
        mapsAdvertDao.save(mapsAdvert);
    }

    private MapsDriver getDriverFromDatabase(Map<String, String[]> parameters) {
        final Long driverId = Long.valueOf(ParametersService.getSpecificParameter(parameters,
                AdvertsConstants.PARAMETER_DRIVER_ID));
        return mapsDriverDao.findById(driverId);
    }

}
