package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.*;

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
    AdvertsList advertsList = new AdvertsList();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        advert.setId(5);
        advert.setDate(new Date());

        try {
            route.setDate(new SimpleDateFormat("dd-MM-yyyy").parse(req.getParameter("date")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        route.setStartCity(req.getParameter("startCity"));
        route.setStartStreet(req.getParameter("startStreet"));
        route.setStartTime(req.getParameter("startTime"));

        route.setEndCity(req.getParameter("endCity"));
        route.setEndStreet(req.getParameter("endStreet"));
        route.setEndTime(req.getParameter("endTime"));

        route.setPickUpCity(req.getParameter("pickUpCity"));
        route.setPickUpStreet(req.getParameter("pickUpCity"));
        route.setPickUpTime(req.getParameter("pickUpTime"));

        advert.setRoute(route);

        Rating rating = new Rating(4.5, 2);
        Driver driver = new Driver("Artur", "Moroz", "555000111", "Gdańsk", "Wrzeszcz", rating, 4);

        advert.setDriver(driver);

        // advertsList.getAdvertsList().add(advert);
        //  advertManager.writeAdvertData(advertsList.getAdvertsList());

        writer.println("<!DOCTYPE html><body>Well done"+advert.toString()+"</body></html>");

    }
}
