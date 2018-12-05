package com.infoshareacademy.usersengine.services.datamodels;

import com.infoshareacademy.usersengine.dao.MapsDriverDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class AddMapsAdvertDataModel extends DataModel<List>{

    private static final String DRIVERS_KEY = "DRIVERS";
    private static final String SUMMARY_KEY = "SUMMARY";

    @Inject
    private MapsDriverDao mapsDriverDao;

    @Override
    public void fillDataModelWithGetData() {
        putApiKeyIntoDataModel();
        dataModel.put(DRIVERS_KEY, mapsDriverDao.findAll());
    }

    @Override
    public void fillDataModelWithPostData(List summary) {
        dataModel.put(SUMMARY_KEY, summary);
    }

}
