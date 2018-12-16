package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.usersengine.dao.UserDao;
import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
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
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/flags-admin")
@Transactional
public class UserFlagServlet extends HttpServlet {
    private Logger LOG = LoggerFactory.getLogger(DriversServlet.class);
    private static final String TEMPLATE_NAME_GET = "users-admin";

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
        HttpSession session = req.getSession();
        dataModel.put("user", session.getAttribute("user"));
        dataModel.put("USERS", userDao.findAll());

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
      Long id = Long.parseLong(req.getParameter("uid"));
      User user = userDao.findById(id);
      user.setAdmin(!user.isAdmin());
      userDao.update(user);
            resp.setHeader("Refresh", "1;url=http://localhost:8080/jjdz5-magicy/users-admin");
        }
    }

