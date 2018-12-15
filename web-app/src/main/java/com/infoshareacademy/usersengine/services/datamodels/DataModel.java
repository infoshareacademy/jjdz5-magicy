package com.infoshareacademy.usersengine.services.datamodels;

import com.infoshareacademy.usersengine.services.PropertiesService;
import com.infoshareacademy.usersengine.services.Property;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DataModel <T>{

    protected Map<String, Object> dataModel;

    public void buildNewDataModel() {
        dataModel = new HashMap<>();
    }

    public abstract void fillDataModelWithGetData();

    public abstract void fillDataModelWithPostData(T postDataType);

    public Map<String, Object> getDataModel() {
        return dataModel;
    }

    protected void putApiKeyIntoDataModel() {
        dataModel.put(Property.API_KEY.name(), getMapsApiKeyAsList());
    }

    private List<String> getMapsApiKeyAsList() {
        return Collections.singletonList(PropertiesService.getMapsApiKey());
    }

    public void addUserToDataModel(HttpServletRequest req){
        HttpSession session = req.getSession();
        dataModel.put("user", session.getAttribute("user"));
    }

}
