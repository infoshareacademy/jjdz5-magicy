package com.infoshareacademy.usersengine.services.maps_adverts;

import com.infoshareacademy.usersengine.adverts.AdvertsConstants;
import com.infoshareacademy.usersengine.api.googlemaps.PlacesClient;
import com.infoshareacademy.usersengine.api.googlemaps.datamodel.PlaceAddressComponentResponse;
import com.infoshareacademy.usersengine.api.googlemaps.datamodel.PlaceAddressComponentType;
import com.infoshareacademy.usersengine.api.googlemaps.datamodel.PlaceResult;
import com.infoshareacademy.usersengine.dao.MapsAddressDao;
import com.infoshareacademy.usersengine.model.MapsAddress;
import com.infoshareacademy.usersengine.services.ParametersService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

@Stateless
public class MapsAddressBuilderBean implements MapsAddressBuilder {

    @Inject
    private PlacesClient client;

    @Inject
    private MapsAddressDao mapsAddressDao;

    @Override
    public MapsAddress buildStartMapsAddress(Map<String, String[]> parameters) {
        final String placeId = ParametersService.getSpecificParameter(parameters,
                AdvertsConstants.PARAMETER_START_MAPS_POINT_ID);
        final String info = ParametersService.getSpecificParameter(parameters,
                AdvertsConstants.PARAMETER_START_INFO);
        return provideMapsAddress(placeId, info);
    }

    @Override
    public MapsAddress buildEndMapsAddress(Map<String, String[]> parameters) {
        final String placeId = ParametersService.getSpecificParameter(parameters,
                AdvertsConstants.PARAMETER_END_MAPS_POINT_ID);
        final String info = ParametersService.getSpecificParameter(parameters,
                AdvertsConstants.PARAMETER_END_INFO);
//        return provideMapsAddress(placeId, info);
        return buildMapsAddress(client.getPlaceById(placeId), info);
    }

    private MapsAddress provideMapsAddress(String placeId, String info) {
        Optional<MapsAddress> mapsAddress = Optional.ofNullable(mapsAddressDao.findById(placeId));
        return mapsAddress.orElseGet(() -> buildMapsAddress(client.getPlaceById(placeId), info));
    }
    
    private MapsAddress buildMapsAddress(PlaceResult result, String info) {
        MapsAddress mapsAddress = new MapsAddress(
            result.getPlaceId(),
            client.getSpecificAddressComponent(result, PlaceAddressComponentType.CITY,
                PlaceAddressComponentResponse.FULL),
            client.getSpecificAddressComponent(result, PlaceAddressComponentType.STREET_NAME,
                PlaceAddressComponentResponse.FULL),
            client.getSpecificAddressComponent(result, PlaceAddressComponentType.STREET_NUMBER,
                PlaceAddressComponentResponse.SHORT),
            result.getGeometry().getLocation().getLatitude(),
            result.getGeometry().getLocation().getLongitude(),
            info);
        mapsAddressDao.save(mapsAddress);
        return mapsAddress;
    }
}