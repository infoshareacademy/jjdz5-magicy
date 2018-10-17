package com.infoshareacademy.usersengine.adverts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.Advert;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.io.*;
import java.util.List;

@Stateless
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
                idMax = advert.getRoute().getId();
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

