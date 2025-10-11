package com.pluralsight;

public class TransactionHolder {
    private int date;
    private int time;
    private String description;
    private String vendor;
    private double amount;

    public TransactionHolder(int date, int time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static void startingMenu() {
        System.out.println("""
                Welcome! Please make your Selection:
                1) Deposit
                2) Payment
                3) Ledger
                4) Exit""");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TransactionHolder{");
        sb.append("date=").append(date);
        sb.append(", time=").append(time);
        sb.append(", description='").append(description).append('\'');
        sb.append(", vendor='").append(vendor).append('\'');
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
