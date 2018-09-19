package com.infoshareacademy.usersengine.adverts;

import com.infoshareacademy.Advert;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AdvertsManager {
    List<Advert> jsonToList (String path);
    List<Advert> addAdvert(Advert advert);
    void listToJson();
    List<Advert> getAdverts();
    void setAdverts(List<Advert> adverts);
    Integer getNextAdvertId();
    Integer getNextRouteId();
}
