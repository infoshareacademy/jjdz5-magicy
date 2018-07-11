import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    void menu(){

        State state = null;
        System.out.println("-----MENU------");


        do {
            System.out.println("1 - Show adverts list");
            System.out.println("2 - Add advert");
            System.out.println("3 - Exit");
            System.out.println("Choice:");

            try {
                Scanner sc = new Scanner(System.in);
                state = State.get(sc.nextInt());

                switch (state) {
                    case SHOW_ADVERTS_LIST:
                        AdManager.showAds();

                        break;
                    case ADD_ADVERT:
                        AdManager adManager = new AdManager();
                        adManager.addAd();

                        break;
                    case EXIT:
                        System.out.println("Thank you!\n");
                        System.out.println("exit");
                        
                        break;
                }


            }catch (NullPointerException e){
                System.out.println("Proszę wybrać poprawnie");
                continue;
            }catch (InputMismatchException e){
                System.out.println("Proszę wybrać poprawnie");
                continue;
            }

        } while (state != state.EXIT) ;



    }



}
