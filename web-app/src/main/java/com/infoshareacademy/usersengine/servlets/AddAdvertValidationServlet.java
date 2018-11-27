package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.usersengine.adverts.AdvertPreparation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/addAdvertValidation")
public class AddAdvertValidationServlet extends HttpServlet{

    @Inject
    AdvertPreparation advertPreparation;
    private Logger LOG = LoggerFactory.getLogger(AddAdvertValidationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

       Map<String, String[]> map = req.getParameterMap();
        PrintWriter writer = resp.getWriter();
        String message = advertPreparation.validateAdvertData(advertPreparation.mapReader(map));
        if(message.isEmpty()){
            LOG.debug("Advert data is valid: {}.", advertPreparation.mapReader(map));
            writer.println("OK");
        }
        else{
            LOG.debug("Advert data is not valid.");
            writer.println(message);
        }

    }

}
