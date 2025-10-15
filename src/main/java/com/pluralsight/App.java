package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {


    public static void main(String[] args) {
        startingMenu();

    }

    public static void startingMenu() {
        Scanner myscanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.println("Please make a selection: ");
            int choice = myscanner.nextInt();
            myscanner.nextLine();

            switch (choice) {
                case 1:
                    deposit();
                    break;
                case 2:
                    payment();
                    break;
                case 3:
                    ledger();
                    break;
                case 4:
                    exit();
                    break;
                default:
                    System.out.println("Please make a different selection");

            }
            //left in for spacing
            System.out.println();
        }
    }

    public static void displayMenu() {
        System.out.println("""
                Welcome! Please make your selection:
                1) Deposit
                2) Payment
                3) Ledger
                4) Exit""");

        ArrayList<Transaction> transactions = readTransactionsFromFile();
        displayTransactions(transactions);



    }

    private static void displayTransactions(ArrayList<Transaction> transactions) {
        //rename
        for (Transaction exchange: transactions){
            System.out.println(transactions);


        }
    }

    private static ArrayList<Transaction> readTransactionsFromFile() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            FileReader filereader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(filereader);
            String line;

            bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null){
                String[] parts = line.split(Pattern.quote("|"));

                Transaction transaction = new Transaction();

                String dateAsString = parts[0];
                int date = Integer.parseInt(dateAsString);

                String timeAsString = parts[1];
                int time = Integer.parseInt(timeAsString);

                String description = parts[2];

                String vendor = parts [3];

                String amountAsString = parts[4];
                double amount = Double.parseDouble(amountAsString);

            }

            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    public static void deposit() {
        boolean running = true;

        while (running) {
            displayDeposits();
            System.out.println("Please choose an option");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Make a deposit?");
                    addDeposits();
                    break;
                case 2:
                    System.out.println("View deposit history?");
                    displayDeposits();
                case 3:
                    System.out.println("Return to main menu?");
                   // if (choice ==3)
                    displayMenu();
                default:
                    System.out.println("Please make another selection");
            }
            //spacing
            System.out.println();
        }
    }

    private static void addDeposits() {
    }

    private static void displayDeposits() {
        boolean running = true;

        while (running) {
            displayDeposits();
            System.out.println("Please choose an option");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("View deposit history?");
                    displayTransactions();
                    break;
                    //another menu for pulling info
                case 2:
                    System.out.println("Return to main menu?");
                    // if (choice ==2)
                    displayMenu();
                default:
                    System.out.println("Please make another selection");
            }
            //spacing
            System.out.println();
        }
    }

    private static void payment() {
        boolean running = true;

        while (running) {
            displayDeposits();
            System.out.println("Please choose an option");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    makepayment();
                    System.out.println("Make a payment");
                    break;
                case 2:
                    System.out.println("View payment history?");
                   displayTransactions();
                    break;
                case 3:
                    System.out.println("Return to main menu?");
                    // if (choice ==3)
                    displayMenu();
                default:
                    System.out.println("Please make another selection");
            }
            //spacing
            System.out.println();
        }
    }

    private static void makepayment() {
    }

    private static void ledger() {
        boolean running = true;

        while (running) {
            displayDeposits();
            System.out.println("Please choose an option");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("View ledger history?");
                    displayledger();
                    break;
                    //menu for transactions, parse info
                case 2:
                    System.out.println("Return to main menu?");
                    // if (choice ==2)
                    displayMenu();
                default:
                    System.out.println("Please make another selection");
            }
            //spacing
            System.out.println();
        }
    }

    private static void displayledger() {
    }

    private static void exit() {
        boolean running = true;

        while (running) {
            displayDeposits();
            System.out.println("Please choose an option");
            int choice = scanner.nextInt();

            switch (choice) {

                case 2:
                    System.out.println("Return to main menu?");
                    displayMenu();
                case 3:
                    System.out.println("Exit");
                    System.out.println("Information save, Goodbye");
                    displayMenu();
                default:
                    System.out.println("Please make another selection");
            }
            //spacing
            System.out.println();
        }
    }

    private static class Transaction {

    }
}
