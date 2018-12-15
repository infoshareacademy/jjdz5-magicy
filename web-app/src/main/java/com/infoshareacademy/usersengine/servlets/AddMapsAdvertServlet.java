package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.usersengine.dao.UserStatisticDao;
import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import com.infoshareacademy.usersengine.model.MapsDriver;
import com.infoshareacademy.usersengine.model.User;
import com.infoshareacademy.usersengine.services.PropertiesService;
import com.infoshareacademy.usersengine.services.RedirectionService;
import com.infoshareacademy.usersengine.services.ServletService;
import com.infoshareacademy.usersengine.services.UserStatisticService;
import com.infoshareacademy.usersengine.services.datamodels.AddMapsAdvertDataModel;
import com.infoshareacademy.usersengine.services.mapsadverts.MapsAdvertProcessing;
import com.infoshareacademy.usersengine.statistics.UserActivity;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@WebServlet("/add-maps-advert")
@Transactional
public class AddMapsAdvertServlet extends AppInitServlet {

    private static final String TEMPLATE_NAME = "add-maps-advert";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private AddMapsAdvertDataModel dataModel;

    @Inject
    private MapsAdvertProcessing processing;

    @Inject
    private UserStatisticService userStatisticService;

    @Inject
    private UserStatisticDao userStatisticDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletService.setDefaultContentTypeAndEncoding(req, resp);
        dataModel.buildNewDataModel();
        dataModel.addUserToDataModel(req);
        User currentUser = (User) req.getSession().getAttribute("user");
        MapsDriver currentDriver = currentUser.getDriver();
        dataModel.fillDataModelWithCurrentDriver(currentDriver);
        dataModel.fillDataModelWithGetData();
        templateProvider.build(getServletContext(), TEMPLATE_NAME,
                dataModel.getDataModel(), resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletService.setDefaultContentTypeAndEncoding(req, resp);
        processing.processMapsAdvertCreation(req.getParameterMap());
        HttpSession session = req.getSession();
        userStatisticDao.save(userStatisticService.
                addStatistic((User)session.getAttribute("user"), UserActivity.ADDING_ADVERT));

        proceed(resp, req);
    }

    private void proceed(HttpServletResponse resp, HttpServletRequest req) throws IOException {
        dataModel.buildNewDataModel();
        dataModel.addUserToDataModel(req);
        User currentUser = (User) req.getSession().getAttribute("user");
        MapsDriver currentDriver = currentUser.getDriver();
        dataModel.fillDataModelWithCurrentDriver(currentDriver);
        if (ifSummaryIsSuccess(processing.getSummary())) {
            resp.getWriter().write(RedirectionService.buildAddAdvertRedirectionPage(
                    PropertiesService.getMsgAdvertAddOk(),
                    processing.getBuildedAdvertId(),
                    PropertiesService.getRedirectionDelay()));
        } else {
            dataModel.fillDataModelWithPostData(processing.getSummary());
            templateProvider.build(getServletContext(), TEMPLATE_NAME,
                    dataModel.getDataModel(), resp);
        }
    }

    private Boolean ifSummaryIsSuccess(List<String> summary) {
        return summary.stream().anyMatch(msg ->
                msg.equals(PropertiesService.getMsgAdvertAddOk()));
    }

}