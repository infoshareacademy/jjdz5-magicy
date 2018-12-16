package com.infoshareacademy.usersengine.adverts;

import com.infoshareacademy.Advert;
import javax.ejb.Local;
import java.util.List;

@Local
public interface AdvertsManager {
    List<Advert> addAdvert(Advert advert,List<Advert> adverts);
    Long getNextAdvertId(List<Advert> adverts);
    Long getNextRouteId(List<Advert> adverts);
    void advertsToJson(List<Advert> advertList, String path);
}