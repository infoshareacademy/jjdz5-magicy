import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) {

        start();
    }

    public static void start(){

            char choice;
            Scanner sc = new Scanner(System.in);

            System.out.println("-----MENU------");
            do {
                System.out.println("1 - Show adverts list");
                System.out.println("2 - Add advert");
                System.out.println("q - Exit");
                System.out.println("Choice:");
                choice = sc.nextLine().charAt(0);
                switch (choice) {
                    case '1':
                       showAd();
                        break;

                    case '2':
                 //      addAd();
                        break;

                    case 'q':
                        System.out.println("Thank you!\n");
                        break;
                }
            } while (choice != 'q');

    }

    public static void showAd(){
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

            for (Object o : ads)
            {
                JSONObject ad = (JSONObject) o;

                String id = (String) ad.get("id");
                String date = (String) ad.get("date");
                String routeId = (String) ad.get("routeId");

                for(Object a : routes){

                    JSONObject route = (JSONObject) a;

                    if(routeId.equals(route.get("id"))){
                        startAddress = (String) route.get("startAddress");
                        stopAddress = (String) route.get("stopAddress");
                        routePoint = (String) route.get("routePoint");
                        dateR = (String) route.get("date");
                        startTime = (String) route.get("startTime");
                        stopTime = (String) route.get("stopTime");
                    }
                }



                System.out.println("Ad number: "+id+"\n Date: " + dateR +"\n Start point: "+ startAddress+" at "+startTime+"\n Stop point: "+stopAddress+" at: "
                        +stopTime +"\n routePoint: "+routePoint);

                System.out.println("------------");
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    public static void addAd(){

    }


}
