package com.infoshareacademy.usersengine.services.mapsadverts;

import com.infoshareacademy.usersengine.dao.MapsWaypointDao;
import com.infoshareacademy.usersengine.model.MapsWaypoint;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless
public class MapsWaypointBuilderBean implements MapsWaypointBuilder{

    @Inject
    private MapsWaypointExtractor extractor;

    @Inject
    private MapsWaypointDao mapsWaypointDao;

    @Override
    public List<MapsWaypoint> buildPassiveMapsWaypointsList(Map<String, String[]> parameters) {
        final Boolean isStopover = false;
        List<MapsWaypoint> waypoints = new ArrayList<>();
        List<String> waypointsFromInput = extractor.extractWaypointsFromInput(parameters);
        if (areWaypointsPresent(waypointsFromInput)) {
            waypointsFromInput.forEach(w -> {
                MapsWaypoint waypoint = buildMapsWaypoint(w, isStopover);
                mapsWaypointDao.save(waypoint);
                waypoints.add(waypoint);
            });
        }
        return waypoints;
    }

    private Boolean areWaypointsPresent(List<String> waypointsFromInput) {
        return waypointsFromInput != null || !waypointsFromInput.isEmpty();
    }

    private MapsWaypoint buildMapsWaypoint(String coordinatesString, Boolean isStopover) {
        String[] coordinates = extractor.extractCoordinates(coordinatesString);
        return new MapsWaypoint(
                Double.valueOf(coordinates[0]),
                Double.valueOf(coordinates[1]),
                isStopover);
    }
}
