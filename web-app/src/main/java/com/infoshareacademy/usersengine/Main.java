package com.infoshareacademy.usersengine;

import com.infoshareacademy.UserInput;
import com.infoshareacademy.usersengine.model.AddressType;

public class Main {
    public static void main(String[] args) {
        UserInput u = new UserInput();
        System.out.println("Hello from goTogether!");
        String s = "Grunwaldzka";
        String s1 = "Bora - Komorowskiego-Generała";
        String n1 = "8";
        String n2 = "8A";
        String n3 = "472a";
        String n4 = "472B";

        System.out.println(u.isStreetNameValid(s));
        System.out.println(u.isStreetNameValid(s1));
        System.out.println(u.isStreetNumberValid(n1));
        System.out.println(u.isStreetNumberValid(n2));
        System.out.println(u.isStreetNumberValid(n3));
        System.out.println(u.isStreetNumberValid(n4));
    }
}
