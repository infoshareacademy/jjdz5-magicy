package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.JsonToList;
import com.infoshareacademy.usersengine.dao.UserDao;
import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import com.infoshareacademy.usersengine.model.User;
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

@WebServlet("admin-panel")
public class AdminPanelServlet extends HttpServlet {

    private JsonToList jsonToList = new JsonToList();
    private Logger LOG = LoggerFactory.getLogger(AdminPanelServlet.class);

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        Map<String, Object> dataModel = new HashMap<>();

        HttpSession session = req.getSession(true);
        String userEmail = session.getAttribute("userEmail").toString();
        User user = userDao.findUserByEmail(userEmail).get(0);

        dataModel.put("user", user);
        Template template = templateProvider.getTemplate(getServletContext(), "admin-panel");
        try {
            template.process(dataModel, resp.getWriter());
            LOG.debug("Template created successfully.");
        } catch (TemplateException e) {
            LOG.error("TemplateException. Template cannot be created.");
        }
    }

}

