package com.pluralsight;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.pluralsight.TransactionHolder.startingMenu;
import static java.lang.System.exit;

public class App {
    static Scanner scanner = new Scanner(System.in);
    private static int option;

    public static void main(String[] args) {
        startingMenu();
        scanner.close();

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) !=null) {
                System.out.println(line);
            }
            bufferedReader.close();

        } catch (IOException e) {
//            throw new RuntimeException(e);
        }

    }

    public static void startingMenu() {
        boolean running = true;

        while (running) {
            startingMenu();
            System.out.println("""
                    Welcome! Please make your Selection:
                A) Deposit
                B) Payment
                C) Ledger
                D) Exit""");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    deposit();
                    break;
                case "B":    
                    payment();
                    break;
                case "C":    
                    ledger();
                    break;
                case "D":    
                    exit();
                    break;
                default:
                    System.out.println("Please make a different selection");


            }



        }
    }

    private static void exit() {
    }

    private static void ledger() {
    }

    private static void payment() {
    }

    private static void deposit() {
    }


}
