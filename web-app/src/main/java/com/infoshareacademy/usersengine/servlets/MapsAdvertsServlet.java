package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.usersengine.dao.UserStatisticDao;
import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import com.infoshareacademy.usersengine.model.User;
import com.infoshareacademy.usersengine.services.ServletService;
import com.infoshareacademy.usersengine.services.UserStatisticService;
import com.infoshareacademy.usersengine.services.datamodels.MapsAdvertDataModel;
import com.infoshareacademy.usersengine.statistics.UserActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @Inject
    private UserStatisticDao userStatisticDao;

    @Inject
    private UserStatisticService userStatisticService;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletService.setDefaultContentTypeAndEncoding(req, resp);
        dataModel.buildNewDataModel();
        dataModel.addUserToDataModel(req);
        dataModel.fillDataModelWithGetData();

        templateProvider.build(getServletContext(), TEMPLATE_NAME_GET,
                dataModel.getDataModel(), resp);

        HttpSession session = req.getSession();
        userStatisticDao.save(userStatisticService.
                addStatistic((User) session.getAttribute("user"), UserActivity.DISPLAYING_ADVERTS));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletService.setDefaultContentTypeAndEncoding(req, resp);
        dataModel.buildNewDataModel();
        dataModel.addUserToDataModel(req);
        dataModel.fillDataModelWithPostData(req.getParameterMap());
        templateProvider.build(getServletContext(), TEMPLATE_NAME_POST,
                dataModel.getDataModel(), resp);
    }
}