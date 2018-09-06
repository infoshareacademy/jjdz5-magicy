package com.infoshareacademy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdvertManager {
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private final ReadProperties readProperties = new ReadProperties();
    private String path = readProperties.readFilePath();

    public AdvertManager() {
    }


    static void showAdverts(List<Advert> advertsList) {
        for (Advert advert: advertsList) {
            showOneAdvert(advert);
        }
    }

    static void showOneAdvert(Advert advert){

        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Advert number: " + advert.getId() +
                "\n Date: " + formatter.format(advert.getRoute().getDate()) +
                "\n Route start point: " + advert.getRoute().getStartCity() + ", " + advert.getRoute().getStartStreet() + " at " + advert.getRoute().getStartTime() +
                "\n Route end point: " + advert.getRoute().getEndCity() + ", " + advert.getRoute().getEndStreet() + " at " + advert.getRoute().getEndTime() +
                "\n Pick up point: " + advert.getRoute().getPickUpCity() + ", " + advert.getRoute().getPickUpStreet() + " at " + advert.getRoute().getPickUpTime() +
                "\n Driver: " +advert.getDriver().getName()+" "+advert.getDriver().getSurname()+", phone number: "+ advert.getDriver().getPhone());

        System.out.println("------------");

    }

     List<Advert> addAdvert(List<Advert> advertList) {
        final UserInput user = new UserInput();

        String startCity = user.askForText("Route start city");
        String startStreet = user.askForText("Route start street");
        String startTime = user.askForTime("At time (HH:mm)");
        String endCity = user.askForText("Route end city");
        String endStreet = user.askForText("Route end street");
        String endTime = user.askForTime("At time (HH:mm)");
        String pickUpCity = user.askForText("Enter pick up city");
        String pickUpStreet = user.askForText("Enter pick up street");
        String pickUpTime = user.askForTime("At time (HH:mm)");
        Date date = user.askForDate("Enter date in format (dd-mm-yyyy)");


        Route route = new Route( getMaxIdList(advertList, 3) + 1, date, startCity, startStreet, endCity,
                endStreet, pickUpCity, pickUpStreet, startTime, endTime, pickUpTime);

        Rating rating = new Rating(4.5, 2);

        Driver driver = new Driver("Artur", "Moroz", "555000111", "Gdańsk", "Wrzeszcz", rating, 4);

        Advert advert = new Advert(getMaxIdList(advertList, 1) + 1, new Date(), driver, route);

        advertList.add(advert);
        writeAdvertData(advertList);

        showAdverts(advertList);

        return advertList;
    }

    public void writeAdvertData(List<Advert> advertList){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String arrayToJson = objectMapper.writeValueAsString(advertList);
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(arrayToJson);
            writer.close();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static Integer getMaxIdList(List<Advert> adverts, Integer num) {
        Integer idMax = 0;
        for (Advert advert : adverts) {

            if (num == 1 && advert.getId() > idMax) {
                idMax = advert.getId();
            }
            if (num == 2 && advert.getDriver().getId() > idMax) {
                idMax = advert.getDriver().getId();
            }
            if (num == 3 && advert.getRoute().getId() > idMax) {
                idMax = advert.getRoute().getId();
            }

        }
        return idMax;
    }
}


