import java.util.HashSet;
import java.util.Set;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private BankAccount account;

    // Collection of all usernames in the bank
    protected static Set<String> uniqueUsernames = new HashSet<>();

    public User(String firstName, String lastName, String username, String password) {

        // Check if the username is unique; if not, throw an exception
        if (!isUsernameUnique(username)) {
            throw new IllegalArgumentException("Username is already taken.");
        }

        // Create a new bank account for the user
        this.account = Bank.createAccount();

        // Initialize user attributes
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;

        // Add the username to the set of unique usernames
        uniqueUsernames.add(this.username);
    }

    // Check if a username is unique among all users
    public static boolean isUsernameUnique(String username) {
        return !uniqueUsernames.contains(username);
    }

    // Deposit money into the user's bank account
    public void depositMoney(double amount) {
        account.addMoney(amount);

        // Add new Transaction to the user's transaction history
        account.addTransaction(new Transaction(amount, "DEPOSIT", account.getBalance()));
    }

    // Withdraw money from the user's bank account
    public void withdrawMoney(double amount) {
        try {
            account.removeMoney(amount);

            // Add new Transaction to the user's transaction history
            account.addTransaction(new Transaction(amount, "WITHDRAW", account.getBalance()));
        } catch (IllegalArgumentException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }

    // Transfer money from the user's account to another account
    public void transferMoney(String receiverAccountNumber, double amount) {
        Bank.transferFunds(account.getAccountNumber(), receiverAccountNumber, amount);
    }

    // Method to display information about the user and their balance
    public void showUserInfo() {
        System.out.println("----------------------------------------");
        System.out.println("USER INFO:");
        System.out.println();
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Username: " + username);
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Balance: " + account.getBalance());
        System.out.println("----------------------------------------");
    }

    // Getters for user attributes
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public BankAccount getAccount() {
        return account;
    }

}