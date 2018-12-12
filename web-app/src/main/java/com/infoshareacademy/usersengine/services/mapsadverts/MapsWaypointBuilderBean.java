package com.infoshareacademy.usersengine.services.mapsadverts;

import com.infoshareacademy.usersengine.adverts.AdvertsConstants;
import com.infoshareacademy.usersengine.dao.MapsWaypointDao;
import com.infoshareacademy.usersengine.model.MapsWaypoint;
import com.infoshareacademy.usersengine.services.ParametersService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Stateless
public class MapsWaypointBuilderBean implements MapsWaypointBuilder{

    @Inject
    private MapsWaypointDao mapsWaypointDao;

    @Override
    public List<MapsWaypoint> buildPassiveMapsWaypointsList(Map<String, String[]> parameters) {
        final Boolean isStopover = false;
        List<MapsWaypoint> waypoints = new ArrayList<>();
        extractWaypointsFromInput(parameters).forEach(w -> {
            MapsWaypoint waypoint = buildMapsWaypoint(w, isStopover);
            mapsWaypointDao.save(waypoint);
            waypoints.add(waypoint);
        });
        return waypoints;
    }

    private MapsWaypoint buildMapsWaypoint(String coordinatesString, Boolean isStopover) {
        String[] coordinates = extractCoordinates(coordinatesString);
        return new MapsWaypoint(
                Double.valueOf(coordinates[0]),
                Double.valueOf(coordinates[1]),
                isStopover);
    }

    private String[] extractCoordinates(String coordinatesString) {
        final String start = "(";
        final String end = ")";
        final String separator = ",";
        final String whitespace = " ";

        String result = "";
        result += coordinatesString.replace(start, "")
                    .replace(end, "")
                    .replace(whitespace, "");
        return result.split(separator);

    }

    private List<String> extractWaypointsFromInput(Map<String, String[]> parameters) {
        final String splitter = ";";
        String waypointsInput = ParametersService.getSpecificParameter(parameters,
                AdvertsConstants.PARAMETER_PASSIVE_WAYPOINTS);
        return Arrays.asList(waypointsInput.split(splitter));
    }

}
