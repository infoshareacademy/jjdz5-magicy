package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import com.infoshareacademy.usersengine.services.PropertiesService;
import com.infoshareacademy.usersengine.services.ServletService;
import com.infoshareacademy.usersengine.services.data_models.AddMapsAdvertDataModel;
import com.infoshareacademy.usersengine.services.maps_adverts.MapsAdvertProcessing;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@WebServlet("/add-maps-advert")
@Transactional
public class AddMapsAdvertServlet extends AppInitServlet {

    private static final String TEMPLATE_NAME = "add-maps-advert";
    private static final String SUMMARY_KEY = "SUMMARY";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private AddMapsAdvertDataModel dataModel;

    @Inject
    private MapsAdvertProcessing processing;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletService.setDefaultContentTypeAndEncoding(req, resp);
        dataModel.buildNewDataModel();
        dataModel.fillDataModelWithGetData();
        templateProvider.build(getServletContext(), TEMPLATE_NAME,
                dataModel.getDataModel(), resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletService.setDefaultContentTypeAndEncoding(req, resp);
        processing.processMapsAdvert(req.getParameterMap());
        proceed(resp, processing.getSummary());
    }

    private void proceed(HttpServletResponse resp, List<String> summary) throws IOException {
        if (ifSummaryIsSuccess(summary)) {
            resp.sendRedirect("/jjdz5-magicy/adverts");
        } else {
            dataModel.fillDataModelWithPostData(SUMMARY_KEY, processing.getSummary());
            templateProvider.build(getServletContext(), TEMPLATE_NAME,
                    dataModel.getDataModel(), resp);
        }
    }

    private Boolean ifSummaryIsSuccess(List<String> summary) {
        return summary.stream().anyMatch(msg ->
                msg.equals(PropertiesService.getMsgAdvertAddOk()));
    }

}