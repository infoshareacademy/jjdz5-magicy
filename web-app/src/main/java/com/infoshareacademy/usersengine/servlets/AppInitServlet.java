package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.usersengine.services.PropertiesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet
public class AppInitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            PropertiesService.loadProperties(getServletContext());
        } catch (NullPointerException e) {
            // TODO obsłużyć to!!!
        }
    }
}
