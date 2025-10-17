# Simple Ledger App
This was a simple accounting ledger. 
It contains a main menu/home screen, options to make deposits and payments, as well as a ledger.
The ledger contains its own menu that can read through a csv file containing transactions.



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

I found this part interesting because while it took me some time to figure it out, I enjoyed putting together the methods.

<img width="1080" height="306" alt="Screenshot 2025-10-16 233245" src="https://github.com/user-attachments/assets/6efd3d1f-7702-4d84-a65b-9114fae7f523" />

I do, however, enjoy making constructors and setters/getters.
