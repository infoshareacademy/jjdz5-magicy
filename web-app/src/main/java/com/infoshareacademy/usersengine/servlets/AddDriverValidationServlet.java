package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.DriversList;

import com.infoshareacademy.usersengine.drivers.DriverPreparation;
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
    DriverPreparation driverPreparation;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

       Map<String, String[]> map = req.getParameterMap();
        PrintWriter writer = resp.getWriter();
        String message = driverPreparation.validateDriver(driverPreparation.mapReader(map), driversList.getDriversList());
        if(message.isEmpty()){
            LOG.debug("Driver data is valid: {}.", driverPreparation.mapReader(map));
            writer.println("OK");
        }
        else{
            LOG.debug("Driver data is not valid.");
            writer.println(message);
        }

    }

}
