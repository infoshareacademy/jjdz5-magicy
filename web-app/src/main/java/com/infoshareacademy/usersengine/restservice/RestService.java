package com.infoshareacademy.usersengine.restservice;

import javax.ws.rs.core.Response;
import java.util.Collection;

public class RestService {

    public <T> Response sendResultResponse(T result) {
        if (isResultEmpty(result)) {
            return Response.noContent().build();
        } else {
            return Response.ok(result).build();
        }
    }

    private <T extends Collection> Boolean isResultEmpty(T resultCollection) {
        return resultCollection.isEmpty();
    }

    private <T> Boolean isResultEmpty(T result) {
        return result == null;
    }
}
