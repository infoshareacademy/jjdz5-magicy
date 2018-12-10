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
import java.time.format.DateTimeFormatter;
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

        MapsDriver driverKuba = new MapsDriver("Kuba", "Jurek",
                "000-111-222");
        mapsDriverDao.save(driverKuba);

        MapsDriver driverMarysia = new MapsDriver("Marysia", "Wicherkiewicz",
                "000-333-444");
        mapsDriverDao.save(driverMarysia);

        MapsDriver driverKrzysiu = new MapsDriver("Krzysztof", "Gotowała",
                "000-555-666");
        mapsDriverDao.save(driverKrzysiu);

        MapsDriver driverGrzesiu = new MapsDriver("Grzegorz", "Ruchniewicz",
                "000-777-888");
        mapsDriverDao.save(driverGrzesiu);

        Car kubaCar = new Car("GD 12345", "Opel", "Vectra", driverKuba);
        carDao.save(kubaCar);

        Car marysiaCar = new Car("GA 12345", "Ford", "Focus", driverMarysia);
        carDao.save(marysiaCar);

        Car krzysiuCar = new Car("GA 67890", "Mercedes", "ML", driverKrzysiu);
        carDao.save(krzysiuCar);

        MapsAddress alfaTestAddress = new MapsAddress("ChIJhYXVl9V0_UYRnv4hHm9KBEE",
                "Gdańsk", "Kołobrzeska", "41c",
                "Alfa Centrum, aleja Grunwaldzka 409, 80-236 Gdańsk, Poland",
                54.4043415, 18.5880136);
        mapsAddressDao.save(alfaTestAddress);

        MapsAddress obcTestAddress = new MapsAddress("ChIJLaUT9i51_UYRd4PCJjKUE6s",
                "Gdańsk", "aleja Grunwaldzka", "472A",
                "Olivia Point, aleja Grunwaldzka 472A, 80-309 Gdańsk, Poland",
                54.4024308, 18.5704119);
        mapsAddressDao.save(obcTestAddress);

        MapsAddress luzyckaTestAddress = new MapsAddress("ChIJ9Wu2Acag_UYRxtFwAORIirk",
                "Gdynia", "Łużycka", "6A",
                "Łużycka Office Park - Budynek A, Łużycka 6A, 81-537 Gdynia, Poland",
                54.4949626, 18.5337226);
        mapsAddressDao.save(luzyckaTestAddress);

        MapsAddress alchemiaTestAddress = new MapsAddress("ChIJs-riZCh1_UYRUa1_LDDJ04A",
                "Gdańsk", "aleja Grunwaldzka", "409",
                "Alchemia, aleja Grunwaldzka 409, 80-236 Gdańsk, Poland",
                54.3985837, 18.5769263);
        mapsAddressDao.save(alchemiaTestAddress);

        MapsAdvert kubaTestAdvert = new MapsAdvert(driverKuba, alfaTestAddress, obcTestAddress,
                "Wyruszam z pod Alfa Centrum.",
                "Moim punktem docelowym jest Olivia Business Centre",
                LocalTime.now().plusHours(2), LocalTime.now().plusHours(3), LocalDate.now());
        mapsAdvertDao.save(kubaTestAdvert);

        MapsAdvert marysiaTestAdvert = new MapsAdvert(driverMarysia, luzyckaTestAddress, obcTestAddress,
                "", "",
                LocalTime.now().plusHours(6), LocalTime.now().plusHours(8), LocalDate.now());
        mapsAdvertDao.save(marysiaTestAdvert);

        MapsAdvert krzysiuTestAdvert = new MapsAdvert(driverKrzysiu, luzyckaTestAddress, alchemiaTestAddress,
                "", "",
                LocalTime.now().plusHours(4), LocalTime.now().plusHours(5), LocalDate.now().plusDays(1));
        mapsAdvertDao.save(krzysiuTestAdvert);

    }
}
