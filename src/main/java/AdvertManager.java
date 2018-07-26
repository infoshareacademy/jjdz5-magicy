import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdvertManager {
    private static final String AD_JSON = "ad.json";
    private static final String ROUTE_JSON = "route.json";
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public AdvertManager() {
    }

    public void showAdverts(List<Advert> advertsList) {
        for (Advert advert: advertsList) {
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

            System.out.println("Advert number: " + advert.getId() +
                    "\n Date: " + formatter.format(advert.getDate()) +
                    "\n Route start point: " + advert.getRoute().getStartCity() + ", " + advert.getRoute().getStartStreet() + " at " + advert.getRoute().getStartTime() +
                    "\n Route end point: " + advert.getRoute().getEndCity() + ", " + advert.getRoute().getEndStreet() + " at " + advert.getRoute().getEndTime() +
                    "\n Pick up point: " + advert.getRoute().getStartCity() + ", " + advert.getRoute().getPickUpStreet() + " at " + advert.getRoute().getPickUpTime());

            System.out.println("------------");
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
