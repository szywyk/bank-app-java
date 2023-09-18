import java.util.ArrayList;

public class BankAccount {
    private String accountNumber;
    private double balance;
    private ArrayList<Transaction> transactionHistory = new ArrayList<>();

    // Constructor to create a BankAccount with a unique account number
    public BankAccount(String accountNumber) {
        // Check if the account number already exists
        if (Bank.accountNumberExists(accountNumber)) {
            throw new IllegalArgumentException("Account number is already taken.");
        }
        this.accountNumber = accountNumber;
        this.balance = 0.0; // Initialize the balance to zero
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    // Method to add money to the account
    public void addMoney(double amount) {
        this.balance += amount;
    }

    // Method to remove money from the account
    public void removeMoney(double amount) throws IllegalArgumentException {
        // Check if there are enough funds to withdraw
        if (this.balance < amount) {
            throw new IllegalArgumentException("There are not enough funds to withdraw/transfer.");
        } else {
            this.balance -= amount;
        }
    }

    // Method to add a transaction to the account's transaction history
    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    // Method to display the transaction history of the account
    public void showTransactionHistory() {
        System.out.println("----------------------------------------");
        System.out.println("TRANSACTION HISTORY: ");
        System.out.println();
        for (Transaction transaction : transactionHistory) {
            System.out.println("Transaction number: " + transaction.getTransactionNumber());
            System.out.println("Transaction type: " + transaction.getType());

            // If sender and receiver are empty, it is a deposit or withdraw transaction
            if (transaction.getSenderAccountNumber() != null && transaction.getReceiverAccountNumber() != null) {
                System.out.println("Sender: " + transaction.getSenderAccountNumber());
                System.out.println("Receiver: " + transaction.getReceiverAccountNumber());
            }

            System.out.println("Amount: " + transaction.getAmount());
            System.out.println("Balance after transaction: " + transaction.getBalanceAfterTransaction());
            System.out.println("Date: " + transaction.getDate());
            System.out.println();
        }
        System.out.println("----------------------------------------");
    }
}