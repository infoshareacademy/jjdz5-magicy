package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import com.infoshareacademy.usersengine.services.BandleService;
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
import java.util.Locale;
import java.util.Map;

@WebServlet("about-us")
public class AboutUsServlet extends HttpServlet {

    private Logger LOG = LoggerFactory.getLogger(AboutUsServlet.class);

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private BandleService bandleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        Map<String, Object> dataModel = new HashMap<>();
        HttpSession session = req.getSession();
        dataModel.put("user", session.getAttribute("user"));

        Locale plLocale = new Locale("pl","PL");
        dataModel.put("language", bandleService.getBundle(plLocale));

        dataModel.put("fatnastic", "fantastic");
        Template template = templateProvider.getTemplate(getServletContext(), "about-us");
        try {
            template.process(dataModel, resp.getWriter());
            LOG.debug("Template created successfully.");
        } catch (TemplateException e){
            LOG.error("TemplateException. Template cannot be created.");
        }
    }
}

