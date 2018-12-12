package com.infoshareacademy.usersengine.services.mapsadverts;

import com.infoshareacademy.usersengine.adverts.AdvertsConstants;
import com.infoshareacademy.usersengine.dao.MapsDriverDao;
import com.infoshareacademy.usersengine.model.AdvertPartType;
import com.infoshareacademy.usersengine.model.AdvertOverallType;
import com.infoshareacademy.usersengine.services.ParametersService;
import com.infoshareacademy.usersengine.services.PropertiesService;
import com.infoshareacademy.usersengine.services.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Stateless
public class MapsAdvertVerifierBean implements MapsAdvertVerifier{

    private static final Logger LOG = LoggerFactory.getLogger(MapsAdvertVerifierBean.class);

    private List<String> errorMessages;

    @Inject
    private MapsAdvertParametersVerifier verifier;
    
    @Inject
    private MapsDriverDao mapsDriverDao;

    @Override
    public void verifyParameters(Map<String, String[]> parameters) {
        errorMessages = new ArrayList<>();
        verifyStartAddressParameters(parameters);
        verifyEndAddressParameters(parameters);
        verifyAddressesEquality(parameters);
        LocalTime startTime = verifyStartTimeParameter(parameters);
        LocalTime endTime = verifyEndTimeParameter(parameters);
        LocalDate date = verifyDate(parameters);
        verifyTimePeriod(startTime, endTime, date);
        verifyDriver(parameters);
    }

    @Override
    public Boolean areParametersCorrect() {
        return errorMessages.isEmpty();
    }

    @Override
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    private void verifyStartAddressParameters(Map<String, String[]> parameters) {
        if (verifier.isAddressParameterCorrect(parameters, AdvertPartType.START)) {
            LOG.debug("Start address main parameter is correct.");
        } else {
            LOG.debug("Start address main parameter is not correct.");
            errorMessages.add(PropertiesService.getMsgBadMainStartAddress());
        }
        if (PropertiesService.getAdvertPreciseCheck()) {
            if (verifier.isAddressPrecisionParameterCorrect(parameters, AdvertPartType.START)) {
                LOG.debug("Start address is precise.");
            } else {
                LOG.debug("Start address is not enough precise.");
                errorMessages.add(PropertiesService.getMsgBadDetailStartAddress());
            }
        }
    }

    private void verifyEndAddressParameters(Map<String, String[]> parameters) {
        if (verifier.isAddressParameterCorrect(parameters, AdvertPartType.END)) {
            LOG.debug("End address main parameter is correct.");
        } else {
            LOG.debug("End address main parameter is not correct.");
            errorMessages.add(PropertiesService.getMsgBadMainEndAddress());
        }
        if (PropertiesService.getAdvertPreciseCheck()) {
            if (verifier.isAddressPrecisionParameterCorrect(parameters, AdvertPartType.END)) {
                LOG.debug("End address is precise.");
            } else {
                LOG.debug("End address is not enough precise.");
                errorMessages.add(PropertiesService.getMsgBadDetailEndAddress());
            }
        }
    }

    private void verifyAddressesEquality(Map<String, String[]> parameters) {
        if (areAddressesEqual(parameters)) {
            errorMessages.add(PropertiesService.getMsgEqualAddresses());
        }
    }

    private LocalTime verifyStartTimeParameter(Map<String, String[]> parameters) {
        if (verifier.isTimeParameterCorrect(parameters, AdvertPartType.START)) {
            LocalTime parsedTime = parseTime(ParametersService.getSpecificParameter(parameters,
                    AdvertsConstants.PARAMETER_START_TIME));
            startTimeValidation(parsedTime);
            return parsedTime;
        } else {
            errorMessages.add(PropertiesService.getMsgBadStartTime());
            return null;
        }
    }

    private LocalTime verifyEndTimeParameter(Map<String, String[]> parameters) {
        if (verifier.isTimeParameterCorrect(parameters, AdvertPartType.END)) {
            LocalTime parsedTime = parseTime(ParametersService.getSpecificParameter(parameters,
                    AdvertsConstants.PARAMETER_END_TIME));
            endTimeValidation(parsedTime);
            return parsedTime;
        } else {
            errorMessages.add(PropertiesService.getMsgBadEndTime());
            return null;
        }
    }

    private void verifyTimePeriod(LocalTime startTime, LocalTime endTime, LocalDate date) {
        if (areDateTimesNotNull(startTime, endTime, date)) {
            timeValidation(startTime, endTime, date);
        }
    }

    private LocalDate verifyDate(Map<String, String[]> parameters) {
        if (verifier.isOverallParameterCorrect(parameters, AdvertOverallType.DATE)) {
            return dateValidation(ParametersService.getSpecificParameter(parameters,
                    AdvertsConstants.PARAMETER_DATE));
        } else {
            LOG.debug("Date is probably missing.");
            errorMessages.add(PropertiesService.getMsgBadDate());
            return null;
        }
    }

    private void verifyDriver(Map<String, String[]> parameters) {
        if (verifier.isOverallParameterCorrect(parameters, AdvertOverallType.DRIVER)) {
            driverValidation(ParametersService.getSpecificParameter(parameters,
                    AdvertsConstants.PARAMETER_DRIVER_ID));
        } else {
            errorMessages.add(PropertiesService.getMsgBadDriver());
        }
    }

    private Boolean areAddressesEqual(Map<String, String[]> parameters) {
        String startAddress = ParametersService.getSpecificParameter(parameters,
                AdvertsConstants.PARAMETER_START_MAPS_POINT_ID);
        String endAddress = ParametersService.getSpecificParameter(parameters,
                AdvertsConstants.PARAMETER_END_MAPS_POINT_ID);
        return startAddress.equals(endAddress);
    }
    
    private void startTimeValidation(LocalTime time) {
        if (isNotNull(time)) {
            LOG.debug("Start time \"{}\" is correct.", time);
        } else {
            LOG.debug("Start time is not correct.");
            errorMessages.add(PropertiesService.getMsgBadStartTime());
        }
    }
    
    private void endTimeValidation(LocalTime time) {
        if (isNotNull(time)) {
            LOG.debug("End time \"{}\" is correct.", time);
        } else {
            LOG.debug("End time is not correct.");
            errorMessages.add(PropertiesService.getMsgBadEndTime());
        }
    }

    private LocalTime parseTime(String time) {
        try {
            LOG.debug("Time {} is parsable and correct.", time);
            return LocalTime.parse(time);
        } catch (DateTimeException e) {
            LOG.debug("Time {} is not parsable.", time);
            return null;
        }
    }

    private void timeValidation(LocalTime startTime, LocalTime endTime, LocalDate date) {
        if (isStartTimeNotTooEarly(startTime, date)) {
            checkTimePeriod(startTime, endTime);
        } else {
            errorMessages.add(PropertiesService.getMsgEarlyStartTime());
        }
    }

    private Boolean isStartTimeNotTooEarly(LocalTime startTime, LocalDate date) {
        return LocalDateTime.now().plusHours(PropertiesService.getAdvertMinHoursToStart())
                .isBefore(LocalDateTime.of(date, startTime));
    }

    private void checkTimePeriod(LocalTime startTime, LocalTime endTime) {
        if (isStartTimeNotBeforeEndTime(startTime, endTime)) {
            errorMessages.add(PropertiesService.getMsgStartAfterEndTime());
        }
    }

    private Boolean isStartTimeNotBeforeEndTime(LocalTime startTime, LocalTime endTime) {
        return startTime.isAfter(endTime);
    }

    private LocalDate dateValidation(String date) {
        LocalDate parsedDate = parseDate(date);
        if (isNotNull(parsedDate) && isDateInCorrectPeriod(parsedDate)) {
            LOG.debug("Date {} is parsable and correct.", date);
            return parsedDate;
        } else {
            LOG.debug("Date {} is not parsable or in correct period of time.", date);
            errorMessages.add(PropertiesService.getMsgBadDate());
            return null;
        }
    }

    private LocalDate parseDate(String date) {
        try {
            LOG.debug("Date \"{}\" parsed correctly.", date);
            return LocalDate.parse(date);
        } catch (DateTimeException e) {
            LOG.debug("Cannot parse date \"{}\"", date);
            return null;
        }
    }

    private Boolean isDateInCorrectPeriod(LocalDate date) {
        return isDateNotBeforeNow(date) &&
               isDateNotTooDistant(date);
    }

    private Boolean isDateNotBeforeNow(LocalDate date) {
        return date.isAfter(LocalDate.now()) || date.isEqual(LocalDate.now());
    }

    private Boolean isDateNotTooDistant(LocalDate date) {
        return date.isBefore(LocalDate.now().plusDays(PropertiesService.getAdvertMaxPeriodDays()));
    }

    private void driverValidation(String driverId) {
        Long parsedDriverId = parseDriverId(driverId);
        if (isNotNull(parsedDriverId)) {
            isDriverInDatabase(parsedDriverId);
        }
    }

    private Long parseDriverId(String driverId) {
        try {
            LOG.debug("Driver id \"{}\" parsed correctly.", driverId);
            return Long.valueOf(driverId);
        } catch (NumberFormatException e) {
            LOG.debug("Cannot parse driver id \"{}\"", driverId);
            errorMessages.add(PropertiesService.getMsgBadDriver());
            return null;
        }
    }

    private void isDriverInDatabase(Long driverId) {
        if (Optional.ofNullable(mapsDriverDao.findById(driverId)).isPresent()) {
            LOG.debug("Driver with id \"{}\" found in database.", driverId);
        } else {
            LOG.debug("Driver with id \"{}\" does not exist in database.", driverId);
            errorMessages.add(PropertiesService.getMsgBadDriver());
        }
    }

    private <T> Boolean isNotNull(T objectToAnalyze) {
        return objectToAnalyze != null;
    }

    private Boolean areDateTimesNotNull(LocalTime startTime, LocalTime endTime, LocalDate date) {
        return isNotNull(startTime) && isNotNull(endTime) && isNotNull(date);
    }
}
