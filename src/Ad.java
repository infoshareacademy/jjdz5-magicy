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

    static void showAd() {
        try {
            JSONParser parser = new JSONParser();
            JSONArray ads = (JSONArray) parser.parse(new FileReader("ad.json"));
            JSONArray routes = (JSONArray) parser.parse(new FileReader("route.json"));

            String startAddress = null;
            String stopAddress = null;
            String routePoint = null;
            String dateR = null;
            String startTime = null;
            String stopTime = null;

            for (Object o : ads) {
                JSONObject ad = (JSONObject) o;

                final String id = (String) ad.get("id");
                final String date = (String) ad.get("date");
                final String routeId = (String) ad.get("routeId");

                for (Object a : routes) {

                    JSONObject route = (JSONObject) a;

                    if (routeId.equals(route.get("id"))) {
                        startAddress = (String) route.get("startAddress");
                        stopAddress = (String) route.get("stopAddress");
                        routePoint = (String) route.get("routePoint");
                        dateR = (String) route.get("date");
                        startTime = (String) route.get("startTime");
                        stopTime = (String) route.get("stopTime");
                    }
                }


                System.out.println("Ad number: " + id + "\n Date: " + dateR + "\n Start point: " + startAddress + " at " + startTime + "\n Stop point: " + stopAddress + " at: "
                        + stopTime + "\n routePoint: " + routePoint);

                System.out.println("------------");
            }

        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

    }

    public static void addAd() {
        String actualDate = getActualDate();
        Integer id = getMaxIt("ad.json") + 1;
        Integer routeId = getMaxIt("route.json") + 1;
        String startAddress = Main.getUserInput("Start point:");
        String startTime = Main.getUserInput("At time:");
        String stopAddress = Main.getUserInput("End point:");
        String stopTime = Main.getUserInput("At time:");
        String routePoint = Main.getUserInput("Enter intermediate point:");
        String date = Main.getUserInput("Enter date in format dd-mm-yyyy:");

        // create the advert object
        JSONObject ad = new JSONObject();
        ad.put("id", id.toString());
        ad.put("date", actualDate);
        ad.put("routeId", routeId.toString());

        // add object to ad.json
        JsonUtil.writeToJsonFile("ad.json", ad.toJSONString());

        // create the route object
        JSONObject route = new JSONObject();
        route.put("id", routeId.toString());
        route.put("startAddress", startAddress);
        route.put("stopAddress", stopAddress);
        route.put("routePoint", routePoint);
        route.put("date", date);
        route.put("startTime", startTime);
        route.put("stopTime", stopTime);

        JsonUtil.writeToJsonFile("route.json", route.toJSONString());

    }

    private static Integer getMaxIt(String fileName) {
        JSONParser parser = new JSONParser();
        Integer idMax = 0;
        try {
            JSONArray array = (JSONArray) parser.parse(new FileReader(fileName));

            for (Object o : array) {
                JSONObject ob = (JSONObject) o;

                if (Integer.valueOf((String) ob.get("id")) > idMax) {
                    idMax = Integer.valueOf((String) ob.get("id"));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        return idMax;
    }

    private static String getActualDate() {
        Date date = new Date();
        String actualDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        try {
            actualDate = formatter.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return actualDate;
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
