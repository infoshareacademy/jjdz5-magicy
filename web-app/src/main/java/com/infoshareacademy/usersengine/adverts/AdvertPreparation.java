package com.infoshareacademy.usersengine.adverts;

import com.infoshareacademy.*;
import javax.ejb.Stateful;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;;
import java.util.List;
import java.util.Map;

@Stateful
public class AdvertPreparation {
    private Advert advert = new Advert();
    private Route route = new Route();
    private Driver driver = new Driver();
    private Rating rating = new Rating();
    private AdvertsValidation advertsValidation = new AdvertsValidationBean();
    private AdvertsManager advertsManager = new AdvertsManagerBean();
    private AdvertData advertData = new AdvertData();

    public AdvertData mapReader(Map<String, String[]> map){
        String startCity = map.get("startCity")[0].trim();
        String startStreet = map.get("startStreet")[0].trim();
        String startTime = map.get("startTime")[0].trim();
        String endCity = map.get("endCity")[0].trim();
        String endStreet = map.get("endStreet")[0].trim();
        String endTime = map.get("endTime")[0].trim();
        String pickUpCity = map.get("pickUpCity")[0].trim();
        String pickUpStreet = map.get("pickUpStreet")[0].trim();
        String pickUpTime = map.get("pickUpTime")[0].trim();
        String date = map.get("date")[0].trim();

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
            message = message + "Enter correct start city  <br>";
        }
        if(!advertsValidation.askForStreet(advertData.getStartStreet())){
            message = message + "Enter correct start street <br>";
        }
        if(!advertsValidation.askForTime(advertData.getStartTime())){
            message = message + "Enter correct start time <br>";
        }
        if(!advertsValidation.askForCity(advertData.getEndCity())){
            message = message + "Enter correct end city <br>";
        }
        if(!advertsValidation.askForStreet(advertData.getEndStreet())){
            message = message + "Enter correct end street <br>";
        }
        if(!advertsValidation.askForTime(advertData.getEndTime())){
            message = message + "Enter correct end time <br>";
        }
        if(!advertData.getPickUpCity().isEmpty() && !advertsValidation.askForCity(advertData.getPickUpCity())){
            message = message + "Enter correct pick up city <br>";
        }
        if(!advertData.getPickUpStreet().isEmpty() && !advertsValidation.askForStreet(advertData.getPickUpStreet())){
            message = message + "Enter correct pick up street <br>";
        }
        if(!advertData.getPickUpTime().isEmpty() && !advertsValidation.askForTime(advertData.getPickUpTime())){
            message = message + "Enter correct pick up time <br>";
        }
        return message;
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
        System.out.println("Route to jest route "+route.toString());
        return route;
    }

    private Driver setDriverData(){
        driver.setCity("Gdańsk");
        driver.setDistrict("Wrzeszcz");
        driver.setId(4);
        driver.setName("Artur");
        driver.setSurname("Moroz");
        driver.setPhone("555000111");
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