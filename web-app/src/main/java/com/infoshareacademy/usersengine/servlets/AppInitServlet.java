package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.usersengine.dao.CarDao;
import com.infoshareacademy.usersengine.dao.MapsAddressDao;
import com.infoshareacademy.usersengine.dao.MapsAdvertDao;
import com.infoshareacademy.usersengine.dao.MapsDriverDao;
import com.infoshareacademy.usersengine.model.Car;
import com.infoshareacademy.usersengine.model.MapsAddress;
import com.infoshareacademy.usersengine.model.MapsAdvert;
import com.infoshareacademy.usersengine.model.MapsDriver;
import com.infoshareacademy.usersengine.services.PropertiesService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.Arrays;

@WebServlet
@Transactional
public class AppInitServlet extends HttpServlet {

    @Inject
    private MapsAdvertDao mapsAdvertDao;

    @Inject
    private MapsAddressDao mapsAddressDao;

    @Inject
    private MapsDriverDao mapsDriverDao;

    @Inject
    private CarDao carDao;

    @Override
    public void init() throws ServletException {
        try {
            PropertiesService.loadProperties(getServletContext());
        } catch (NullPointerException e) {
            // TODO obsłużyć to!!!
        }
        fillDatabaseWithAdvancedDefaults();
    }

    private void fillDatabaseWithAdvancedDefaults() {

        MapsAddress address1 = new MapsAddress("Gdańsk", "Olsztyńska", "8A",
                -54.00, 18.00, "uniqueId");
        mapsAddressDao.save(address1);
        MapsAddress address2 = new MapsAddress("Gdańsk", "Grunwaldzka", "472A",
                -54.00, 18.00, "uniqueId1");
        mapsAddressDao.save(address2);
        MapsAddress address3 = new MapsAddress("Gdańsk", "Łąkowa", "10",
                -54.00, 18.00, "uniqueId2");
        mapsAddressDao.save(address3);
        MapsAddress address4 = new MapsAddress("Sopot", "Monte Casino", "777",
                1.00, -21.34, "unikalny");
        mapsAddressDao.save(address4);

        MapsDriver driver1 = new MapsDriver("Kuba", "Jurek", "000-111-222");
        mapsDriverDao.save(driver1);
        MapsDriver driver2 = new MapsDriver("Pjoter", "Wiochacz", "0-700-123-123");
        mapsDriverDao.save(driver2);

        Car car1 = new Car("GD 12345", "Opel", "Vectra", driver1);
        carDao.save(car1);
        Car car2 = new Car("G0 KOZAK", "Opel", "Calibra", driver2);
        carDao.save(car2);

        MapsAdvert advert1 = new MapsAdvert(driver1, address1, address2, LocalTime.now(), LocalTime.now());
        mapsAdvertDao.save(advert1);
        MapsAdvert advert2 = new MapsAdvert(driver2, address3, address4, LocalTime.now(), LocalTime.now());
        mapsAdvertDao.save(advert2);

    }

}
