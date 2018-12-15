package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.usersengine.dao.MapsAdvertDao;
import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import com.infoshareacademy.usersengine.model.MapsAdvert;
import com.infoshareacademy.usersengine.services.ServletService;
import com.infoshareacademy.usersengine.services.datamodels.MapsAdvertDataModel;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

@WebServlet(urlPatterns = "/adverts-admin")
@Transactional
public class MapsAdvertsAdminServlet extends AppInitServlet {

    private static final String TEMPLATE_NAME_GET = "maps-adverts-admin";

    @Inject
    private MapsAdvertDataModel dataModel;

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private MapsAdvertDao mapsAdvertDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletService.setDefaultContentTypeAndEncoding(req, resp);
        dataModel.buildNewDataModel();
        dataModel.addUserToDataModel(req);
        dataModel.fillDataModelWithGetData();
        templateProvider.build(getServletContext(), TEMPLATE_NAME_GET,
                dataModel.getDataModel(), resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(req.getParameter("id"));
       mapsAdvertDao.delete(id);
        resp.sendRedirect("/jjdz5-magicy/adverts-admin");
    }
}