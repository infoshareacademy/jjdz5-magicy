package com.infoshareacademy.usersengine.services.data_models;

import com.infoshareacademy.usersengine.dao.MapsDriverDao;
import com.infoshareacademy.usersengine.services.PropertiesService;
import com.infoshareacademy.usersengine.services.Property;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class AddMapsAdvertDataModel {

    private static final String DRIVERS_KEY = "DRIVERS";

    private Map<String, List> dataModel;

    @Inject
    private MapsDriverDao mapsDriverDao;

    public void buildNewDataModel() {
        dataModel = new HashMap<>();
    }

    public void fillDataModelWithGetData() {
        dataModel.put(Property.API_KEY.name(), getMapsApiKeyAsList());
        dataModel.put(DRIVERS_KEY, mapsDriverDao.findAll());
    }

    public void fillDataModelWithPostData(String key, List value) {
        dataModel.put(key, value);
    }

    public Map<String, List> getDataModel() {
        return dataModel;
    }

    private List<String> getMapsApiKeyAsList() {
        return Collections.singletonList(PropertiesService.getMapsApiKey());
    }



}
