package com.infoshareacademy.usersengine.servlets;
import com.infoshareacademy.usersengine.dao.CarDao;
import com.infoshareacademy.usersengine.dao.MapsAddressDao;
import com.infoshareacademy.usersengine.dao.MapsAdvertDao;
import com.infoshareacademy.usersengine.dao.MapsDriverDao;
import com.infoshareacademy.usersengine.dao.UserDao;
import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import com.infoshareacademy.usersengine.model.Car;
import com.infoshareacademy.usersengine.model.MapsAddress;
import com.infoshareacademy.usersengine.model.MapsAdvert;
import com.infoshareacademy.usersengine.model.MapsDriver;
import com.infoshareacademy.usersengine.model.User;
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
import javax.servlet.http.HttpSession;
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

    @Inject
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        fillDatabaseWithAdvancedDefaults();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> dataModel = new HashMap<>();
        HttpSession session = req.getSession(true);
        String userEmail = session.getAttribute("userEmail").toString();
        User user = userDao.findUserByEmail(userEmail).get(0);

        dataModel.put("user", user);
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

        User userKupa = new User(1L, "kuba.jurek@gmail.com", driverKuba, true, true);
        userDao.save(userKupa);
        User userMarysia = new User(2L, "maria.wicherkeiwicz@gmail.com", driverMarysia, true, true);
        userDao.save(userMarysia);
        User userKrzysiu = new User(3L, "kris.gotowala@gmail.com", driverKrzysiu, true, true);
        userDao.save(userKrzysiu);
        User userGrzesiu = new User(4L, "grzegorz.ruchniewicza@gmail.com", driverKrzysiu, true, true);
        userDao.save(userGrzesiu);

        Car kubaCar = new Car("GD 12345", "Opel", "Vectra", driverKuba);
        carDao.save(kubaCar);

        Car marysiaCar = new Car("GA 12345", "Ford", "Focus", driverMarysia);
        carDao.save(marysiaCar);

        Car krzysiuCar = new Car("GA 67890", "Mercedes", "ML", driverGrzesiu);
        carDao.save(krzysiuCar);

        MapsAddress testFirstAddress = new MapsAddress("ChIJhYXVl9V0_UYRnv4hHm9KBEE",
                "Gdańsk", "Kołobrzeska", "41c",
                "Alfa Centrum, Kołobrzeska 41C, Gdańsk",
                54.4043415, 18.5880136);
        mapsAddressDao.save(testFirstAddress);
        MapsAddress testSecondAddress = new MapsAddress("ChIJLaUT9i51_UYRd4PCJjKUE6s",
                "Gdańsk", "aleja Grunwaldzka", "472A",
                "Olivia Bussiness Centre, aleja Grunwaldzka 472A, Gdańsk",
                54.4024308, 18.5704119);
        mapsAddressDao.save(testSecondAddress);
        MapsAddress testThirdAddress = new MapsAddress("ChIJ9Wu2Acag_UYRxtFwAORIirk",
                "Gdynia", "Łużycka", "6A",
                "Łużycka Office Park, Łużycka 6A, Gdynia",
                54.4949626, 18.5337226);
        mapsAddressDao.save(testThirdAddress);

        MapsAdvert testFirstAdvert = new MapsAdvert(driverKuba, testFirstAddress, testSecondAddress,
                "#1 Test start info", "#1 Test end info",
                LocalTime.now().plusHours(2),
                LocalTime.now().plusHours(3), LocalDate.now());
        mapsAdvertDao.save(testFirstAdvert);

        MapsAdvert testSecondAdvert = new MapsAdvert(driverKuba, testThirdAddress, testSecondAddress,
                "#2 Test start info", "#2 Test end info",
                LocalTime.now().plusHours(6), LocalTime.now().plusHours(8), LocalDate.now());
        mapsAdvertDao.save(testSecondAdvert);

    }
}
