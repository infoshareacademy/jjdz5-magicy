package com.infoshareacademy.usersengine.services.mapsadverts;

import com.infoshareacademy.usersengine.adverts.AdvertsConstants;
import com.infoshareacademy.usersengine.model.AdvertPartType;
import com.infoshareacademy.usersengine.model.AdvertOverallType;
import com.infoshareacademy.usersengine.services.ParametersService;
import com.infoshareacademy.usersengine.services.PropertiesService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Map;

@Stateless
public class MapsAdvertParametersVerifierBean implements MapsAdvertParametersVerifier {

    @Inject
    private MapsWaypointExtractor extractor;

    @Override
    public Boolean isAddressParameterCorrect(Map<String, String[]> parameters, AdvertPartType type) {
        Boolean parametersCorrectness = false;
        if (type.equals(AdvertPartType.START)) {
            parametersCorrectness = ParametersService.isParameterCorrect(parameters,
                        AdvertsConstants.PARAMETER_START_MAPS_POINT_ID);
        }
        if (type.equals(AdvertPartType.END)) {
            parametersCorrectness =  ParametersService.isParameterCorrect(parameters,
                        AdvertsConstants.PARAMETER_END_MAPS_POINT_ID);
        }
        return parametersCorrectness;
    }

    @Override
    public Boolean isTimeParameterCorrect(Map<String, String[]> parameters, AdvertPartType type) {
        Boolean parametersCorrectness = false;
        if (type.equals(AdvertPartType.START)) {
            parametersCorrectness = ParametersService.isParameterCorrect(parameters,
                            AdvertsConstants.PARAMETER_START_TIME);
        }
        if (type.equals(AdvertPartType.END)) {
            parametersCorrectness = ParametersService.isParameterCorrect(parameters,
                            AdvertsConstants.PARAMETER_END_TIME);
        }
        return parametersCorrectness;
    }

    @Override
    public Boolean isOverallParameterCorrect(Map<String, String[]> parameters,
                                             AdvertOverallType type) {
        Boolean overallParameterCorrectness = false;
        if (type.equals(AdvertOverallType.DATE)) {
            overallParameterCorrectness = ParametersService.isParameterCorrect(parameters,
                    AdvertsConstants.PARAMETER_DATE);
        }
        if (type.equals(AdvertOverallType.DRIVER)) {
            overallParameterCorrectness = ParametersService.isParameterCorrect(parameters,
                    AdvertsConstants.PARAMETER_DRIVER_ID);
        }
        return overallParameterCorrectness;
    }

    @Override
    public Boolean isAddressPrecisionParameterCorrect(Map<String, String[]> parameters,
                                                      AdvertPartType type) {
        Boolean precisionParameterCorrectness = false;
        if (type.equals(AdvertPartType.START)) {
            precisionParameterCorrectness = ParametersService.isParameterCorrect(parameters,
                    AdvertsConstants.PARAMETER_START_STREET_NUMBER);
        }
        if (type.equals(AdvertPartType.END)) {
            precisionParameterCorrectness = ParametersService.isParameterCorrect(parameters,
                    AdvertsConstants.PARAMETER_END_STREET_NUMBER);
        }
        return precisionParameterCorrectness;
    }

    @Override
    public Boolean areNotToManyPassiveWaypoints(Map<String, String[]> parameters) {
        return extractor.extractWaypointsFromInput(parameters).size() <=
                PropertiesService.getAdvertMaxRouteModifiers();
    }
}
