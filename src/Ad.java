import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Ad {

    private Integer id;
    private Date date;
    private Integer routeId;

    public Ad() {
    }

    public Ad(Integer id, Date date, Integer routeId) {
        this.id = id;
        this.date = date;
        this.routeId = routeId;
    }

    public Integer getid() {
        return id;
    }

    public Date getdate() {
        return date;
    }

    public void setdate(Date date) {
        this.date = date;
    }

    public Integer getRouteId() {
        return routeId;
    }

}
