package com.infoshareacademy.usersengine.adverts;

import com.infoshareacademy.usersengine.services.ParametersService;

import javax.ejb.Singleton;
import java.util.Map;

@Singleton
public class MapsAdvertParametersPreparation {

    private AdvertData advertData;

    public AdvertData prepareMapsAdvertData(Map<String, String[]> map) {
        advertData = new AdvertData();
        prepareStartParameters(map);
        prepareEndParameters(map);
        return advertData;
    }

    private void prepareStartParameters(Map<String, String[]> map) {
        String startCity = ParametersService.getSpecificParameter(map,
                AdvertsConstants.PARAMETER_START_CITY);
        String startStreet = ParametersService.getSpecificParameter(map,
                AdvertsConstants.PARAMETER_START_STREET);
        String startStreetNumber = ParametersService.getSpecificParameter(map,
                AdvertsConstants.PARAMETER_START_STREET_NUMBER);
        String startLatitude = ParametersService.getSpecificParameter(map,
                AdvertsConstants.PARAMETER_START_LATITUDE);
        String startLongitude = ParametersService.getSpecificParameter(map,
                AdvertsConstants.PARAMETER_START_LONGITUDE);
        String startMapsPointId = ParametersService.getSpecificParameter(map,
                AdvertsConstants.PARAMETER_START_MAPS_POINT_ID);
        String startTime = ParametersService.getSpecificParameter(map,
                AdvertsConstants.PARAMETER_START_TIME);
        String startInfo = ParametersService.getSpecificParameter(map,
                AdvertsConstants.PARAMETER_START_INFO);
        advertData.setStartCity(startCity);
        advertData.setStartStreet(startStreet);
        advertData.setStartStreetNumber(startStreetNumber);
        advertData.setStartLatitude(startLatitude);
        advertData.setStartLongitude(startLongitude);
        advertData.setStartMapsPointId(startMapsPointId);
        advertData.setStartTime(startTime);
        advertData.setStartInfo(startInfo);
    }

    private void prepareEndParameters(Map<String, String[]> map) {
        String endCity = ParametersService.getSpecificParameter(map,
                AdvertsConstants.PARAMETER_END_CITY);
        String endStreet = ParametersService.getSpecificParameter(map,
                AdvertsConstants.PARAMETER_END_STREET);
        String endStreetNumber = ParametersService.getSpecificParameter(map,
                AdvertsConstants.PARAMETER_END_STREET_NUMBER);
        String endLatitude = ParametersService.getSpecificParameter(map,
                AdvertsConstants.PARAMETER_END_LATITUDE);
        String endLongitude = ParametersService.getSpecificParameter(map,
                AdvertsConstants.PARAMETER_END_LONGITUDE);
        String endMapsPointId = ParametersService.getSpecificParameter(map,
                AdvertsConstants.PARAMETER_END_MAPS_POINT_ID);
        String endTime = ParametersService.getSpecificParameter(map,
                AdvertsConstants.PARAMETER_END_TIME);
        String endInfo = ParametersService.getSpecificParameter(map,
                AdvertsConstants.PARAMETER_END_INFO);
        advertData.setEndCity(endCity);
        advertData.setEndStreet(endStreet);
        advertData.setEndStreetNumber(endStreetNumber);
        advertData.setEndLatitude(endLatitude);
        advertData.setEndLongitude(endLongitude);
        advertData.setEndMapsPointId(endMapsPointId);
        advertData.setEndTime(endTime);
        advertData.setEndInfo(endInfo);
    }
}
