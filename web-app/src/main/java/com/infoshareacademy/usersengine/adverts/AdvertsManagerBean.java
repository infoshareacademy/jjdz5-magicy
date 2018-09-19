package com.infoshareacademy.usersengine.adverts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.Advert;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class AdvertsManagerBean implements AdvertsManager {
    public List<Advert> adverts = new ArrayList<>();

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
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

    public List<Advert> addAdvert(Advert advert){
        this.adverts.add(advert);
        return this.adverts;
    }

    public void listToJson(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String arrayToJson = objectMapper.writeValueAsString(this.adverts);
            BufferedWriter writer = new BufferedWriter(new FileWriter("/adverts.json"));
            writer.write(arrayToJson);
            writer.close();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer getNextAdvertId(){
        Integer idMax = 0;
        for(Advert advert: this.adverts){
            if (advert.getId() > idMax) {
                idMax = advert.getId();
            }
        }
        return idMax+1;
    }
    public Integer getNextRouteId(){
        Integer idMax = 0;
        for(Advert advert: this.adverts){
            if (advert.getRoute().getId() > idMax) {
                idMax = advert.getId();
            }
        }
        return idMax+1;
    }

}
