package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.Driver;
import com.infoshareacademy.DriversList;
import com.infoshareacademy.JsonToList;
import com.infoshareacademy.usersengine.dao.CarDao;
import com.infoshareacademy.usersengine.dao.MapsDriverDao;
import com.infoshareacademy.usersengine.dao.UserDao;
import com.infoshareacademy.usersengine.dao.UserStatisticDao;
import com.infoshareacademy.usersengine.drivers.DriverPreparation;
import com.infoshareacademy.usersengine.drivers.DriversManager;
import com.infoshareacademy.usersengine.drivers.MapsDriverPreparation;
import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import com.infoshareacademy.usersengine.model.Car;
import com.infoshareacademy.usersengine.model.MapsDriver;
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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/add-driver")
public class AddDriverServlet extends HttpServlet {

    private Logger LOG = LoggerFactory.getLogger(AddDriverServlet.class);

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private MapsDriverDao mapsDriverDao;

    @Inject
    private CarDao carDao;

    @Inject
    private UserDao userDao;

    @Inject
    private MapsDriverPreparation driverPreparation;

    @Inject
    private UserStatisticDao userStatisticDao;

    @Inject
    private UserStatisticService userStatisticService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> dataModel = new HashMap<>();
        HttpSession session = req.getSession();
        dataModel.put("user", session.getAttribute("user"));
        Template template = templateProvider.getTemplate(getServletContext(), "add-maps-driver");
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

        Map<String, String[]> map = req.getParameterMap();

        MapsDriver driver = driverPreparation.driverMapReader(map);
        String message = driverPreparation.validateDriver(driver);
        if(message.isEmpty()){
            carDao.save(driver.getCar());
            mapsDriverDao.save(driver);
            User currentUser = (User) req.getSession().getAttribute("user");
            currentUser.setDriver(driver);
            currentUser.setDriverStatus(true);
            userDao.update(currentUser);
        }

        HttpSession session = req.getSession();
        userStatisticDao.save(userStatisticService.
                addStatistic((User) session.getAttribute("user"), UserActivity.ADDING_DRIVER));
    }
}