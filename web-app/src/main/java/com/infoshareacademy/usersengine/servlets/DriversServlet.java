package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.*;
import com.infoshareacademy.usersengine.dao.GenericDao;
import com.infoshareacademy.usersengine.dao.UserStatisticDao;
import com.infoshareacademy.usersengine.drivers.DriversManager;
import com.infoshareacademy.usersengine.drivers.DriversValidation;
import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import com.infoshareacademy.usersengine.model.User;
import com.infoshareacademy.usersengine.services.BandleService;
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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@WebServlet("drivers")
public class DriversServlet extends HttpServlet {

    private JsonToList jsonToList = new JsonToList();
    private DriversList driversList = new DriversList();
    private Logger LOG = LoggerFactory.getLogger(DriversServlet.class);

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private DriversManager driversManager;

    @Inject
    private DriversValidation driversValidation;

    @Inject
    private UserStatisticDao userStatisticDao;

    @Inject
    private UserStatisticService userStatisticService;

    @Inject
    private BandleService bandleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        Map<String, Object> dataModel = new HashMap<>();
        HttpSession session = req.getSession();
        dataModel.put("user", session.getAttribute("user"));

        Locale plLocale = new Locale("pl","PL");
        dataModel.put("language", bandleService.getBundle(plLocale));

        driversList.setDriversList(jsonToList.driversToList(getPath()));
        dataModel.put("drivers", driversList.getDriversList());
        Template template = templateProvider.getTemplate(getServletContext(), "drivers");
        try {
            template.process(dataModel, resp.getWriter());
            LOG.debug("Template created successfully.");
        } catch (TemplateException e) {
            LOG.error("TemplateException. Template cannot be created.");
        }

        userStatisticDao.save(userStatisticService.
                addStatistic((User) session.getAttribute("user"), UserActivity.DISPLAYING_DRIVERS));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String rating = req.getParameter("rating");

        redirect(resp,driversValidation.validateDriverData(id, rating, driversList.getDriversList()), id, rating);
    }

    private String getPath(){
        ServletContext application = getServletConfig().getServletContext();
        return application.getRealPath("WEB-INF/driver.json");
    }

    private void redirect(HttpServletResponse resp, String message, String id, String rating) throws IOException {
        if(message.isEmpty()) {
            LOG.debug("Rating \"{}\" is correct.", rating);
            driversList.setDriversList(driversManager.updateDriversList(driversList.getDriversList(), Integer.parseInt(rating), Long.parseLong(id)));
            driversManager.writeDriverData(driversList.getDriversList(),getPath());
            resp.sendRedirect("/jjdz5-magicy/drivers");
        } else {
            LOG.debug("Rating \"{}\" is not correct.", rating);
            PrintWriter writer = resp.getWriter();
            writer.println("<!DOCTYPE html><body><form><t1>" + message+ "</t1><br/><input type=\"button\" value=\"Go back!\" onclick=\"history.back()\"></form></body></html>");
        }
    }
}

