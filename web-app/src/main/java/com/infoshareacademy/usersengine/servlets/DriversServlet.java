package com.infoshareacademy.usersengine.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.*;
import com.infoshareacademy.usersengine.drivers.DriversManager;
import com.infoshareacademy.usersengine.drivers.DriversValidation;
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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/drivers")
public class DriversServlet extends HttpServlet {
    private JsonToList jsonToList = new JsonToList();
    @Inject
    private TemplateProvider templateProvider;
    @Inject
    private DriversManager driversManager;
    @Inject
    private DriversValidation driversValidation;
    private DriversList driversList = new DriversList();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        Map<String, Object> dataModel = new HashMap<>();
        driversList.setDriversList(jsonToList.driversToList(getPath()));
        dataModel.put("drivers", driversList.getDriversList());
        Template template = templateProvider.getTemplate(getServletContext(), "drivers");
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
        String id = req.getParameter("id");
        String rating = req.getParameter("rating");

        redirect(resp,driversValidation.validateAdvertData(id, rating, driversList.getDriversList()), id, rating);
    }

    private String getPath(){
        ServletContext application = getServletConfig().getServletContext();
        return application.getRealPath("WEB-INF/driver.json");
    }

    private void redirect(HttpServletResponse resp, String message, String id, String rating) throws IOException {
        if(message.isEmpty()){
            driversList.setDriversList(driversManager.updateDriversList(driversList.getDriversList(),driversManager.setNewRating(driversManager.getUserById(driversList.getDriversList(), Integer.parseInt(id)).get(), Integer.parseInt(rating)), Integer.parseInt(id)));
            driversManager.writeDriverData(driversList.getDriversList(),getPath());
            resp.sendRedirect("/jjdz5-magicy/drivers");
        }
        else{
            PrintWriter writer = resp.getWriter();
            writer.println("<!DOCTYPE html><body><form><t1>" + message+ "</t1><br/><input type=\"button\" value=\"Go back!\" onclick=\"history.back()\"></form></body></html>");
        }
    }
}
