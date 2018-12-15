package com.infoshareacademy.usersengine.services.mapsadverts;

import com.infoshareacademy.usersengine.adverts.AdvertsConstants;
import com.infoshareacademy.usersengine.model.AdvertPartType;
import com.infoshareacademy.usersengine.model.AdvertOverallType;
import com.infoshareacademy.usersengine.services.ParametersService;
import com.infoshareacademy.usersengine.services.PropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Stateless
public class MapsAdvertParametersVerifierBean implements MapsAdvertParametersVerifier {

    private static final Logger LOG = LoggerFactory.getLogger(MapsAdvertParametersVerifierBean.class);

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
    public Boolean areTooManyPassiveWaypoints(Map<String, String[]> parameters) {
        List<String> waypoints = extractor.extractWaypointsFromInput(parameters);
        LOG.info("Number of passive waypoints from parameters: {}.", waypoints.size());
        return waypoints.size() > PropertiesService.getAdvertMaxRouteModifiers();
    }
}
