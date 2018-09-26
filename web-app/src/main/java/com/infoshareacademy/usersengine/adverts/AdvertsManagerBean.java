package com.infoshareacademy.usersengine.adverts;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.Advert;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ejb.Stateful;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Stateful
public class AdvertsManagerBean implements AdvertsManager {

    public List<Advert> addAdvert(Advert advert, List<Advert> adverts){
        adverts.add(advert);
        return adverts;
    }

    public Integer getNextAdvertId(List<Advert> adverts){
        Integer idMax = 0;
        for(Advert advert: adverts){
            if (advert.getId() > idMax) {
                idMax = advert.getId();
            }
        }
        return idMax+1;
    }
    public Integer getNextRouteId(List<Advert> adverts){
        Integer idMax = 0;
        for(Advert advert: adverts){
            if (advert.getRoute().getId() > idMax) {
                idMax = advert.getId();
            }
        }
        return idMax+1;
    }

    public void advertsToJson(List<Advert> advertList, String path){
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

    public List<Advert> jsonToList (String path) {
        ObjectMapper objectMapper = new ObjectMapper();

        JSONParser parser = new JSONParser();
        List<Advert> advertList = new ArrayList<>();
        try {

            JSONArray adverts = (JSONArray) parser.parse(new FileReader(path));
            String jsonAdvertArray = adverts.toString();

            advertList = objectMapper.readValue(jsonAdvertArray, new TypeReference<List<Advert>>(){});

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return advertList;

    }

}
