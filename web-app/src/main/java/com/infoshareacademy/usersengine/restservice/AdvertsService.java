package com.infoshareacademy.usersengine.restservice;

import com.infoshareacademy.usersengine.dao.MapsAdvertDao;
import com.infoshareacademy.usersengine.model.MapsAdvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Path(value = "/")
public class AdvertsService extends RestService {

    private static final Logger LOG = LoggerFactory.getLogger(AdvertsService.class);

    @Inject
    private MapsAdvertDao mapsAdvertDao;

    @GET
    @Path(value = "/adverts")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAllAdverts() {
        List<MapsAdvert> adverts = mapsAdvertDao.findAll();
        logAdvertsNumber(adverts.size());
        return sendResultResponse(adverts);
    }

    @GET
    @Path(value = "/adverts/date")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAdvertsByDate(@QueryParam(value = "value") String value) {
        try {
            return processAdvertsByDateRequest(value);
        } catch (DateTimeParseException e) {
            return processAdvertsByDateRequestParseFailure(value);
        }
    }

    @GET
    @Path(value = "/adverts/startcity")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAdvertsByStartCity(@QueryParam(value = "value") String value) {
        List<MapsAdvert> adverts = mapsAdvertDao.findAdvertsByStartCity(value);
        logAdvertsNumber(adverts.size());
        return sendResultResponse(adverts);
    }

    @GET
    @Path(value = "/adverts/endcity")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAdvertsByEndCity(@QueryParam(value = "value") String value) {
        List<MapsAdvert> adverts = mapsAdvertDao.findAdvertsByEndCity(value);
        logAdvertsNumber(adverts.size());
        return sendResultResponse(adverts);
    }

    private void logAdvertsNumber(Integer responseSize) {
        LOG.info("Number of adverts available for this request: {}.", responseSize);
    }

    private Response processAdvertsByDateRequest(String date) {
        LocalDate requestDate = LocalDate.parse(date);
        List<MapsAdvert> adverts = mapsAdvertDao.findAdvertsByDate(requestDate);
        logAdvertsNumber(adverts.size());
        return sendResultResponse(adverts);
    }

    private Response processAdvertsByDateRequestParseFailure(String date) {
        LOG.warn("Cannot parse date from query parameter: {}.", date);
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
