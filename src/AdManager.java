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
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public AdManager() {
    }

    static void showAds() {
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

    void addAd() {
        final UserInput user = new UserInput();

        String startAddress = user.askForText("Start point");
        String startTime = user.askForTime("At time (HH:mm)");
        String stopAddress = user.askForText("End point");
        String stopTime = user.askForText("At time (HH:mm)");
        String routePoint = user.askForText("Enter intermediate city");
        Date date = user.askForDate("Enter date in format (dd-mm-yyyy)");

        Integer routeId = getMaxIt(ROUTE_JSON) + 1;

        // create the advert object
        Ad ad = new Ad(Long.valueOf(getMaxIt(AD_JSON) + 1), new Date(), Long.valueOf(routeId));
        JSONObject jsonAd = adToJson(ad);

        // add object to ad.json
        JsonUtil.writeToJsonFile(AD_JSON, jsonAd.toJSONString());

        // create the route object
        Route route = new Route(routeId, startAddress, stopAddress, routePoint, date, startTime, stopTime);
        JSONObject jsonRoute = routeToJson(route);

        JsonUtil.writeToJsonFile(ROUTE_JSON, jsonRoute.toJSONString());
    }

    private JSONObject adToJson(Ad ad) {
        final DateFormat format = new SimpleDateFormat(DATE_FORMAT);

        JSONObject jsonAd = new JSONObject();
        jsonAd.put("id", ad.getId().toString());
        jsonAd.put("date", format.format(ad.getDate()));
        jsonAd.put("routeId", ad.getRouteId().toString());

        return jsonAd;
    }

    private JSONObject routeToJson(Route route) {
        final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        final DateFormat timeFormat = new SimpleDateFormat("HH:mm");

        JSONObject jsonRoute = new JSONObject();
        jsonRoute.put("id", route.getId().toString());
        jsonRoute.put("startAddress", route.getStartAddress());
        jsonRoute.put("stopAddress", route.getStopAddress());
        jsonRoute.put("routePoint", route.getRoutePoint());
        jsonRoute.put("date", dateFormat.format(route.getDate()));
        jsonRoute.put("startTime", route.getStartTime());
        jsonRoute.put("stopTime", route.getStopTime());

        return jsonRoute;
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
