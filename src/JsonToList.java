import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.ArrayList;


public class JsonToList {
    private static final String ADVERTS_JSON = "adverts.json";

    public void jsonToList () {
        JSONParser parser = new JSONParser();

        try {

            JSONArray adverts = (JSONArray) parser.parse(ADVERTS_JSON);
//            for (Object o : adverts) {
//                JSONObject advert = (JSONObject) o;


            ArrayList<JSONObject> advertsJsonObjects = new ArrayList<>();
//                JSONArray childArray = advertsJsonObjects.getJSONArray("childObject0");
            for (int i = 0; i < adverts.size(); i++) {
                advertsJsonObjects.addAll(adverts);

            }
            System.out.println(advertsJsonObjects);
//                for (Object a : driver) {
//                    JSONObject drive = (JSONObject) a;
//                }
//            }

        } catch (ParseException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

