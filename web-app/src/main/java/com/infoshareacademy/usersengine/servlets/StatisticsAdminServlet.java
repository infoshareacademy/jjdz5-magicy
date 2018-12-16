package com.infoshareacademy.usersengine.servlets;

import java.time.LocalDateTime;
import com.infoshareacademy.usersengine.dao.UserStatisticDao;
import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
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
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/statistics-admin")
@Transactional
public class StatisticsAdminServlet extends HttpServlet {
    private Logger LOG = LoggerFactory.getLogger(DriversServlet.class);
    private static final String TEMPLATE_NAME_GET = "statistics-admin";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private UserStatisticDao userStatisticDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        Map<String, Object> dataModel = new HashMap<>();
        HttpSession session = req.getSession();
        dataModel.put("user", session.getAttribute("user"));
        dataModel.put("STATISTICS", userStatisticDao.findAll());

        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME_GET);
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
        String date1 = req.getParameter("dateStart");
        String time1 = req.getParameter("timeStart");
        if(time1.isEmpty()){
            time1 = "00:00";
        }
        String date2 = req.getParameter("dateStop");
        String time2 = req.getParameter("timeStop");
        if(time2.isEmpty()){
            time2 = "23:59";
        }
        String dateTime1s = (date1+" "+time1).trim();
        String dateTime2s = (date2+" "+time2).trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTime1s, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(dateTime2s, formatter);

        Map<String, Object> dataModel = new HashMap<>();
        HttpSession session = req.getSession();
        dataModel.put("user", session.getAttribute("user"));
        dataModel.put("STATISTICS", userStatisticDao.findStatisticInInterval(dateTime1, dateTime2));

        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME_GET);
        try {
            template.process(dataModel, resp.getWriter());
            LOG.debug("Template created successfully.");
        } catch (TemplateException e) {
            LOG.error("TemplateException. Template cannot be created.");
        }
    }
}
