package com.infoshareacademy.usersengine.api.googlemaps;

import com.infoshareacademy.usersengine.api.GenericClient;
import com.infoshareacademy.usersengine.api.googlemaps.datamodel.PlaceAddressComponent;
import com.infoshareacademy.usersengine.api.googlemaps.datamodel.PlaceAddressComponentResponse;
import com.infoshareacademy.usersengine.api.googlemaps.datamodel.PlaceAddressComponentType;
import com.infoshareacademy.usersengine.api.googlemaps.datamodel.PlaceRestResponse;
import com.infoshareacademy.usersengine.api.googlemaps.datamodel.PlaceResult;
import com.infoshareacademy.usersengine.services.PropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.NotFoundException;

public class PlacesClientImpl extends GenericClient implements PlacesClient {

    private static final Logger LOG = LoggerFactory.getLogger(PlacesClientImpl.class);

    @Override
    public PlaceResult getPlaceById(String id) {
        PlaceRestResponse response = getResponse(buildRequestUrl(id),
                PlaceRestResponse.class);
        if (isPlaceResponseCorrect(response)) {
            LOG.info("Response correct for place of id: {}.", id);
            return response.getResult();
        }
        LOG.info("Response failed for place of id: {}.", id);
        return null;
    }

    @Override
    public String getSpecificAddressComponent(PlaceResult result, PlaceAddressComponentType type,
                                              PlaceAddressComponentResponse response) {
        PlaceAddressComponent component = result.getAddressComponents().stream().filter(c ->
                c.getTypes().contains(type.getComponentType())).findAny()
                .orElseThrow(NotFoundException::new);
        if (response.equals(PlaceAddressComponentResponse.SHORT)) {
            LOG.info("{}, pulled correctly: {}.", type.name(), component.getShortName());
            return component.getShortName();
        } else {
            LOG.info("{}, pulled correctly: {}.", type.name(), component.getLongName());
            return component.getLongName();
        }
    }

    private String buildRequestUrl(String parameterValue) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL);
        sb.append(API_KEY_PARAMETER).append(PropertiesService.getMapsApiKey());
        sb.append(PLACE_ID_PARAMETER).append(parameterValue);
        return sb.toString();
    }

    private Boolean isPlaceResponseCorrect (PlaceRestResponse response) {
        return response.getStatus().equalsIgnoreCase(CORRECT_RESPONSE_STATUS);
    }

}
