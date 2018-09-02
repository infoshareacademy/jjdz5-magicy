package com.infoshareacademy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonToList {

    public List<Advert> jsonToList (String path) {
        ObjectMapper objectMapper = new ObjectMapper();

        JSONParser parser = new JSONParser();
        List<Advert> advertList = new ArrayList<>();
        System.out.println("PATH222: " + path);
//        try {
//
////            JSONArray adverts = (JSONArray) parser.parse(new FileReader(path));
////            String jsonAdvertArray = adverts.toString();
////
////            advertList = objectMapper.readValue(jsonAdvertArray, new TypeReference<List<Advert>>(){});
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        System.out.println("RETURN: " + advertList);
        return advertList;

    }

    public List<Driver> driversToList (String path) {
        ObjectMapper objectMapper = new ObjectMapper();

        JSONParser parser = new JSONParser();
        List<Driver> driversList = null;

        try {
            JSONArray drivers = (JSONArray) parser.parse(new FileReader(path));
            String jsonDriverArray = drivers.toString();

            driversList = objectMapper.readValue(jsonDriverArray, new TypeReference<List<Driver>>(){});

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return driversList;

    }

}

