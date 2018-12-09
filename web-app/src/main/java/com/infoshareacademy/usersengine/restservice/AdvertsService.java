package com.infoshareacademy.usersengine.restservice;

import com.infoshareacademy.usersengine.dao.MapsAdvertDao;
import com.infoshareacademy.usersengine.model.MapsAdvert;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path(value = "/")
public class AdvertsService extends RestService {

    private static final String ADVERT_IDENTIFIER = "Advert #";

    @Inject
    private MapsAdvertDao mapsAdvertDao;

    @GET
    @Path(value = "/adverts")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAllAdverts() {
        return sendResultResponse(prepareResponse(mapsAdvertDao.findAll()));
    }

    private Map<String, MapsAdvert> prepareResponse(List<MapsAdvert> adverts) {
        Map<String, MapsAdvert> advertsMap = new HashMap<>();
        adverts.forEach(a -> advertsMap.put(prepareIdentifier(a.getId()), a));
        return advertsMap;
    }

    private String prepareIdentifier(Long id) {
        StringBuilder sb = new StringBuilder();
        sb.append(ADVERT_IDENTIFIER).append(String.valueOf(id));
        return sb.toString();
    }

}
