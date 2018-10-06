package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.usersengine.freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("about-us")
public class AboutUsServlet extends HttpServlet {
    @Inject
    private TemplateProvider templateProvider;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("fatnastic", "fantastic");
        Template template = templateProvider.getTemplate(getServletContext(), "about-us");
        try{
            template.process(dataModel, resp.getWriter());
        }catch (TemplateException e){
            e.printStackTrace();
        }
    }
}

