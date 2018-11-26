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
    private static final Integer START_ID = 0;
    private static final Integer VALUE_TO_ADD = 1;

    public List<Advert> addAdvert(Advert advert, List<Advert> adverts){
        adverts.add(advert);
        return adverts;
    }

    public Long getNextAdvertId(List<Advert> adverts){
        Long nextAdvertId = adverts.stream().mapToLong(Advert::getId).max()
                .orElse(START_ID) + VALUE_TO_ADD;
        LOG.debug("Next advert id value: {}.", nextAdvertId);
        return nextAdvertId;
    }

    public Long getNextRouteId(List<Advert> adverts){
        Long nextRouteId = adverts.stream().mapToLong(advert -> advert.getRoute().getId()).max()
                .orElse(START_ID) + VALUE_TO_ADD;
        LOG.debug("Next route id value: {}.", nextRouteId);
        return nextRouteId;
    }

    public void advertsToJson(List<Advert> advertList, String path){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String arrayToJson = objectMapper.writeValueAsString(advertList);
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(arrayToJson);
            writer.close();
            LOG.info("Writing adverts to JSON file successful.");
        } catch (IOException e) {
            LOG.warn("IOException in advertsToJson method.");
        }
    }

}

