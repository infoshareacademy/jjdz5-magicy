package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.JsonToList;
import com.infoshareacademy.usersengine.dao.UserStatisticDao;
import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet("adverts")
public class AdvertsServlet extends HttpServlet {

    private JsonToList jsonToList = new JsonToList();
    private Logger LOG = LoggerFactory.getLogger(AdvertsServlet.class);

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    UserStatisticDao userStatisticDao;

    @Inject
    UserStatisticService userStatisticService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("adverts", jsonToList.jsonToList(getPath()));
        Template template = templateProvider.getTemplate(getServletContext(), "adverts");
        try {
            template.process(dataModel, resp.getWriter());
            LOG.debug("Template created successfully.");
        } catch (TemplateException e) {
            LOG.error("TemplateException. Template cannot be created.");
        }

        HttpSession session = req.getSession();
        userStatisticDao.save(userStatisticService.
                addStatistic((User)session.getAttribute("user"), UserActivity.DISPLAYING_ADVERTS));

    }

    private String getPath() {
        ServletContext application = getServletConfig().getServletContext();
        return application.getRealPath("WEB-INF/adverts.json");
    }
}
