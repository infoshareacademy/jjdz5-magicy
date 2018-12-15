package com.infoshareacademy.usersengine.services.mapsadverts;

import com.infoshareacademy.usersengine.adverts.AdvertsConstants;
import com.infoshareacademy.usersengine.dao.MapsWaypointDao;
import com.infoshareacademy.usersengine.model.MapsWaypoint;
import com.infoshareacademy.usersengine.services.ParametersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless
public class MapsWaypointBuilderBean implements MapsWaypointBuilder{

    private static final Logger LOG = LoggerFactory.getLogger(MapsWaypointBuilderBean.class);

    @Inject
    private MapsWaypointExtractor extractor;

    @Inject
    private MapsWaypointDao mapsWaypointDao;

    @Override
    public List<MapsWaypoint> buildPassiveMapsWaypointsList(Map<String, String[]> parameters) {
        final Boolean isStopover = false;
        List<MapsWaypoint> waypoints = new ArrayList<>();
        if (areWaypointsPresent(parameters)) {
            extractor.extractWaypointsFromInput(parameters).forEach(w -> {
                MapsWaypoint waypoint = buildMapsWaypoint(w, isStopover);
                mapsWaypointDao.save(waypoint);
                waypoints.add(waypoint);
            });
        }
        return waypoints;
    }

    private Boolean areWaypointsPresent(Map<String, String[]> parameters) {
        return ParametersService.getSpecificParameter(parameters,
                AdvertsConstants.PARAMETER_PASSIVE_WAYPOINTS).length() > 0;
    }

    private MapsWaypoint buildMapsWaypoint(String coordinatesString, Boolean isStopover) {
        String[] coordinates = extractor.extractCoordinates(coordinatesString);
        return new MapsWaypoint(
                Double.valueOf(coordinates[0]),
                Double.valueOf(coordinates[1]),
                isStopover);
    }
}
