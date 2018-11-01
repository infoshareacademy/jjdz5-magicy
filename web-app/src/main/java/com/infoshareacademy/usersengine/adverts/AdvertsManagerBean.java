package com.infoshareacademy.usersengine.adverts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.Advert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Stateless
public class AdvertsManagerBean implements AdvertsManager {

    private static final Logger LOG = LoggerFactory.getLogger(AdvertsManagerBean.class);
    private final Integer valueToAdd = 1;

    public List<Advert> addAdvert(Advert advert, List<Advert> adverts){
        adverts.add(advert);
        return adverts;
    }

    public Integer getNextAdvertId(List<Advert> adverts){
        Integer nextAdvertId = adverts.stream().mapToInt(Advert::getId).max().getAsInt() + valueToAdd;
        LOG.debug("Next advert id value: " + nextAdvertId + ".");
        return nextAdvertId;
    }

    public Integer getNextRouteId(List<Advert> adverts){
        Integer nextRouteId = adverts.stream().mapToInt(advert -> advert.getRoute().getId()).max()
                .getAsInt() + valueToAdd;
        LOG.debug("Next route id value: " + nextRouteId + ".");
        return nextRouteId;
    }

    public void advertsToJson(List<Advert> advertList, String path){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String arrayToJson = objectMapper.writeValueAsString(advertList);
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(arrayToJson);
            writer.close();
        } catch (IOException e) {
            LOG.warn("IOException in advertsToJson method.");
        }
    }

}

