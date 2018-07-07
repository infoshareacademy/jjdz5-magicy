import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.menu();
    }







    public static String getUserInput(String text) {

        String userInput = null;

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println(text);
            userInput = scanner.nextLine();
        } while (userInput == null || userInput.equals("") || userInput.equals(" "));

        return userInput;

    }

    public static boolean dateValid(String date) {

        boolean valid = true;
        /// validation to write!!!!

        return valid;
    }



}
