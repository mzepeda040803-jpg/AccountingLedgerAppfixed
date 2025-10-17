package com.pluralsight;

import java.io.*;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
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

            System.out.println("""
                    Welcome! PLease make a selection:
                    D) Add Deposit
                    P) Make Payment (Debit)
                    L) Ledger
                    X) Exit""");


            String choice = myscanner.nextLine().trim();

            switch (choice) {
                case "D":
                    addDeposits();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    ledger();
                    break;
                case "X":
                    running = false;
                    System.out.println("Thank you, come again");
                    break;
                default:
                    System.out.println("Please make a different selection");

            }

            System.out.println();
        }
    myscanner.close();
    }


    private static void displayTransactions(ArrayList<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found");
        } else {
            for (Transaction t: transactions) {
                System.out.println(t);
            }
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

                LocalDate date = LocalDate.parse(parts[0]);

                LocalTime time = LocalTime.parse(parts[1]);

                String description = parts[2];

                String vendor = parts[3];

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

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();


//        LocalDate date = LocalDate.parse(LocalDate.now().toString());
//        LocalTime time = LocalTime.parse(LocalTime.now().toString());

        Transaction deposit = new Transaction(date, time, description, vendor, amount);
        writeTransactionToFile(deposit);

        System.out.println("Deposit added");

    }

    private static void writeTransactionToFile(Transaction t) {
        try {

            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(t.toString());
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR: An unexpected error occurred");
            e.getStackTrace();
        }
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

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();


//        String date = LocalDate.now().toString();
//        String time = LocalTime.now().toString();

        Transaction payment = new Transaction(date, time, description, vendor, -Math.abs(amount));
        writeTransactionToFile(payment);

        System.out.println("Payment processed and saved");

    }

    private static void ledger() {
        ArrayList<Transaction> transactions = readTransactionsFromFile();
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                Ledger Menu
                A) All
                D) Deposits
                P) Payments
                R) Reports
                H) Main Menu""");


        String choice = scanner.nextLine().trim().toUpperCase();

        switch (choice) {
                case "A":
                   displayTransactions(transactions);
                    break;
                case "D":
                    displayTransactions(filterDeposits(transactions));
                    break;
                case "P":
                    displayTransactions(filterPayments(transactions));
                    break;
                case "R":
                    showReports(transactions);
                    break;
                case "H":
                    return;
                default:
                    System.out.println("Please make another selection");
            }
            System.out.println();
        }


    private static void showReports(ArrayList<Transaction> transactions) {
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

            String choice = scanner.nextLine();

     //       LocalDate date = LocalDate.now();
     //       LocalTime time = LocalTime.now();

     //       ArrayList<Transaction> results = new ArrayList<>();

            switch (choice) {
                case "1":
                    showMonthToDate(transactions);
                    break;
                case "2":
                    showPreviousMonth(transactions);
                    break;
                case "3":
                    showYearToDate(transactions);
                    break;
                case "4":
                    showPreviousYear(transactions);
                    break;
                case "5":
                    showByVendor(transactions);
                    break;
                case "6":
                    return;

                default:
                    System.out.println("Error, please make another selection");

            }
     //       results.clear();
            System.out.println("-----------");

        }
    }


    private static void showByVendor(ArrayList<Transaction> transactions) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter vendor name: ");
        String vendorInput = scanner.nextLine().trim();

        ArrayList<Transaction> results = new ArrayList<>();

        for (Transaction t: transactions) {
            String transactionVendor = t.getVendor();

            if (transactionVendor.equalsIgnoreCase(vendorInput)) {
                results.add(t);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No transactions found for vendor: " + vendorInput);
        } else {
            System.out.println("Found " + results.size() + " transaction(s) for vendor \"" + vendorInput + "\":\n");

            for (Transaction t : results) {
                System.out.println(t);
            }
        }

    }

    private static void showPreviousYear(ArrayList<Transaction> transactions) {
        System.out.println("Generating Previous Year Report");

        LocalDate today = LocalDate.now();
        int lastYear = today.getYear() - 1;

        ArrayList<Transaction> results = new ArrayList<>();

        for (Transaction t: transactions) {
            LocalDate transactionDate = t.getDate();

            if (transactionDate.getYear() == lastYear) {
                results.add(t);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No transactions found for the previous year");

        } else {
            System.out.println("Found " + results.size() + " transaction(s) from" + lastYear + ":\n");

            for (Transaction t: results) {
                System.out.println(t);
            }
        }
    }

    private static void showYearToDate(ArrayList<Transaction> transactions) {
        System.out.println("Generating Year to Date Report");

        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();

        ArrayList<Transaction> results = new ArrayList<>();

        for (Transaction t: transactions) {
            LocalDate transactionDate = t.getDate();

            if (transactionDate.getYear() == currentYear) {
                results.add(t);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No transactions found for the current year");
        } else {
            System.out.println("Found " + results.size() + " transaction(s) this year:\n");

            for (Transaction t: results) {
                System.out.println(t);
            }
        }
    }

    private static void showPreviousMonth(ArrayList<Transaction> transactions) {
        System.out.println("Generating Previous Month Report");

        LocalDate today = LocalDate.now();
        YearMonth lastMonth = YearMonth.from(today).minusMonths(1);

        ArrayList<Transaction> results = new ArrayList<>();

        for (Transaction t: transactions) {
            LocalDate transactionDate = t.getDate();
            YearMonth transactionMonth = YearMonth.from(transactionDate);

            if (transactionMonth.equals(lastMonth)) {
                results.add(t);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No transactions found for last month");
        } else {
            System.out.println("Found " + results.size() + " transaction(s) from last month: \n");

            for (Transaction transaction: results) {
                    System.out.println(transaction);
            }
        }
    }

    private static void showMonthToDate(ArrayList<Transaction> transactions) {
        System.out.println("Month to Date Report");

        LocalDate today = LocalDate.now();
        ArrayList<Transaction> results = new ArrayList<>();
        for (Transaction t: transactions) {
            LocalDate transactionDate = t.getDate();
            if (transactionDate.getYear() == today.getYear() &&
                    transactionDate.getMonth() == today.getMonth()) {
                results.add(t);
            }
        }
        if (results.isEmpty()) {
            System.out.println("No transactions for this month");
        } else {
            System.out.println("Found " + results.size() + " transaction(s) for this month: \n");
        }
        for (Transaction t: results) {
            System.out.println(t);
        }
    }

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




