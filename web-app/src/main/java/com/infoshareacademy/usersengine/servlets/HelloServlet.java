package com.infoshareacademy.usersengine.servlets;
import com.infoshareacademy.usersengine.dao.CarDao;
import com.infoshareacademy.usersengine.dao.MapsAddressDao;
import com.infoshareacademy.usersengine.dao.MapsAdvertDao;
import com.infoshareacademy.usersengine.dao.MapsDriverDao;
import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import com.infoshareacademy.usersengine.model.Car;
import com.infoshareacademy.usersengine.model.MapsAddress;
import com.infoshareacademy.usersengine.model.MapsAdvert;
import com.infoshareacademy.usersengine.model.MapsDriver;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/home")
public class HelloServlet extends HttpServlet {

    private Logger LOG = LoggerFactory.getLogger(HelloServlet.class);

    @Inject
    private TemplateProvider templateProvider;

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
        fillDatabaseWithAdvancedDefaults();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> dataModel = new HashMap<>();
        Template template = templateProvider.getTemplate(getServletContext(), "home");
        try {
            template.process(dataModel, resp.getWriter());
            LOG.debug("Template created successfully.");
        } catch (TemplateException e) {
            LOG.error("TemplateException. Template cannot be created.");
        }
    }

    private void fillDatabaseWithAdvancedDefaults() {

        MapsDriver testDriver = new MapsDriver("Kuba", "Jurek", "000-111-222");
        mapsDriverDao.save(testDriver);

        Car testCar = new Car("GD 12345", "Opel", "Vectra", testDriver);
        carDao.save(testCar);

        MapsAddress testFirstAddress = new MapsAddress("ChIJhYXVl9V0_UYRnv4hHm9KBEE",
                "Gdańsk", "Kołobrzeska", "41c",
                54.4043415, 18.5880136,"");
        mapsAddressDao.save(testFirstAddress);
        MapsAddress testSecondAddress = new MapsAddress("ChIJLaUT9i51_UYRd4PCJjKUE6s",
                "Gdańsk", "aleja Grunwaldzka", "472A",
                54.4024308, 18.5704119, "Olivia Point");
        mapsAddressDao.save(testSecondAddress);
        MapsAddress testThirdAddress = new MapsAddress("ChIJ9Wu2Acag_UYRxtFwAORIirk",
                "Gdynia", "Łużycka", "6A",
                54.4949626, 18.5337226, "Łużycka Office Park - Budynek A");
        mapsAddressDao.save(testThirdAddress);

        MapsAdvert testFirstAdvert = new MapsAdvert(testDriver, testFirstAddress, testSecondAddress,
                LocalTime.now().plusHours(2), LocalTime.now().plusHours(3), LocalDate.now());
        mapsAdvertDao.save(testFirstAdvert);

        MapsAdvert testSecondAdvert = new MapsAdvert(testDriver, testThirdAddress, testSecondAddress,
                LocalTime.now().plusHours(6), LocalTime.now().plusHours(8), LocalDate.now());
        mapsAdvertDao.save(testSecondAdvert);

    }
}
