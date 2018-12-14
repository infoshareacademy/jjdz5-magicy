package com.infoshareacademy.usersengine.servlets;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
;
import com.infoshareacademy.usersengine.dao.UserDao;
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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "login")
public class GLoginServlet extends HttpServlet {

    private Logger LOG = LoggerFactory.getLogger(GLoginServlet.class);

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    UserDao userDao;

    @Inject
    UserStatisticService userStatisticService;

    @Inject
    UserStatisticDao userStatisticDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        Map<String, Object> dataModel = new HashMap<>();
        Template template = templateProvider.getTemplate(getServletContext(), "login");
        try{
            template.process(dataModel, resp.getWriter());
            LOG.debug("Template created successfully.");
        }catch (TemplateException e){
            LOG.error("TemplateException. Template cannot be created.");
        }
    }@Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        try {
            String idToken = req.getParameter("id_token");
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
            String name = (String) payLoad.get("name");
            String email = payLoad.getEmail();
            LOG.info("User name: " + name);
            LOG.info("User email: " + email);

            HttpSession session = req.getSession(true);
            session.setAttribute("userName", name);

            User user = userDao.getUserByEmail(email);
            session.setAttribute("user", user);
            LOG.info("User with id: " + user.getId() + " and email: " + user.getEmail() + " is logged in");

            userStatisticDao.save(userStatisticService.addStatistic(user, UserActivity.LOG_IN));

            resp.sendRedirect("/jjdz5-magicy/home");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
