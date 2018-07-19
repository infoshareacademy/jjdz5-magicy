import java.util.Scanner;

class Menu {
    AdvertManager advertManager = new AdvertManager();
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
                    advertManager.showAdverts();
                    break;
                case '2':
                    advertManager.addAdvert();
                    break;
                case 'q':
                    System.out.println("Thank you!\n");
                    break;
            }
        } while (choice != 'q');
    }
}
