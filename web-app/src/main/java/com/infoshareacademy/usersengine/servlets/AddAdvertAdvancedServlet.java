package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.usersengine.adverts.MapsAdvertPreparation;
import com.infoshareacademy.usersengine.dao.MapsAdvertDao;
import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import com.infoshareacademy.usersengine.model.MapsAdvert;
import com.infoshareacademy.usersengine.services.PropertiesService;
import com.infoshareacademy.usersengine.services.ServletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/add-advert-advanced")
public class AddAdvertAdvancedServlet extends AppInitServlet {

    private static final String TEMPLATE_NAME = "add-advert-advanced";

    private Logger LOG = LoggerFactory.getLogger(AddAdvertAdvancedServlet.class);

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private MapsAdvertPreparation preparation;

    @Inject
    private MapsAdvertDao mapsAdvertDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletService.setDefaultContentTypeAndEncoding(req, resp);
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put(PropertiesService.PROPERTY_API_KEY, PropertiesService.getMapsApiKey());

        templateProvider.build(getServletContext(), TEMPLATE_NAME, dataModel, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletService.setDefaultContentTypeAndEncoding(req, resp);
        req.getParameterMap().entrySet().forEach(e ->
        {
            try {
                resp.getWriter().write(e.getKey() + "=" + e.getValue()[0] + "<br>");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void buildNewAdvert(Map<String, String[]> parametersMap, HttpServletResponse resp)
            throws IOException {
        MapsAdvert mapsAdvert = preparation.buildMapsAdvert(parametersMap);
        if (mapsAdvert == null) {
            List<String> errors = preparation.getValidationSummary();
            resp.getWriter().write("<!DOCTYPE html><body>");
            resp.getWriter().write("ZŁO");
            for (String error : errors) {
                resp.getWriter().write(error);
            }
            resp.getWriter().write("</body></html>");
        } else {
            resp.getWriter().write(mapsAdvert.toString());
            mapsAdvertDao.save(mapsAdvert);
            resp.sendRedirect("/jjdz5-magicy/home");
        }
    }

}