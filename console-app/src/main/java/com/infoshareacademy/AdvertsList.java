package com.infoshareacademy;

import java.util.ArrayList;
import java.util.List;

public class AdvertsList {
    private final JsonToList jsonToList = new JsonToList();
    private final ReadProperties readProperties = new ReadProperties();
    private String path = readProperties.readFilePath();
  //  private List<Advert> advertsList = jsonToList.jsonToList(path);
    private List<Advert> advertsList = new ArrayList<>();


    public void setAdvertsList(List<Advert> advertsList) {
        this.advertsList = advertsList;
    }

    public List<Advert> getAdvertsList() {
       return advertsList;
    }
}
