import java.util.Scanner;

class Menu {
    Menu() {
    }

    void menu() {
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
                    AdvertManager.showAdverts();
                    break;
                case '2':
                    AdvertManager AdvertManager = new AdvertManager();
                    AdvertManager.addAdvert();
                    break;
                case 'q':
                    System.out.println("Thank you!\n");
                    break;
            }
        } while (choice != 'q');
    }
}
