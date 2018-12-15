package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.*;
import com.infoshareacademy.usersengine.adverts.AdvertPreparation;
import com.infoshareacademy.usersengine.adverts.AdvertsManager;
import com.infoshareacademy.usersengine.dao.UserStatisticDao;
import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import com.infoshareacademy.usersengine.model.User;
import com.infoshareacademy.usersengine.services.UserStatisticService;
import com.infoshareacademy.usersengine.statistics.UserActivity;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("add-advert")
public class AddAdvertServlet extends HttpServlet {

    private JsonToList jsonToList = new JsonToList();
    private AdvertsList advertsList = new AdvertsList();
    private Logger LOG = LoggerFactory.getLogger(AddAdvertServlet.class);

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private AdvertsManager advertsManager;

    @Inject
    private AdvertPreparation advertPreparation;

    @Inject
    private UserStatisticDao userStatisticDao;

    @Inject
    private UserStatisticService userStatisticService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> dataModel = new HashMap<>();
        HttpSession session = req.getSession();
        dataModel.put("user", session.getAttribute("user"));
        Template template = templateProvider.getTemplate(getServletContext(), "add-advert");
        try{
            template.process(dataModel, resp.getWriter());
            LOG.debug("Template created successfully.");
        }catch (TemplateException e){
            LOG.error("TemplateException. Template cannot be created.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        List<Advert> adverts = jsonToList.jsonToList(getPath());
        advertsList.setAdvertsList(adverts);

        Map<String, String[]> map = req.getParameterMap();

        advertsList.setAdvertsList(advertsManager.addAdvert(advertPreparation.getNewAdvert(adverts, advertPreparation.mapReader(map)), adverts));
        advertsManager.advertsToJson(adverts, getPath());

        HttpSession session = req.getSession();
        userStatisticDao.save(userStatisticService.
                addStatistic((User)session.getAttribute("user"), UserActivity.ADDING_ADVERT));
    }

    private String getPath(){
        ServletContext application = getServletConfig().getServletContext();
        return application.getRealPath("WEB-INF/adverts.json");
    }
}