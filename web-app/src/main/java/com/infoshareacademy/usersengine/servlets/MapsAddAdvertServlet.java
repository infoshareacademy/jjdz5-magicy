package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.Advert;
import com.infoshareacademy.AdvertsList;
import com.infoshareacademy.JsonToList;
import com.infoshareacademy.usersengine.adverts.AdvertPreparation;
import com.infoshareacademy.usersengine.adverts.AdvertsManager;
import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import com.infoshareacademy.usersengine.services.PropertiesService;
import com.infoshareacademy.usersengine.services.ServletService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("maps-add-advert")
public class MapsAddAdvertServlet extends AppInitServlet {

    private static final String TEMPLATE_NAME = "maps-add-advert";

    private JsonToList jsonToList = new JsonToList();
    private AdvertsList advertsList = new AdvertsList();
    private Logger LOG = LoggerFactory.getLogger(MapsAddAdvertServlet.class);

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private AdvertsManager advertsManager;

    @Inject
    private AdvertPreparation advertPreparation;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletService.setDefaultContentTypeAndEncoding(req, resp);
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put(PropertiesService.PROPERTY_API_KEY, PropertiesService.getMapsApiKey());
        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        try {
            template.process(dataModel, resp.getWriter());
            LOG.debug("Template created successfully.");
        } catch (TemplateException e) {
            LOG.error("TemplateException. Template cannot be created.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletService.setDefaultContentTypeAndEncoding(req, resp);

        List<Advert> adverts = jsonToList.jsonToList(getPath());
        advertsList.setAdvertsList(adverts);

        Map<String, String[]> map = req.getParameterMap();
        redirect(resp, advertPreparation.validateAdvertData(advertPreparation.mapReader(map)), adverts);
    }

    private String getPath(){
        ServletContext application = getServletConfig().getServletContext();
        return application.getRealPath("WEB-INF/adverts.json");
    }

    private void redirect(HttpServletResponse resp, String message, List<Advert> adverts) throws IOException {
        if(!message.isEmpty()){
            LOG.debug("Advert data is not valid.");
            PrintWriter writer = resp.getWriter();
            writer.println("<!DOCTYPE html><body><form><t1>" + message+ "</t1><br/><input type=\"button\" value=\"Go back!\" onclick=\"history.back()\"></form></body></html>");
        } else {
            Advert advertToAdd = advertPreparation.getNewAdvert(adverts);
            advertsList.setAdvertsList(advertsManager.addAdvert(advertToAdd, adverts));
            LOG.debug("Advert data is valid: {}.", advertToAdd);
            LOG.debug("Updated adverts list: " + advertsList.getAdvertsList().toString());
            advertsManager.advertsToJson(adverts, getPath());
            resp.sendRedirect("/jjdz5-magicy/home");
        }
    }

}