package com.infoshareacademy.usersengine.adverts;

import com.infoshareacademy.UserInput;
import com.infoshareacademy.usersengine.dao.MapsDriverDao;
import com.infoshareacademy.usersengine.model.AddressType;
import com.infoshareacademy.usersengine.model.MapsDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MapsAdvertValidation {

    private static final Logger LOG = LoggerFactory.getLogger(MapsAdvertValidation.class);
    private UserInput userInput = new UserInput();
    private List<String> checkingMessages;

    @Inject
    private AdvertsValidation advertsValidation;

    @Inject
    private MapsDriverDao mapsDriverDao;

    public void checkAdvertDataCorrection(AdvertData advertData) {
        checkingMessages = new ArrayList<>();
        checkStartAdvertDataCorrection(advertData);
        checkEndAdvertDataCorrection(advertData);
        if (areTimesCorrect()) {
            checkTimePeriodCorrection(advertData);
        }
        checkDateCorrection(advertData.getDate());
        checkDriverIdCorrection(advertData.getDriverId());
    }

    public List<String> getCheckingMessages() {
        return checkingMessages;
    }
    
    private void checkStartAdvertDataCorrection(AdvertData advertData) {
        final AddressType type = AddressType.START;
        checkCityCorrection(advertData.getStartCity(), type);
        checkStreetCorrection(advertData.getStartStreet(), type);
        checkStreetNumberCorrection(advertData.getStartStreetNumber(), type);
        checkTimeCorrection(advertData.getStartTime(), type);
        checkLatitudeCorrection(advertData.getStartLatitude(), type);
        checkLongitudeCorrection(advertData.getStartLongitude(), type);
        checkMapsPointIdCorrection(advertData.getStartMapsPointId(), type);
    }
    
    private void checkEndAdvertDataCorrection(AdvertData advertData) {
        final AddressType type = AddressType.END;
        checkCityCorrection(advertData.getEndCity(), type);
        checkStreetCorrection(advertData.getEndStreet(), type);
        checkStreetNumberCorrection(advertData.getEndStreetNumber(), type);
        checkTimeCorrection(advertData.getEndTime(), type);
        checkLatitudeCorrection(advertData.getEndLatitude(), type);
        checkLongitudeCorrection(advertData.getEndLongitude(), type);
        checkMapsPointIdCorrection(advertData.getEndMapsPointId(), type);
    }

    private void checkCityCorrection(String city, AddressType type) {
        if (!advertsValidation.askForCity(city)) {
            LOG.info("Incorrect {} city from user input: {}.", type.name().toLowerCase(), city);
            selectMessage(type, AdvertsConstants.MESSAGE_INCORRECT_START_CITY,
                    AdvertsConstants.MESSAGE_INCORRECT_END_CITY);
        }
    }

    private void checkStreetCorrection(String street, AddressType type) {
        if (!advertsValidation.askForStreet(street)) {
            LOG.info("Incorrect {} street from user input: {}.", type.name().toLowerCase(), street);
            selectMessage(type, AdvertsConstants.MESSAGE_INCORRECT_START_STREET,
                    AdvertsConstants.MESSAGE_INCORRECT_END_STREET);
        }
    }

    private void checkStreetNumberCorrection(String streetNumber, AddressType type) {
        if (isInputEmpty(streetNumber)) {
            LOG.info("Incorrect {} street number from user input: {}.",
                    type.name().toLowerCase(), streetNumber);
            selectMessage(type, AdvertsConstants.MESSAGE_INCORRECT_START_STREET_NUMBER,
                    AdvertsConstants.MESSAGE_INCORRECT_END_STREET_NUMBER);
        }
    }
    
    private void checkTimeCorrection(String time, AddressType type) {
        if (!userInput.isTimeValid(time)) {
            LOG.info("Incorrect {} time from user input: {}.",
                    type.name().toLowerCase(), time);
            selectMessage(type, AdvertsConstants.MESSAGE_INCORRECT_START_TIME,
                    AdvertsConstants.MESSAGE_INCORRECT_END_TIME);
        }
    }
    
    private void checkTimePeriodCorrection(AdvertData advertData) {
        final LocalTime startTime = LocalTime.parse(advertData.getStartTime());
        final LocalTime endTime = LocalTime.parse(advertData.getEndTime());
        if (isTimePeriodNotCorrect(startTime, endTime)) {
            LOG.info("Incorrect time period between start time ({}) and end time ({}).",
                    startTime, endTime);
            checkingMessages.add(AdvertsConstants.MESSAGE_INCORRECT_TIME_PERIOD);
        }
    }

    private void checkLatitudeCorrection(String latitude, AddressType type) {
        final Double lat = parseCoordinate(latitude);
        if (lat == null || isLatitudeNotInRange(lat)) {
            LOG.info("Incorrect {} latitude from user input: {}.",
                    type.name().toLowerCase(), latitude);
            selectMessage(type, AdvertsConstants.MESSAGE_INCORRECT_START_LATITUDE,
                    AdvertsConstants.MESSAGE_INCORRECT_END_LATITUDE);
        }
    }

    private void checkLongitudeCorrection(String longitude, AddressType type) {
        final Double lng = parseCoordinate(longitude);
        if (lng == null || isLongitudeNotInRange(lng)) {
            LOG.info("Incorrect {} latitude from user input: {}.",
                    type.name().toLowerCase(), longitude);
            selectMessage(type, AdvertsConstants.MESSAGE_INCORRECT_START_LONGITUDE,
                    AdvertsConstants.MESSAGE_INCORRECT_END_LONGITUDE);
        }
    }

    private void checkMapsPointIdCorrection(String mapsPointId, AddressType type) {
        if (isInputEmpty(mapsPointId)) {
            LOG.info("Empty {} maps point id");
            selectMessage(type, AdvertsConstants.MESSAGE_INCORRECT_START_MAPS_POINT_ID,
                    AdvertsConstants.MESSAGE_INCORRECT_END_MAPS_POINT_ID);
        }
    }

    private void checkDateCorrection(String date) {
        if (!advertsValidation.askForDate(date)) {
            LOG.info("Incorrect date from user input: {}.", date);
            checkingMessages.add(AdvertsConstants.MESSAGE_INCORRECT_DATE);
        }
    }

    private void checkDriverIdCorrection(String driverId) {
        Long parsedId = parseDriverId(driverId);
        if (parsedId == null) {
            LOG.info("Cannot parse given driver id: {}.", driverId);
            checkingMessages.add(AdvertsConstants.MESSAGE_INCORRECT_DRIVER_ID);
        } else {
            checkDriverInDatabase(parsedId);
        }
    }

    private Boolean areTimesCorrect() {
        return !checkingMessages.contains(AdvertsConstants.MESSAGE_INCORRECT_START_TIME) ||
               !checkingMessages.contains(AdvertsConstants.MESSAGE_INCORRECT_END_TIME);
    }
    
    private Boolean isTimePeriodNotCorrect(LocalTime start, LocalTime end) {
        return start.isAfter(end) || start.equals(end);
    }

    private Double parseCoordinate(String coordinate) {
        try {
            return Double.valueOf(coordinate);
        } catch (NumberFormatException e) {
            LOG.info("Cannot parse given coordinate: {}.", coordinate);
        }
        return null;
    }

    private Boolean isLatitudeNotInRange(Double latitude) {
        final Double minLat = -90.00;
        final Double maxLat = 90.00;
        try {
            return latitude < minLat || latitude > maxLat;
        } catch (NullPointerException e) {
            return true;
        }
    }

    private Boolean isLongitudeNotInRange(Double longitude) {
        final Double minLat = -180.00;
        final Double maxLat = 180.00;
        try {
            return longitude < minLat || longitude > maxLat;
        } catch (NullPointerException e) {
            return true;
        }
    }

    private Long parseDriverId(String driverId) {
        try {
            return Long.valueOf(driverId);
        } catch (NumberFormatException e) {
            LOG.info("Cannot parse given driver id: {}.", driverId);
        }
        return null;
    }

    private Boolean isDriverInDatabase(Long driverId) {
        final MapsDriver driver = mapsDriverDao.findById(driverId);
        return driver != null;
    }

    private void checkDriverInDatabase(Long driverId) {
        if (!isDriverInDatabase(driverId)) {
            LOG.info("There is no driver with given id: {}.", driverId);
            checkingMessages.add(AdvertsConstants.MESSAGE_INCORRECT_DRIVER_ID);
        }
    }

    private void selectMessage(AddressType type, String startDataMessage, String endDataMessage) {
        if (type.equals(AddressType.START)) {
            checkingMessages.add(startDataMessage);
        }
        if (type.equals(AddressType.END)) {
            checkingMessages.add(endDataMessage);
        }
    }

    private Boolean isInputEmpty(String input){
        return input==null || input.isEmpty();
    }

}
