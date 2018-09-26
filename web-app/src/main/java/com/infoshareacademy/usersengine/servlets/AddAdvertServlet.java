package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.*;
import com.infoshareacademy.usersengine.adverts.AdvertsManager;
import com.infoshareacademy.usersengine.adverts.AdvertsValidation;
import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/add-advert")
public class AddAdvertServlet extends HttpServlet {

    Advert advert = new Advert();
    Route route = new Route();
    JsonToList jsonToList = new JsonToList();
    AdvertsList advertsList = new AdvertsList();

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    AdvertsManager advertsManager;

    @Inject
    AdvertsValidation advertsValidation;

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

        List<Advert> adverts = jsonToList.jsonToList(getPath());

        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        advertsList.setAdvertsList(adverts);
        advert.setId(advertsManager.getNextAdvertId(adverts));
        advert.setDate(new Date());
        String advertDate = req.getParameter("date");

        if(!advertsValidation.askForDate(resp, advertDate)){
            return;
        }

        String startCity = req.getParameter("startCity").trim();
        String startStreet = req.getParameter("startStreet").trim();
        String startTime = req.getParameter("startTime");

        String endCity = req.getParameter("endCity").trim();
        String endStreet = req.getParameter("endStreet").trim();
        String endTime = req.getParameter("endTime");

        String pickUpCity = req.getParameter("pickUpCity").trim();
        String pickUpStreet = req.getParameter("pickUpStreet").trim();
        String pickUpTime = req.getParameter("pickUpTime");

        try {
            route.setDate(new SimpleDateFormat("dd-MM-yyyy").parse(req.getParameter("date")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(!advertsValidation.askForCity(resp, startCity, "start")){
            return;
        }
        if(!advertsValidation.askForStreet(resp, startStreet, "start")){
            return;
        }
        if(!advertsValidation.askForTime(resp, startTime, "start")){
            return;
        }
        if(!advertsValidation.askForCity(resp, endCity, "end")){
            return;
        }
        if(!advertsValidation.askForStreet(resp, endStreet, "end")){
            return;
        }
        if(!advertsValidation.askForTime(resp, endTime, "end")){
            return;
        }
        if(!pickUpCity.isEmpty() && !advertsValidation.askForCity(resp, pickUpCity, "pick up")){
            return;
        }
        if(!pickUpStreet.isEmpty() && !advertsValidation.askForStreet(resp, pickUpStreet, "pick up")){
            return;
        }
        if(!pickUpTime.isEmpty() && !advertsValidation.askForTime(resp, pickUpTime, "pick up")){
            return;
        }

        route.setId(advertsManager.getNextRouteId(adverts));
        route.setStartCity(startCity);
        route.setStartStreet(startStreet);
        route.setStartTime(startTime);

        route.setEndCity(endCity);
        route.setEndStreet(endStreet);
        route.setEndTime(endTime);

        route.setPickUpCity(pickUpCity);
        route.setPickUpStreet(pickUpStreet);
        route.setPickUpTime(pickUpTime);

        advert.setRoute(route);

        Rating rating = new Rating(4.5, 2);
        Driver driver = new Driver("Artur", "Moroz", "555000111", "Gdańsk", "Wrzeszcz", rating, 4);

        advert.setDriver(driver);
        advertsList.setAdvertsList(advertsManager.addAdvert(advert, adverts));
        advertsManager.advertsToJson(adverts, getPath());

        resp.sendRedirect("/jjdz5-magicy/home");

    }
    private String getPath(){
        ServletContext application = getServletConfig().getServletContext();
        return application.getRealPath("WEB-INF/adverts.json");
    }
}
