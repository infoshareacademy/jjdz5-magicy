package com.infoshareacademy.usersengine.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.Driver;
import com.infoshareacademy.DriversList;
import com.infoshareacademy.JsonToList;
import com.infoshareacademy.Rating;
import com.infoshareacademy.usersengine.drivers.DriversManager;
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

        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer rating = Integer.parseInt(req.getParameter("rating"));

        driversList.setDriversList(driversManager.setNewRating(driversList.getDriversList(), id, rating));
        driversManager.writeDriverData(driversList.getDriversList(),getPath());
        resp.sendRedirect("/jjdz5-magicy/drivers");
    }

    public String getPath(){
        ServletContext application = getServletConfig().getServletContext();
        return application.getRealPath("WEB-INF/driver.json");

    }
}
