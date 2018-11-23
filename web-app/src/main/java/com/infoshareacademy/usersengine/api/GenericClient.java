package com.infoshareacademy.usersengine.api;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class GenericClient {

    protected <T> T getResponse(String url, Class<T> clazz) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(url);
        Response response = webTarget.request().get();

        T result = response.readEntity(clazz);
        response.close(); // !!!
        return result;
    }

}
