import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdManager {
    private static final String AD_JSON = "ad.json";
    private static final String ROUTE_JSON = "route.json";

    public AdManager() {
    }

    static void showAd() {
        try {
            JSONParser parser = new JSONParser();
            JSONArray ads = (JSONArray) parser.parse(new FileReader(AD_JSON));
            JSONArray routes = (JSONArray) parser.parse(new FileReader(ROUTE_JSON));

            String startAddress = null;
            String stopAddress = null;
            String routePoint = null;
            String dateR = null;
            String startTime = null;
            String stopTime = null;

            String id;
            String date;
            String routeId;
            for (Object o : ads) {
                JSONObject ad = (JSONObject) o;

                id = (String) ad.get("id");
                date = (String) ad.get("date");
                routeId = (String) ad.get("routeId");

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

                System.out.println("AdManager number: " + id + "\n Date: " + dateR + "\n Start point: " + startAddress + " at " + startTime + "\n Stop point: " + stopAddress + " at: "
                        + stopTime + "\n routePoint: " + routePoint);

                System.out.println("------------");
            }

        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

    }

    static void addAd() {
        final UserInput user = new UserInput();

        Date currentDate = new Date();
        Integer id = getMaxIt(AD_JSON) + 1;
        Integer routeId = getMaxIt(ROUTE_JSON) + 1;
        String startAddress = user.askForText("Start point");
        String startTime = user.askForTime("At time (HH:mm)");
        String stopAddress = user.askForText("End point");
        String stopTime = user.askForText("At time");
        String routePoint = user.askForText("Enter intermediate city");
        Date date = user.askForDate("Enter date in format (dd-mm-yyyy)");

        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        // create the advert object
        JSONObject ad = new JSONObject();
        ad.put("id", id.toString());
        ad.put("date", format.format(currentDate));
        ad.put("routeId", routeId.toString());

        // add object to ad.json
        JsonUtil.writeToJsonFile(AD_JSON, ad.toJSONString());

        // create the route object
        JSONObject route = new JSONObject();
        route.put("id", routeId.toString());
        route.put("startAddress", startAddress);
        route.put("stopAddress", stopAddress);
        route.put("routePoint", routePoint);
        route.put("date", format.format(date));
        route.put("startTime", startTime);
        route.put("stopTime", stopTime);

        JsonUtil.writeToJsonFile(ROUTE_JSON, route.toJSONString());
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
}
