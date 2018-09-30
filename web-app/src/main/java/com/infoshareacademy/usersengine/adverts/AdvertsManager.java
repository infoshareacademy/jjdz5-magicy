package com.infoshareacademy.usersengine.adverts;

import com.infoshareacademy.Advert;
import javax.ejb.Local;
import java.util.List;

@Local
public interface AdvertsManager {
    List<Advert> addAdvert(Advert advert,List<Advert> adverts);
    Integer getNextAdvertId(List<Advert> adverts);
    Integer getNextRouteId(List<Advert> adverts);
    void advertsToJson(List<Advert> advertList, String path);
}
