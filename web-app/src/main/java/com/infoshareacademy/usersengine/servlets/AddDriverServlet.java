package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.Driver;
import com.infoshareacademy.DriversList;
import com.infoshareacademy.JsonToList;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet
public class AddDriverServlet extends HttpServlet {

    private JsonToList jsonToList = new JsonToList();
    private DriversList driversList = new DriversList();

    @Inject
    private TemplateProvider templateProvider;

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

        List<Driver> drivers = jsonToList.driversToList(getPath());
        driversList.setDriversList(drivers);

        Map<String, String[]> map = req.getParameterMap();
        System.out.println("Drivers  "+ drivers);
        redirect(resp, advertPreparation.validateAdvertData(advertPreparation.mapReader(map)), drivers);
    }

    private String getPath(){
        ServletContext application = getServletConfig().getServletContext();
        return application.getRealPath("WEB-INF/driver.json");
    }


}
