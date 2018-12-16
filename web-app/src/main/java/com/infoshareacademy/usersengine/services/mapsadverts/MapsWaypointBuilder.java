package com.infoshareacademy.usersengine.services.mapsadverts;

import com.infoshareacademy.usersengine.model.MapsWaypoint;

import java.util.List;
import java.util.Map;

public interface MapsWaypointBuilder {

    public List<MapsWaypoint> buildPassiveMapsWaypointsList(Map<String, String[]> parameters);

}
