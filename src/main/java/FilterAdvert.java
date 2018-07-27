import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FilterAdvert {

    UserInput user = new UserInput();
    AdvertManager advertManager = new AdvertManager();


    public FilterAdvert(List<Advert> adverts){

        filterMenu(adverts);

    }


    private void showByCity(List<Advert> list){

        String startCity = user.askForText("Input start city ");
        String endtCity = user.askForText("Input end city ");
        System.out.println("------------");
        int n=0;

        for (Advert advert: list
                ) {
            if( advert.getRoute().getStartCity().contains(startCity) &&
                    advert.getRoute().getEndCity().contains(endtCity)){

                advertManager.showOneAdvert(advert);
                n++;
            }

        }
        if(n==0){

            System.out.println("No advert meets the criteria \n");
        }


    }


    private void showByDate(List<Advert> list){

        String userDate = user.getUserInput("Input Date (dd-mm-yyyy): ").trim();
        System.out.println("------------");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        int n=0;

        for (Advert advert: list
                ) {

            String dateString = dateFormat.format(advert.getRoute().getDate());
            if (userDate.contains(dateString)){

                advertManager.showOneAdvert(advert);
                n++;
            }

        }
        if (n==0){

            System.out.println("No advert meets the criteria \n");

        }


    }


    private void showByTime(List<Advert> list){

        String time = user.askForTime("Input time (hh:mm): ").trim();
        System.out.println("------------");
        int n = 0;

        for (Advert advert: list
                ) {

            int n1 = getNumber(advert.getRoute().getStartTime());
            int n2 = getNumber(time);
            int n3 = getN(time);


            if (n1>=n2 && n1<n3){

                advertManager.showOneAdvert(advert);
                n++;
            }


        }
        if(n==0){

            System.out.println("No advert meets the criteria \n");
        }



    }


    private int getNumber(String text){

        StringTokenizer stringTokenizer = new StringTokenizer(text, ":");
        String n1 = stringTokenizer.nextToken();
        String n2 = stringTokenizer.nextToken();
        int result = Integer.parseInt(n1+n2);


        return result;
    }
    private int getN(String text){

        StringTokenizer stringTokenizer = new StringTokenizer(text, ":");
        int n1 = (Integer.parseInt(stringTokenizer.nextToken()))*100;
        int n2 = Integer.parseInt(stringTokenizer.nextToken());
        int n3 = n1+n2;
        int result = (n3-n2)+60;


        return result;
    }


    private void filterMenu(List<Advert> adverts){

        char choice;
        Scanner sc = new Scanner(System.in);



        System.out.println("----Filter by----");
        System.out.println("1 - City         |");
        System.out.println("2 - Date         |");
        System.out.println("3 - Sart time    |");
        System.out.println("-----------------");
        System.out.println("Choice:");
        choice = sc.nextLine().charAt(0);
        switch (choice) {
            case '1':
                showByCity(adverts);
                break;
            case '2':
                showByDate(adverts);
                break;
            case '3':
                showByTime(adverts);
                break;
        }
        
    }



}
