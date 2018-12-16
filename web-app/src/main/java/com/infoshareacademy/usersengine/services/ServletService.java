package com.infoshareacademy.usersengine.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class ServletService {

    public static final String DEFAULT_CONTENT_TYPE = "text/html;charset=UTF-8";
    public static final String UTF_8_ENCODING = "UTF-8";

    public static void setDefaultContentTypeAndEncoding(HttpServletRequest req, HttpServletResponse resp)
            throws UnsupportedEncodingException {
        resp.setContentType(DEFAULT_CONTENT_TYPE);
        resp.setCharacterEncoding(UTF_8_ENCODING);
        req.setCharacterEncoding(UTF_8_ENCODING);
    }

}
