package com.infoshareacademy.usersengine.services.datamodels;

import com.infoshareacademy.usersengine.dao.MapsDriverDao;
import com.infoshareacademy.usersengine.model.MapsDriver;
import com.infoshareacademy.usersengine.services.BandleService;
import com.infoshareacademy.usersengine.services.PropertiesService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Stateless
public class AddMapsAdvertDataModel extends DataModel<List>{

    private static final String DRIVERS_KEY = "DRIVERS";
    private static final String SUMMARY_KEY = "SUMMARY";
    private static final String TIME_CONDITION_KEY = "TIME_CONDITION";
    private static final String DATE_CONDITION_KEY = "DATE_CONDITION";
    private static final String ROUTE_MODIFIERS_CONDITION_KEY = "ROUTE_MODIFIERS_CONDITION";
    private static final String DRIVER_KEY = "DRIVER";
    private static final String BANDLE_KEY = "language";


    @Inject
    private MapsDriverDao mapsDriverDao;

    @Inject
    private BandleService bandleService;

    @Override
    public void fillDataModelWithGetData() {
        putApiKeyIntoDataModel();
        dataModel.put(DRIVERS_KEY, mapsDriverDao.findAll());
        fillDataModuleWithInformationVariables();

        Locale plLocale = new Locale("pl","PL");
        dataModel.put(BANDLE_KEY, bandleService.getBundle(plLocale));
    }

    @Override
    public void fillDataModelWithPostData(List summary) {
        fillDataModelWithGetData();
        dataModel.put(SUMMARY_KEY, summary);
    }

    private void fillDataModuleWithInformationVariables() {
        dataModel.put(TIME_CONDITION_KEY, Collections.singletonList(
                PropertiesService.getAdvertMinHoursToStart()));
        dataModel.put(DATE_CONDITION_KEY, Collections.singletonList(
                PropertiesService.getAdvertMaxPeriodDays()));
        dataModel.put(ROUTE_MODIFIERS_CONDITION_KEY, Collections.singletonList(
                PropertiesService.getAdvertMaxRouteModifiers()));
    }

    public void fillDataModelWithCurrentDriver(MapsDriver currentDriver) {
        dataModel.put(DRIVER_KEY, currentDriver);
    }

}
