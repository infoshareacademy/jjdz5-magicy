package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.Driver;
import com.infoshareacademy.DriversList;
import com.infoshareacademy.JsonToList;
import com.infoshareacademy.usersengine.dao.UserStatisticDao;
import com.infoshareacademy.usersengine.drivers.DriverPreparation;
import com.infoshareacademy.usersengine.drivers.DriversManager;
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
import java.util.List;
import java.util.Locale;
import java.util.Map;

@WebServlet("/add-driver")
public class AddDriverServlet extends HttpServlet {

    private JsonToList jsonToList = new JsonToList();
    private DriversList driversList = new DriversList();
    private Logger LOG = LoggerFactory.getLogger(AddDriverServlet.class);

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private DriversManager driversManager;

    @Inject
    private DriverPreparation driverPreparation;

    @Inject
    private UserStatisticDao userStatisticDao;

    @Inject
    private UserStatisticService userStatisticService;

    @Inject
    private BandleService bandleService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> dataModel = new HashMap<>();
        HttpSession session = req.getSession();
        dataModel.put("user", session.getAttribute("user"));

        Locale plLocale = new Locale("pl","PL");
        dataModel.put("language", bandleService.getBundle(plLocale));

        Template template = templateProvider.getTemplate(getServletContext(), "add-driver");
        try {
            template.process(dataModel, resp.getWriter());
            LOG.debug("Template created successfully.");
        } catch (TemplateException e) {
            LOG.error("TemplateException. Template cannot be created.");
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

        driversManager.addDriver(driverPreparation.getNewDriver(drivers, driverPreparation.mapReader(map)), drivers);
        driversList.setDriversList(drivers);
        driversManager.writeDriverData(drivers, getPath());

        HttpSession session = req.getSession();
        userStatisticDao.save(userStatisticService.
                addStatistic((User)session.getAttribute("user"), UserActivity.ADDING_DRIVER));
        }

    private String getPath() {
        ServletContext application = getServletConfig().getServletContext();
        return application.getRealPath("WEB-INF/driver.json");
    }
}