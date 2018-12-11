package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import com.infoshareacademy.usersengine.services.ServletService;
import com.infoshareacademy.usersengine.services.datamodels.MapsAdvertDataModel;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

@WebServlet(urlPatterns = "/maps-adverts")
@Transactional
public class MapsAdvertsServlet extends AppInitServlet {

    private static final String TEMPLATE_NAME_GET = "maps-adverts";
    private static final String TEMPLATE_NAME_POST = "maps-adverts-details";

    @Inject
    private MapsAdvertDataModel dataModel;

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletService.setDefaultContentTypeAndEncoding(req, resp);
        dataModel.buildNewDataModel();
        dataModel.fillDataModelWithGetData();
        templateProvider.build(getServletContext(), TEMPLATE_NAME_GET,
                dataModel.getDataModel(), resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletService.setDefaultContentTypeAndEncoding(req, resp);
        dataModel.buildNewDataModel();
        dataModel.fillDataModelWithPostData(req.getParameterMap());
        templateProvider.build(getServletContext(), TEMPLATE_NAME_POST,
                dataModel.getDataModel(), resp);
    }
}