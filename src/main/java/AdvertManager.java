import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdvertManager {
    private static final String AD_JSON = "ad.json";
    private static final String ROUTE_JSON = "route.json";
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public AdvertManager() {
    }

    static void showAdverts() {
        try {
            JSONParser parser = new JSONParser();
            JSONArray adverts = (JSONArray) parser.parse(new FileReader(AD_JSON));
            JSONArray routes = (JSONArray) parser.parse(new FileReader(ROUTE_JSON));

            String startCity = null;
            String startStreet = null;
            String endCity = null;
            String endStreet = null;
            String pickUpCity = null;
            String pickUpStreet = null;
            String dateR = null;
            String startTime = null;
            String endTime = null;
            String pickUpTime = null;

            String id;
            String date;
            String routeId;
            for (Object o : adverts) {
                JSONObject advert = (JSONObject) o;

                id = (String) advert.get("id");
                date = (String) advert.get("date");
                routeId = (String) advert.get("routeId");

                for (Object a : routes) {
                    JSONObject route = (JSONObject) a;

                    if (routeId.equals(route.get("id"))) {
                        startCity = (String) route.get("startCity");
                        startStreet = (String) route.get("startStreet");
                        endCity = (String) route.get("endCity");
                        endStreet = (String) route.get("endStreet");
                        pickUpCity = (String) route.get("pickUpCity");
                        pickUpStreet = (String) route.get("pickUpStreet");
                        dateR = (String) route.get("date");
                        startTime = (String) route.get("startTime");
                        endTime = (String) route.get("endTime");
                        pickUpTime = (String) route.get("pickUpTime");
                    }
                }

                System.out.println("Advert number: " + id +
                        "\n Date: " + dateR +
                        "\n Route start point: " + startCity + ", " + startStreet + " at " + startTime +
                        "\n Route end point: " + endCity + ", " + endStreet + " at " + endTime +
                        "\n Pick up point: " + pickUpCity + ", " + pickUpStreet + " at " + pickUpTime);

                System.out.println("------------");
            }

        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    void addAdvert() {
        final UserInput user = new UserInput();

        String startCity = user.askForText("Route start city");
        String startStreet = user.askForText("Route start street");
        String startTime = user.askForTime("At time (HH:mm)");
        String endCity = user.askForText("Route end city");
        String endStreet = user.askForText("Route end street");
        String endTime = user.askForText("At time (HH:mm)");
        String pickUpCity = user.askForText("Enter pick up city");
        String pickUpStreet = user.askForText("Enter pick up street");
        String pickUpTime = user.askForText("At time (HH:mm)");
        Date date = user.askForDate("Enter date in format (dd-mm-yyyy)");

        Integer routeId = getMaxId(ROUTE_JSON) + 1;

        // create the advert object
     //   Advert advert = new Advert(Long.valueOf(getMaxId(AD_JSON) + 1), new Date(), Long.valueOf(routeId));
    //    JSONObject jsonAdvert = adToJson(advert);

        // add object to ad.json
       // JsonUtil.writeToJsonFile(AD_JSON, jsonAdvert.toJSONString());

        // create the route object
        Route route = new Route(routeId, date, startCity, startStreet, endCity,
                endStreet, pickUpCity, pickUpStreet, startTime, endTime, pickUpTime);
        JSONObject jsonRoute = routeToJson(route);

        JsonUtil.writeToJsonFile(ROUTE_JSON, jsonRoute.toJSONString());
    }

    private JSONObject adToJson(Advert advert) {
        final DateFormat format = new SimpleDateFormat(DATE_FORMAT);

        JSONObject jsonAdvert = new JSONObject();
        jsonAdvert.put("id", advert.getId().toString());
        jsonAdvert.put("date", format.format(advert.getDate()));
 //       jsonAdvert.put("routeId", advert.getRouteId().toString());

        return jsonAdvert;
    }

    private JSONObject routeToJson(Route route) {
        final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        final DateFormat timeFormat = new SimpleDateFormat("HH:mm");

        JSONObject jsonRoute = new JSONObject();
        jsonRoute.put("id", route.getId().toString());
        jsonRoute.put("startCity", route.getStartCity());
        jsonRoute.put("startStreet", route.getStartStreet());
        jsonRoute.put("endCity", route.getEndCity());
        jsonRoute.put("endStreet", route.getEndStreet());
        jsonRoute.put("pickUpCity", route.getPickUpCity());
        jsonRoute.put("pickUpStreet", route.getPickUpStreet());
        jsonRoute.put("date", dateFormat.format(route.getDate()));
        jsonRoute.put("startTime", route.getStartTime());
        jsonRoute.put("endTime", route.getEndTime());
        jsonRoute.put("pickUpTime", route.getPickUpTime());

        return jsonRoute;
    }

    private static Integer getMaxId(String fileName) {
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
