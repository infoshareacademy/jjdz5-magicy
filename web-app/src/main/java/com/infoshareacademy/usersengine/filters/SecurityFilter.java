package com.infoshareacademy.usersengine.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class SecurityFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if(req.getRequestURL().toString().endsWith("/jjdz5-magicy/login")){
            System.out.println(req.getRequestURL());
            chain.doFilter(req, res);
        }
        else{
            HttpSession session = req.getSession();
            Object logged = req.getSession().getAttribute("userName");
            if(logged != null){
                chain.doFilter(req,res);
            }else{
                res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
       }

    }
}
