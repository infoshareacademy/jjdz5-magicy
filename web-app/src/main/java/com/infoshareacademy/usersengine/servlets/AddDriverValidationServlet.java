package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.DriversList;

import com.infoshareacademy.usersengine.dao.CarDao;
import com.infoshareacademy.usersengine.dao.MapsDriverDao;
import com.infoshareacademy.usersengine.dao.UserDao;
import com.infoshareacademy.usersengine.drivers.MapsDriverPreparation;
import com.infoshareacademy.usersengine.model.Car;
import com.infoshareacademy.usersengine.model.MapsDriver;
import com.infoshareacademy.usersengine.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/addDriverValidation")
public class AddDriverValidationServlet extends HttpServlet{

    private DriversList driversList = new DriversList();
    private Logger LOG = LoggerFactory.getLogger(AddDriverValidationServlet.class);

    @Inject
    private MapsDriverPreparation driverPreparation;

    @Inject
    private MapsDriverDao mapsDriverDao;

    @Inject
    private CarDao carDao;

    @Inject
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        Map<String, String[]> map = req.getParameterMap();
        map.entrySet().forEach(e -> LOG.info(e.getKey() + " --- " + e.getValue()[0]));
        PrintWriter writer = resp.getWriter();
        MapsDriver driver = driverPreparation.driverMapReader(map);
        Car car = driverPreparation.carMapReader(map);
        LOG.info(car.toString());
        String message = driverPreparation.validateDriver(driver, car);
        if(message.isEmpty()){
            LOG.debug("Driver data is valid.");
            writer.println("OK");
//            mapsDriverDao.save(driver);
//            car.setDriver(driver);
//            carDao.save(car);
//            User currentUser = (User) req.getSession().getAttribute("user");
//            currentUser.setDriver(driver);
//            currentUser.setDriverStatus(true);
//            userDao.update(currentUser);
        }
        else{
            LOG.debug("Driver data is not valid.");
            writer.println(message);
        }

    }

}
