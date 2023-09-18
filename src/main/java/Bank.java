import java.util.HashMap;
import java.util.UUID;

public class Bank {
    // Collection of all accounts from the bank
    protected static HashMap<String, BankAccount> accounts = new HashMap<>();

    // Check if an account with a given account number exists
    public static boolean accountNumberExists(String accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    // Create a new bank account with a unique account number
    public static BankAccount createAccount() throws IllegalArgumentException {
        String accountNumber;
        accountNumber = UUID.randomUUID().toString();
        try {
            BankAccount newAccount = new BankAccount(accountNumber);

            // Add an account to the collection of accounts
            accounts.put(accountNumber, newAccount);
            return newAccount;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    // Find a bank account object by its account number
    public static BankAccount findAccount(String accountNumber) throws IllegalArgumentException {
        if (!accountNumberExists(accountNumber)) {
            throw new IllegalArgumentException("Account with this number does not exist.");
        }
        return accounts.get(accountNumber);
    }

    // Transfer funds from one account to another
    public static void transferFunds(String senderAccountNumber, String receiverAccountNumber, double amount) {
        try {
            BankAccount senderAccount = Bank.findAccount(senderAccountNumber);
            BankAccount receiverAccount = Bank.findAccount(receiverAccountNumber);
            senderAccount.removeMoney(amount);
            receiverAccount.addMoney(amount);

            // Make a new Transaction and add it to transaction history of a sender account
            Transaction transaction = new Transaction(amount, senderAccountNumber, receiverAccountNumber, senderAccount.getBalance());
            senderAccount.addTransaction(transaction);

            // Make a new Transaction from previous one and add it to transaction history of a receiver account
            receiverAccount.addTransaction(new Transaction(transaction, receiverAccount.getBalance()));
        } catch (IllegalArgumentException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }
}