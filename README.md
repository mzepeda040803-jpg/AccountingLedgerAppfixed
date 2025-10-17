# Simple Ledger App
This project demonstrates a simple accounting ledger. It features menu options within menu options. The menus include a home screen menu, a ledger menu, a reports menu and a data file called transactions.csv. The program allows you to make deposits, make payments, display all entries in the ledger and generate reports. The file contains data organized by date, time, description, vendor, and amount. This program also displays the usage of buffered readers and writers, switches, arrays, if and else statements, for loops, and the usage of toString.

#Reports 
The reports feature generates data from the csv file that can be organized by year/month/day, the previous year, a search by vendor function and the option to leave and return to the previous menu.



#Interesting Code
```java
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
 ```

I found this part interesting as it gave me more practice with learning how to create methods and getting the methods to run the correct data. The methods are named after the action that they are meant to do. The code within the methods attempts to call on the date/time data within the csv file and organizing that data based on which method called it. This include calling data based on month to date, by the previous month, year to date, by the previous year, and by the vendor. The methods use a scanner to prompt for user input; this user input is used to call forward the requested data from the csv file.

<img width="1080" height="306" alt="Screenshot 2025-10-16 233245" src="https://github.com/user-attachments/assets/6efd3d1f-7702-4d84-a65b-9114fae7f523" />

#Interesting Code
One piece of code that I found interesting was the constructors and the getters/setters.

Constructor: a method used to create objects from a class. Constructors help to set up the object's data and is also known as fields or attributes. It's called automatically when you create an object using "new". (These are the first set of code in the picture posted.)

Getter: allows you to read the value of a variable and return that value. Setter: allows you to set and change the value of a variable in a class; Getters/Setters describe an object and store the values that the object needs to know. (These are the second set of code in the picture posted.)


