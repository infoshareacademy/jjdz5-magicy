package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.usersengine.dao.MapsAdvertDao;
import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import com.infoshareacademy.usersengine.model.MapsAdvert;
import com.infoshareacademy.usersengine.services.ParametersService;
import com.infoshareacademy.usersengine.services.PropertiesService;
import com.infoshareacademy.usersengine.services.Property;
import com.infoshareacademy.usersengine.services.ServletService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/maps-adverts")
@Transactional
public class MapsAdvertsServlet extends AppInitServlet {

    private Map<String, List> dataModel;

    @Inject
    private MapsAdvertDao mapsAdvertDao;

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletService.setDefaultContentTypeAndEncoding(req, resp);
        List<MapsAdvert> adverts = mapsAdvertDao.findAll();

        dataModel = new HashMap<>();
        dataModel.put(Property.API_KEY.name(),
                Collections.singletonList(PropertiesService.getMapsApiKey()));
        dataModel.put("ADVERTS", adverts);
        templateProvider.build(getServletContext(), "maps-adverts", dataModel, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletService.setDefaultContentTypeAndEncoding(req, resp);
        Long detailAdvertId = Long.valueOf(ParametersService.getSpecificParameter(
                req.getParameterMap(), "advertId"));
        dataModel = new HashMap<>();
        dataModel.put(Property.API_KEY.name(),
                Collections.singletonList(PropertiesService.getMapsApiKey()));
        dataModel.put("DETAIL_ADVERT", Collections.singletonList(
                mapsAdvertDao.findById(detailAdvertId)));
        templateProvider.build(getServletContext(), "maps-adverts-details",
                dataModel, resp);
    }
}