package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {


    public static void main(String[] args) {
        startingMenu();
  //      scanner.close();

    }

    public static void startingMenu() {
        Scanner myscanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
//            System.out.println("Home Menu");
//            System.out.println("1) Add Deposit");
//            System.out.println("2) Make Payment");
//            System.out.println("3) Ledger");
//            System.out.println("4 Exit");
//            System.out.println("Enter you choice: ");

            System.out.println("""
                    Welcome! PLease make a selection:
                    1) Add Deposit
                    2) Make Payment
                    3) Ledger
                    4) Exit""");

//            displayMenu();
//            System.out.println("Please make a selection: ");
//            int choice = myscanner.nextInt();
//            myscanner.nextLine();

            String choice = myscanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addDeposits();
                    break;
                case "2":
                    makePayment();
                    break;
                case "3":
                    ledger();
                    break;
                case "4":
                    running = false;
                    System.out.println("Thank you, come again");
                    break;
                default:
                    System.out.println("Please make a different selection");

            }
            //left in for spacing
            System.out.println();
            myscanner.close();
        }

    }

    public static void displayMenu() {
        System.out.println("Welcome! Please make your selection: ");
        System.out.println("(1) Deposit");
        System.out.println("(2) Payment");
        System.out.println("(3) Ledger");
        System.out.println("(4) Exit");

        ArrayList<Transaction> transactions = readTransactionsFromFile();
        displayTransactions(transactions);



    }

    private static void displayTransactions(ArrayList<Transaction> transactions) {
        for (int i = transactions.size()-1; i >= 0; i--) {
            System.out.println(transactions.get(i));
        }
        //rename
//        for (Transaction exchange: transactions){
//            System.out.println(transactions);
//        }
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

                String date = parts[0];

                String time = parts[1];

                String description = parts[2];

                String vendor = parts [3];

                String amountAsString = parts[4];
                double amount = Double.parseDouble(amountAsString);
// come back later
                Transaction transaction = new Transaction();
                transactions.add(transaction);

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

    public static void addDeposits() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Description: ");
        String description = scanner.nextLine();

        System.out.println("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.println("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        String date = LocalDate.now().toString();
        String time = LocalTime.now().toString();

        Transaction deposit = new Transaction();
        writeTransactionToFile(deposit);

        System.out.println("Deposit added");

    }

    private static void writeTransactionToFile(Transaction deposit) {
    }


    private static void displayDeposits() {
        Scanner scanner = new Scanner(System.in);
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

    private static void displayTransactions() {
        //parse
    }

    private static void makePayment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Description: ");
        String description = scanner.nextLine();

        System.out.println("Enter Vendor: ");
        String vendor = scanner.nextLine();

        System.out.println("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        String date = LocalDate.now().toString();
        String time = LocalTime.now().toString();

        System.out.println("Payment processed");



//        while (running) {
//            displayDeposits();
//            System.out.println("Please choose an option");
//            int choice = scanner.nextInt();
//
//            switch (choice) {
//                case 1:
//                    makePayment();
//                    System.out.println("Make a payment");
//                    break;
//                case 2:
//                    System.out.println("View payment history?");
//                   displayTransactions();
//                    break;
//                case 3:
//                    System.out.println("Return to main menu?");
//                    // if (choice ==3)
//                    displayMenu();
//                default:
//                    System.out.println("Please make another selection");
//            }
//            //spacing
//            System.out.println();
//        }
    }


    private static void ledger() {
        ArrayList<Transaction> transactions = readTransactionsFromFile();

        System.out.println("""
                Ledger Menu: Please make a selection 
                1) All
                2) Deposits
                3) Payments
                4) Main Menu""");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim().toUpperCase();
        
        switch (choice) {
                case "1":
                   displayTransactions(transactions);
                    break;
                    //menu for transactions, parse info
                case "2":
                    displayTransactions(filterDeposits(transactions));
                default:
                    System.out.println("Please make another selection");
            }
            //spacing
            System.out.println();
        }

    private static ArrayList<Transaction> filterDeposits(ArrayList<Transaction> transactions) {
    }
}

    private static void displayLedger() {
    }

    private static void exit() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayDeposits();
            System.out.println("Please choose an option");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Return to main menu?");
                    displayMenu();
                case 2:
                    System.out.println("Exit");
                    System.out.println("Information saved, Goodbye");
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
