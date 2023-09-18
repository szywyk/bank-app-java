public class BankApp {
    public static void main(String[] args) {
        // Create two users
        User user1 = new User("John", "Doe", "johndoe", "password");
        User user2 = new User("Alice", "Smith", "alicesmith", "123456");

        // Show initial user info and balances
        System.out.println("Initial User Info:");
        System.out.println("USER 1:");
        user1.showUserInfo();
        System.out.println("USER 2:");
        user2.showUserInfo();

        // Deposit money for User 1
        user1.depositMoney(500.0);
        System.out.println("\nAfter depositing 500.00 for User 1:");
        user1.showUserInfo();

        // Withdraw money from User 1
        user1.withdrawMoney(200.0);
        System.out.println("\nAfter withdrawing 200.00 from User 1:");
        user1.showUserInfo();
        System.out.println("Updated Balance for User 1: " + user1.getAccount().getBalance());

        // Transfer money from User 1 to User 2
        user1.transferMoney(user2.getAccount().getAccountNumber(), 100.0);
        System.out.println("\nAfter transferring 100.00 from User 1 to User 2:");
        user1.showUserInfo();
        user2.showUserInfo();

        // Attempt to transfer money from User 1 to non-existing user
        user1.transferMoney("invalidNumber", 100.0);
        System.out.println("\nAfter attempting to transfer 100.00 from User 1 to non-existing user:");
        user1.showUserInfo();

        // Attempt to transfer too much money from User 2 to User 1
        user2.transferMoney(user1.getAccount().getAccountNumber(), 10000.0);
        System.out.println("\nAfter attempting to transfer too much money (10000.00) from User 2 to User 1:");
        user1.showUserInfo();
        user2.showUserInfo();

        // Attempt to withdraw too much money from User 2
        user2.withdrawMoney(5000.0);
        System.out.println("\nAfter attempting to withdraw too much money (5000.00) from User 2:");
        user2.showUserInfo();

        // Show transaction history for users
        System.out.println("\nTransaction History for User 1:");
        user1.getAccount().showTransactionHistory();
        System.out.println("\nTransaction History for User 2:");
        user2.getAccount().showTransactionHistory();

        // Show user info for both users
        System.out.println("\nFinal User Info:");
        user1.showUserInfo();
        user2.showUserInfo();
    }
}