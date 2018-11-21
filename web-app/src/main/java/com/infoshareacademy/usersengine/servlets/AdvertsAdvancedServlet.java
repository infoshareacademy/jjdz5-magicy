package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.usersengine.dao.CarDao;
import com.infoshareacademy.usersengine.dao.MapsAddressDao;
import com.infoshareacademy.usersengine.dao.MapsAdvertDao;
import com.infoshareacademy.usersengine.dao.MapsDriverDao;
import com.infoshareacademy.usersengine.model.Car;
import com.infoshareacademy.usersengine.model.MapsAddress;
import com.infoshareacademy.usersengine.model.MapsAdvert;
import com.infoshareacademy.usersengine.model.MapsDriver;
import com.infoshareacademy.usersengine.services.ServletService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/adverts-advanced")
@Transactional
public class AdvertsAdvancedServlet extends AppInitServlet {

    @Inject
    private MapsAdvertDao mapsAdvertDao;

    @Inject
    private MapsAddressDao mapsAddressDao;

    @Inject
    private MapsDriverDao mapsDriverDao;

    @Inject
    private CarDao carDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletService.setDefaultContentTypeAndEncoding(req, resp);
        printCars(req, resp);
        resp.getWriter().write("<br><br>");
        printAddresses(req, resp);
        resp.getWriter().write("<br><br>");
        printDrivers(req, resp);
        resp.getWriter().write("<br><br>");
        printAdverts(req, resp);
    }

    private void printCars(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final List<Car> cars = carDao.findAll();
        for (Car c : cars) {
            resp.getWriter().write(c.toString() + "<br>");
        }
    }

    private void printAddresses(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final List<MapsAddress> addresses = mapsAddressDao.findAll();
        for (MapsAddress a : addresses) {
            resp.getWriter().write(a.toString() + "<br>");
        }
    }

    private void printDrivers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final List<MapsDriver> drivers = mapsDriverDao.findAll();
        for (MapsDriver d : drivers) {
            resp.getWriter().write(d.toString() + "<br>");
        }
    }

    private void printAdverts(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final List<MapsAdvert> adverts = mapsAdvertDao.findAll();
        for (MapsAdvert a : adverts) {
            resp.getWriter().write(a.toString() + "<br>");
        }
    }
}
