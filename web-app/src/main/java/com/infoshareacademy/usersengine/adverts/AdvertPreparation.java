package com.infoshareacademy.usersengine.adverts;

import com.infoshareacademy.Advert;
import com.infoshareacademy.Driver;
import com.infoshareacademy.Rating;
import com.infoshareacademy.Route;
import com.infoshareacademy.usersengine.drivers.DriversConstants;
import com.infoshareacademy.usersengine.services.ParametersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateful;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Stateful
public class AdvertPreparation {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final Logger LOG = LoggerFactory.getLogger(AdvertPreparation.class);
    private Advert advert = new Advert();
    private Route route = new Route();
    private Driver driver = new Driver();
    private Rating rating = new Rating();
    private AdvertsValidation advertsValidation = new AdvertsValidationBean();
    private AdvertsManager advertsManager = new AdvertsManagerBean();
    private AdvertData advertData = new AdvertData();

    public AdvertData mapReader(Map<String, String[]> map) {
        String startCity = ParametersService.getSpecificParameter(map, AdvertsConstants.PARAMETER_START_CITY);
        String startStreet = ParametersService.getSpecificParameter(map, AdvertsConstants.PARAMETER_START_STREET);
        String startTime = ParametersService.getSpecificParameter(map, AdvertsConstants.PARAMETER_START_TIME);
        String endCity = ParametersService.getSpecificParameter(map, AdvertsConstants.PARAMETER_END_CITY);
        String endStreet = ParametersService.getSpecificParameter(map, AdvertsConstants.PARAMETER_END_STREET);
        String endTime = ParametersService.getSpecificParameter(map, AdvertsConstants.PARAMETER_END_TIME);
        String pickUpCity = ParametersService.getSpecificParameter(map, AdvertsConstants.PARAMETER_PICK_UP_CITY);
        String pickUpStreet = ParametersService.getSpecificParameter(map, AdvertsConstants.PARAMETER_PICK_UP_STREET);
        String pickUpTime = ParametersService.getSpecificParameter(map, AdvertsConstants.PARAMETER_PICK_UP_TIME);
        String date = ParametersService.getSpecificParameter(map, AdvertsConstants.PARAMETER_DATE);

        advertData.setStartCity(startCity);
        advertData.setStartStreet(startStreet);
        advertData.setStartTime(startTime);
        advertData.setEndCity(endCity);
        advertData.setEndStreet(endStreet);
        advertData.setEndTime(endTime);
        advertData.setPickUpCity(pickUpCity);
        advertData.setPickUpStreet(pickUpStreet);
        advertData.setPickUpTime(pickUpTime);
        advertData.setDate(date);

        return advertData;
    }

    public String validateAdvertData(AdvertData advertData) {
        String message = DriversConstants.EMPTY_FIELD;
        if(!advertsValidation.askForDate(advertData.getDate())) {
            LOG.info("Incorrect date from user input: {}.", advertData.getDate());
            message += AdvertsConstants.MESSAGE_INCORRECT_DATE;
        }
        if(!advertsValidation.askForCity(advertData.getStartCity())) {
            LOG.info("Incorrect start city from user input: {}.", advertData.getStartCity());
            message += AdvertsConstants.MESSAGE_INCORRECT_START_CITY;
        }
        if(!advertsValidation.askForStreet(advertData.getStartStreet())) {
            LOG.info("Incorrect start street from user input: {}.", advertData.getStartStreet());
            message += AdvertsConstants.MESSAGE_INCORRECT_START_STREET;
        }
        if(!advertsValidation.askForTime(advertData.getStartTime())) {
            LOG.info("Incorrect start time from user input: {}.", advertData.getStartTime());
            message += AdvertsConstants.MESSAGE_INCORRECT_START_TIME;
        }
        if(!advertsValidation.askForCity(advertData.getEndCity())) {
            LOG.info("Incorrect end city from user input: {}.", advertData.getEndCity());
            message += AdvertsConstants.MESSAGE_INCORRECT_END_CITY;
        }
        if(!advertsValidation.askForStreet(advertData.getEndStreet())) {
            LOG.info("Incorrect end street from user input: {}.", advertData.getEndStreet());
            message += AdvertsConstants.MESSAGE_INCORRECT_END_STREET;
        }
        if(!advertsValidation.askForTime(advertData.getEndTime())) {
            LOG.info("Incorrect end time from user input: {}.", advertData.getEndTime());
            message += AdvertsConstants.MESSAGE_INCORRECT_END_TIME;
        }
        if(!advertData.getPickUpCity().isEmpty() && !advertsValidation.askForCity(
                advertData.getPickUpCity())) {
            LOG.info("Pick up city from user input is present but incorrect: {}.",
                    advertData.getPickUpCity());
            message += AdvertsConstants.MESSAGE_INCORRECT_PICK_UP_CITY;
        }
        if(!advertData.getPickUpStreet().isEmpty() && !advertsValidation.askForStreet(
                advertData.getPickUpStreet())) {
            LOG.info("Pick up street from user input is present but incorrect: {}.",
                    advertData.getPickUpStreet());
            message += AdvertsConstants.MESSAGE_INCORRECT_PICK_UP_STREET;
        }
        if(!advertData.getPickUpTime().isEmpty() && !advertsValidation.askForTime(
                advertData.getPickUpTime())) {
            LOG.info("Pick up time from user input is present but incorrect: {}.",
                    advertData.getPickUpTime());
            message += AdvertsConstants.MESSAGE_INCORRECT_PICK_UP_TIME;
        }
        return message;
    }

    public Advert getNewAdvert(List<Advert> adverts) {
        advert.setId(advertsManager.getNextAdvertId(adverts));
        advert.setDriver(setDriverData());
        advert.setRoute(setRouteData(adverts));
        advert.setDate(new Date());
        return advert;
    }

    private Route setRouteData(List<Advert> adverts) {
        try {
            route.setDate(new SimpleDateFormat(DATE_FORMAT).parse(advertData.getDate()));
        } catch (ParseException e) {
            LOG.warn("ParseException in setRouteData method.");
        }
        route.setId(advertsManager.getNextRouteId(adverts));
        route.setStartCity(advertData.getStartCity());
        route.setStartStreet(advertData.getStartStreet());
        route.setStartTime(advertData.getStartTime());
        route.setEndCity(advertData.getEndCity());
        route.setEndStreet(advertData.getEndStreet());
        route.setEndTime(advertData.getEndTime());
        route.setPickUpCity(advertData.getPickUpCity());
        route.setPickUpStreet(advertData.getPickUpStreet());
        route.setPickUpTime(advertData.getPickUpTime());
        return route;
    }

    private Driver setDriverData(){
        driver.setCity("Gdańsk");
        driver.setDistrict("Wrzeszcz");
        driver.setId(4L);
        driver.setName("Krzysztof");
        driver.setSurname("Gotowała");
        driver.setPhone("555 555 555");
        driver.setRating(setRatingData());
        return driver;
    }

    private Rating setRatingData(){
        rating.setAverage(4.5);
        rating.setPersons(2);
        return rating;
    }
}