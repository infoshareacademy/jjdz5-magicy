
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private AdvertsList advertsList = new AdvertsList();


    void menu(){
        AdvertManager advertManager = new AdvertManager();
        State state = null;
        System.out.println("-----MENU------");


        do {
            System.out.println("1 - Show adverts list");
            System.out.println("2 - Add advert");
            System.out.println("0 - Exit");
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
