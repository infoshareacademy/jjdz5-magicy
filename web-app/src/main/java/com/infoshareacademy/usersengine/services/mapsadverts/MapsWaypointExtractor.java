package com.infoshareacademy.usersengine.services.mapsadverts;

import java.util.List;
import java.util.Map;

public interface MapsWaypointExtractor {

    String COORDINATE_START = "(";
    String COORDINATE_END = ")";
    String INNER_SPLITTER = ",";
    String OUTER_SPLITTER = ";";
    String WHITESPACE = " ";
    String NO_CHARACTER = "";

    String[] extractCoordinates(String coordinatesString);

    List<String> extractWaypointsFromInput(Map<String, String[]> parameters);

}
