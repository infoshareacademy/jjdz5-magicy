import java.util.Scanner;

public class Menu {
    public Menu() {
    }
    public static void menu() {
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
                    Main.showAd();
                    break;

                case '2':
                    Main.addAd();
                    break;

                case 'q':
                    System.out.println("Thank you!\n");
                    break;
            }
        } while (choice != 'q');

    }

}
