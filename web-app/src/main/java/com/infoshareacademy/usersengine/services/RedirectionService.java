package com.infoshareacademy.usersengine.services;

public class RedirectionService {

    public static String buildAddAdvertRedirectionPage(String successMsg,
                       Long newAdvertId, Integer secondsOfDelay) {
        final String id = String.valueOf(newAdvertId);
        return "<form action=\"/jjdz5-magicy/maps-adverts\" method=\"post\" id=\"redirection\">\n" +
               "<input type=\"hidden\" name=\"advertId\" value=\"" + id + "\">\n" +
               "</form>\n" +
               "<div style=\"text-align: center; margin-top: 20px\">\n" +
               "<h2>" + successMsg + "</h2>\n" +
               "<h2>You will be redirected in " + secondsOfDelay + " seconds...</h2>\n" +
               "</div>\n" +
               "<script>" +
               "window.onload = function() {\n" +
               "    window.setTimeout(function () {\n" +
               "        document.getElementById(\"redirection\").submit();\n" +
               "    }, " + secondsOfDelay + "000);\n" +
               "};" +
               "</script>";
    }
}
