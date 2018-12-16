package com.infoshareacademy.usersengine.restservice;

import javax.ws.rs.core.Response;
import java.util.Collection;

public class RestService {

    public <C extends Collection> Response sendResultResponse(C result) {
        if (isResultEmpty(result)) {
            return Response.noContent().build();
        } else {
            return Response.ok(result).build();
        }
    }

    private <C extends Collection> Boolean isResultEmpty(C result) {
        return result == null || result.isEmpty();
    }
}
