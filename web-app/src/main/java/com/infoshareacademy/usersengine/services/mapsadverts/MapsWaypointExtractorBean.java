package com.infoshareacademy.usersengine.services.mapsadverts;

import com.infoshareacademy.usersengine.adverts.AdvertsConstants;
import com.infoshareacademy.usersengine.services.ParametersService;

import javax.ejb.Stateless;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Stateless
public class MapsWaypointExtractorBean implements MapsWaypointExtractor{

    @Override
    public String[] extractCoordinates(String coordinatesString) {
        String result = NO_CHARACTER;
        result += coordinatesString.replace(COORDINATE_START, NO_CHARACTER)
                .replace(COORDINATE_END, NO_CHARACTER)
                .replace(WHITESPACE, NO_CHARACTER);
        return result.split(INNER_SPLITTER);
    }

    @Override
    public List<String> extractWaypointsFromInput(Map<String, String[]> parameters) {
        String waypointsInput = ParametersService.getSpecificParameter(parameters,
                AdvertsConstants.PARAMETER_PASSIVE_WAYPOINTS);
        return Arrays.asList(waypointsInput.split(OUTER_SPLITTER));
    }

}
