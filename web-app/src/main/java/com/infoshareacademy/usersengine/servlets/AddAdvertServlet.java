package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.*;
import com.infoshareacademy.usersengine.adverts.AdvertPreparation;
import com.infoshareacademy.usersengine.adverts.AdvertsManager;
import com.infoshareacademy.usersengine.adverts.AdvertsValidation;
import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.*;

@WebServlet("add-advert")
public class AddAdvertServlet extends HttpServlet {

    private JsonToList jsonToList = new JsonToList();
    private AdvertsList advertsList = new AdvertsList();

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private AdvertsManager advertsManager;

    @Inject
    private AdvertsValidation advertsValidation;

    @Inject
    AdvertPreparation advertPreparation;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> dataModel = new HashMap<>();
        Template template = templateProvider.getTemplate(getServletContext(), "add-advert");
        try{
            template.process(dataModel, resp.getWriter());
        }catch (TemplateException e){
            e.printStackTrace();
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
        redirect(resp, advertPreparation.validateAdvertData(advertPreparation.mapReader(map)), adverts);
    }

    private String getPath(){
        ServletContext application = getServletConfig().getServletContext();
        return application.getRealPath("WEB-INF/adverts.json");
    }

    private void redirect(HttpServletResponse resp, String message, List<Advert> adverts) throws IOException {
        if(message.isEmpty()){
            advertsList.setAdvertsList(advertsManager.addAdvert(advertPreparation.getNewAdvert(adverts), adverts));
            advertsManager.advertsToJson(adverts, getPath());
            resp.sendRedirect("/jjdz5-magicy/adverts");
        }
        else{
            PrintWriter writer = resp.getWriter();
            writer.println("<!DOCTYPE html><body><form><t1>" + message + "</t1><input type=\"button\" value=\"Go back!\" onclick=\"history.back()\"></form></body></html>");
        }
    }

}