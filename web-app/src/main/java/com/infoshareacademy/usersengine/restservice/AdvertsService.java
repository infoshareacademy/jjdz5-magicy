package com.infoshareacademy.usersengine.restservice;

import com.infoshareacademy.usersengine.dao.MapsAdvertDao;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(value = "/")
public class AdvertsService extends RestService{

    @Inject
    private MapsAdvertDao mapsAdvertDao;

    @GET
    @Path(value = "/adverts")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAllAdverts() {
        return sendResultResponse(mapsAdvertDao.findAll());
    }

}
