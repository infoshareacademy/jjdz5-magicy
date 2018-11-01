package com.infoshareacademy.usersengine.adverts;

import com.infoshareacademy.Advert;
import com.infoshareacademy.Driver;
import com.infoshareacademy.Rating;
import com.infoshareacademy.Route;
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

    private static final Integer PARAMETER_INDEX = 0;
    private static final Logger LOG = LoggerFactory.getLogger(AdvertPreparation.class);
    private Advert advert = new Advert();
    private Route route = new Route();
    private Driver driver = new Driver();
    private Rating rating = new Rating();
    private AdvertsValidation advertsValidation = new AdvertsValidationBean();
    private AdvertsManager advertsManager = new AdvertsManagerBean();
    private AdvertData advertData = new AdvertData();

    public AdvertData mapReader(Map<String, String[]> map){
        String startCity = getSpecificParameter(map, AdvertsConstants.PARAMETER_START_CITY);
        String startStreet = getSpecificParameter(map, AdvertsConstants.PARAMETER_START_STREET);
        String startTime = getSpecificParameter(map, AdvertsConstants.PARAMETER_START_TIME);
        String endCity = getSpecificParameter(map, AdvertsConstants.PARAMETER_END_CITY);
        String endStreet = getSpecificParameter(map, AdvertsConstants.PARAMETER_END_STREET);
        String endTime = getSpecificParameter(map, AdvertsConstants.PARAMETER_END_TIME);
        String pickUpCity = getSpecificParameter(map, AdvertsConstants.PARAMETER_PICK_UP_CITY);
        String pickUpStreet = getSpecificParameter(map, AdvertsConstants.PARAMETER_PICK_UP_STREET);
        String pickUpTime = getSpecificParameter(map, AdvertsConstants.PARAMETER_PICK_UP_TIME);
        String date = getSpecificParameter(map, AdvertsConstants.PARAMETER_DATE);

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

    public String validateAdvertData(AdvertData advertData){
        String message = "";
        if(!advertsValidation.askForDate(advertData.getDate())){

            message = message + "The entered date must be later than today and no later than a month after today. Enter correct date<br>";
        }
        if(!advertsValidation.askForCity(advertData.getStartCity())){
            message = message + "Enter correct departure city <br>";
        }
        if(!advertsValidation.askForStreet(advertData.getStartStreet())){
            message = message + "Enter correct departure street <br>";
        }
        if(!advertsValidation.askForTime(advertData.getStartTime())){
            message = message + "Enter correct departure time <br>";
        }
        if(!advertsValidation.askForCity(advertData.getEndCity())){
            message = message + "Enter correct arrival city <br>";
        }
        if(!advertsValidation.askForStreet(advertData.getEndStreet())){
            message = message + "Enter correct arrival street <br>";
        }
        if(!advertsValidation.askForTime(advertData.getEndTime())){
            message = message + "Enter correct arrival time <br>";
        }
        if(!advertData.getPickUpCity().isEmpty() && !advertsValidation.askForCity(advertData.getPickUpCity())){
            message = message + "Enter correct city where you can make a stop <br>";
        }
        if(!advertData.getPickUpStreet().isEmpty() && !advertsValidation.askForStreet(advertData.getPickUpStreet())){
            message = message + "Enter correct street where you can make a stop <br>";
        }
        if(!advertData.getPickUpTime().isEmpty() && !advertsValidation.askForTime(advertData.getPickUpTime())){
            message = message + "Enter correct time when you can make a stop <br>";
        }
        return message;
    }

    private String getSpecificParameter (Map<String, String[]> map, String parameter) {
        return map.get(parameter)[PARAMETER_INDEX].trim();
    }

    private Route setRouteData(List<Advert> adverts){
        try {
            route.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(advertData.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
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
        driver.setId(4);
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

    public Advert getNewAdvert(List<Advert> adverts){
        advert.setId(advertsManager.getNextAdvertId(adverts));
        advert.setDriver(setDriverData());
        advert.setRoute(setRouteData(adverts));
        advert.setDate(new Date());
        return advert;
    }
}