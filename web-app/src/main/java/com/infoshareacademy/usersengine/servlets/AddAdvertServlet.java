package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.*;
import com.infoshareacademy.usersengine.adverts.AdvertsManager;
import com.infoshareacademy.usersengine.adverts.AdvertsValidation;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/add-advert")
public class AddAdvertServlet extends HttpServlet {

    Advert advert = new Advert();
    Route route = new Route();

    @Inject
    AdvertsManager advertsManager;

    @Inject
    AdvertsValidation advertsValidation;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        advertsManager.setAdverts(advertsManager.jsonToList(getPath()));
        advert.setId(advertsManager.getNextAdvertId());
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
        if(!advertsValidation.askForCity(resp, endCity, "end")){
            return;
        }
        if(!advertsValidation.askForStreet(resp, endStreet, "end")){
            return;
        }
        if(!pickUpCity.isEmpty() && !advertsValidation.askForCity(resp, pickUpCity, "pick up")){
            return;
        }
        if(!pickUpStreet.isEmpty() && !advertsValidation.askForStreet(resp, pickUpStreet, "pick up")){
            return;
        }

        route.setId(advertsManager.getNextRouteId());
        route.setStartCity(startCity);
        route.setStartStreet(startStreet);
        route.setStartTime(startTime);

        route.setEndCity(endCity);
        route.setEndStreet(endCity);
        route.setEndTime(endTime);

        route.setPickUpCity(pickUpCity);
        route.setPickUpStreet(pickUpStreet);
        route.setPickUpTime(pickUpTime);

        advert.setRoute(route);

        Rating rating = new Rating(4.5, 2);
        Driver driver = new Driver("Artur", "Moroz", "555000111", "Gdańsk", "Wrzeszcz", rating, 4);

        advert.setDriver(driver);
        advertsManager.setAdverts(advertsManager.addAdvert(advert));
        advertsManager.listToJson();

        resp.sendRedirect("/index.jsp");

    }
    private String getPath(){
        ServletContext application = getServletConfig().getServletContext();
        return application.getRealPath("/adverts.json");
    }
}
