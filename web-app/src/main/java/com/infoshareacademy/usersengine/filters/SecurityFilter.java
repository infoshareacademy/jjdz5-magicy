package com.infoshareacademy.usersengine.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class SecurityFilter extends HttpFilter {

    private static final String LOGIN_SERVLET_URL = "/jjdz5-magicy/login";
    private static final String USER_NAME_ATTRIBUTE = "userName";

    private Logger LOG = LoggerFactory.getLogger(SecurityFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        if(req.getRequestURL().toString().endsWith(LOGIN_SERVLET_URL)) {
            System.out.println(req.getRequestURL());
            chain.doFilter(req, res);
        } else {
            Object logged = req.getSession().getAttribute(USER_NAME_ATTRIBUTE);
            if(logged != null) {
                LOG.info("User recognized. Access granted.");
                chain.doFilter(req,res);
            }else{
                LOG.info("User need to sign in. Access denied.");
                res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
       }

    }
}
