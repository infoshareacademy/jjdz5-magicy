
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private AdvertsList advertsList = new AdvertsList();
    private DriversList driversList = new DriversList();

    void menu(){
        AdvertManager advertManager = new AdvertManager();
        DriverManager driverManager = new DriverManager();
        State state = null;

        do {
            System.out.println("-------MENU-----------");
            System.out.println("1 - Show adverts list |");
            System.out.println("2 - Add advert        |");
            System.out.println("3 - Filter adverts    |");
            System.out.println("4 - Driver's rating   |");
            System.out.println("0 - Exit              |");
            System.out.println("----------------------");
            System.out.println("Choice:");

            try {
                Scanner sc = new Scanner(System.in);
                state = State.get(sc.nextInt());

                switch (state) {
                    case SHOW_ADVERTS_LIST:
                        advertManager.showAdverts(advertsList.getAdvertsList());

                        break;
                    case ADD_ADVERT:
                        advertManager.addAdvert();

                        break;

                    case FILTER:
                        FilterAdvert filterAdvert = new FilterAdvert(advertsList.getAdvertsList());

                        break;
                    case RATING:
                        driverManager.showDrivers(driversList.getDriversList());

                        break;

                    case EXIT:
                        System.out.println("Thank you!\n");
                        System.out.println("exit");

                        break;
                }


            }catch (NullPointerException e){
                System.out.println("Please choose correctly");
                continue;
            }catch (InputMismatchException e){
                System.out.println("Please choose correctly");
                continue;
            }

        } while (state != state.EXIT) ;



    }



}