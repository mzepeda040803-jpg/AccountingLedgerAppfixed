package com.pluralsight;

import java.io.*;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
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

            System.out.println("""
                    Welcome! PLease make a selection:
                    1) Add Deposit
                    2) Make Payment (Debit)
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
        }
    myscanner.close();
    }

    public static void displayMenu() {
        System.out.println("Welcome! Please make your selection: ");
        System.out.println("(1) Deposit");
        System.out.println("(2) Payment");
        System.out.println("(3) Ledger");
        System.out.println("(4) Exit");
//idk if done right
        ArrayList<Transaction> transactions = readTransactionsFromFile();
        displayTransactions(transactions);



    }

    private static void displayTransactions(ArrayList<Transaction> transactions) {

        for (Transaction t: transactions) {
            System.out.println(t);
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

                String date = parts[0];

                String time = parts[1];

                String description = parts[2];

                String vendor = parts [3];

                String amountAsString = parts[4];
                double amount = Double.parseDouble(amountAsString);

                Transaction transaction = new Transaction(date, time, description, vendor, amount);
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

        Transaction deposit = new Transaction(date, time, description, vendor, amount);
        writeTransactionToFile(deposit);

        System.out.println("Deposit added");

    }

    private static void writeTransactionToFile(Transaction t) {
        //worked on here, pulled from workbook examples
        try {
            // create a FileWriter
            FileWriter fileWriter = new FileWriter("text.txt", true);
            // create a BufferedWriter
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            // write to the file
            bufferedWriter.write("line 1 is here \n");
            bufferedWriter.write("line 2 is here \n");
            bufferedWriter.write("line 3 is here \n");

            // close the writer
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR: An unexpected error occurred");
            e.getStackTrace();
        }

    }

//    private static void displayTransactions() {
//        Scanner scanner = new Scanner(System.in);
//        boolean running = true;
//
//        while (running) {
//            displayDeposits();
//            System.out.println("Please choose an option");
//            String choice = scanner.nextLine();
//
//            switch (choice) {
//                case "1":
//                    System.out.println("View deposit history?");
//                    displayTransactions();
//                    break;
//                case "2":
//                    System.out.println("Return to main menu?");
//                    displayMenu();
//                default:
//                    System.out.println("Please make another selection");
//            }
//            //spacing
//            System.out.println();
//        }
//
//
//    }

//    private static void displayDeposits() {
//    }

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

//   /    while (running) {
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
                Ledger Menu Options 
                1) All
                2) Deposits
                3) Payments
                4) Reports
                5) Main Menu""");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim().toUpperCase();

        switch (choice) {
                case "1":
                   displayTransactions(transactions);
                    break;
                case "2":
                    displayTransactions(filterDeposits(transactions));
                    break;
                case "3":
                    displayTransactions(filterPayments(transactions));
                    break;
                case "4":
                    showReports(transactions);
                case "5":
                    return;
                default:
                    System.out.println("Please make another selection");
            }
            //spacing
            System.out.println();
        }

    private static void showReports(ArrayList<Transaction> transactions) {
        //came from chat, need help reforming it to school standards
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Reports Available
                    1) Month to Date
                    2) Previous Month
                    3) Year to Date
                    4) Previous Year
                    5) Search by Vendor
                    6) Back
                    Choose an Option""");
//help here too
            String choice = scanner.nextLine();

            LocalDate now = LocalDate.now();
//help, chat was not as helpful i don't know what I'm doing :[
            switch (choice) {
                case "1":
                    displayTransactions(transactions.stream()
                            .filter (t -> t.getDate().getMonth() == now.getMonth() && t.getDate().getYear() == now.getYear())
                            .toList());
                    break;
                case "2":
                    YearMonth lastMonth = YearMonth.now().minusMonths(1);
                    displayTransactions(transactions.stream()
                            .filter(t -> YearMonth.from(t.getDate()).equals(lastMonth))
                            .toList());
                    break;
                case "3":
                    displayTransactions(transactions.stream()
                            .filter(t -> t.getDate().getYear() == now.getYear())
                            .toList());
                    break;
                case "4":
                    displayTransactions(transactions.stream()
                            .filter(t -> t.getDate().getYear() == now.getYear() - 1)
                            .toList());
                    break;
                case "5":
                    System.out.println("Enter vendor name: ");
                    String vendor = scanner.nextLine().trim();
                    displayTransactions(transactions.stream()
                            .filter(t -> t.getVendor().equalsIgnoreCase(vendor))
                            .toList());
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Please make a valid selection");



            }
        }
    }

//    private static void displayTransactions() {
//
//    }

    private static ArrayList<Transaction> filterPayments(ArrayList<Transaction> transactions) {
        //this came from chatgpt when I asked it for help with fixing my code but it doesn't look familiar to me at the moment
        ArrayList<Transaction> payments = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) payments.add(t);
        }
        return payments;
    }
    private static ArrayList<Transaction> filterDeposits(ArrayList<Transaction> transactions) {
        //this came from chatgpt when I asked it for help with fixing my code
        ArrayList<Transaction> deposits = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() > 0) deposits.add(t);
        }
        return deposits;
    }
}



