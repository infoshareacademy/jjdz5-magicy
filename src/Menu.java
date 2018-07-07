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
                    AdManager.showAd();
                    break;
                case '2':
                    AdManager.addAd();
                    break;
                case '3':
                    UserInput user = new UserInput();
                    user.askForDate("blah blah");
                    break;
                case 'q':
                    System.out.println("Thank you!\n");
                    break;
            }
        } while (choice != 'q');
    }
}
