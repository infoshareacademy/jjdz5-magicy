package com.infoshareacademy.usersengine.services.datamodels;

import com.infoshareacademy.usersengine.adverts.AdvertsConstants;
import com.infoshareacademy.usersengine.dao.MapsAdvertDao;
import com.infoshareacademy.usersengine.services.BandleService;
import com.infoshareacademy.usersengine.services.ParametersService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;

@Stateless
public class MapsAdvertDataModel extends DataModel<Map<String, String[]>>{

    private static final String ADVERTS_KEY = "ADVERTS";
    private static final String DETAIL_ADVERT_KEY = "DETAIL_ADVERT";
    private static final String BANDLE_KEY = "language";

    @Inject
    private MapsAdvertDao mapsAdvertDao;

    @Inject
    private BandleService bandleService;

    @Override
    public void fillDataModelWithGetData() {
        putApiKeyIntoDataModel();
        dataModel.put(ADVERTS_KEY, mapsAdvertDao.findAll());

        Locale plLocale = new Locale("pl","PL");
        dataModel.put(BANDLE_KEY, bandleService.getBundle(plLocale));
    }


    @Override
    public void fillDataModelWithPostData(Map<String, String[]> parameters) {
        putApiKeyIntoDataModel();
        dataModel.put(DETAIL_ADVERT_KEY, Collections.singletonList(
                mapsAdvertDao.findById(getDetailedAdvertId(parameters))));
    }

    private Long getDetailedAdvertId(Map<String, String[]> parameters) {
        return Long.valueOf(ParametersService.getSpecificParameter(parameters,
                AdvertsConstants.PARAMETER_ADVERT_ID));
    }

}
