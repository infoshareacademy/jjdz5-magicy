package com.infoshareacademy.usersengine.servlets;

import com.infoshareacademy.Advert;
import com.infoshareacademy.AdvertsList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/hello-servlet")
public class HelloServlet extends HttpServlet {

    AdvertsList advertsList = new AdvertsList();
    List<Advert> adverts = advertsList.getAdvertsList();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        // writer.println("<!DOCTYPE html>");
        writer.println("Hello from Servlet!");
        writer.println("adverts: " + adverts);
//        writer.println("Adverts Lst "+adverts.toString());
    }
}
