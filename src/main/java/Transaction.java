import java.time.LocalDateTime;

public class Transaction {
    private final LocalDateTime date = LocalDateTime.now(); // Transaction date and time
    private String type; // Type of transaction (TRANSFER IN/OUT, DEPOSIT, WITHDRAW)
    private double amount;
    private String transactionNumber;
    private String receiverAccountNumber;
    private String senderAccountNumber;
    private double balanceAfterTransaction;
    private static int transactionCounter = 0; // Counter for generating unique transaction numbers

    // Constructor for deposit/withdraw transactions
    public Transaction(double amount, String type, double balanceAfterTransaction) {
        this.amount = amount;
        this.type = type;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.transactionNumber = generateTransactionNumber();
    }

    // Constructor for send (transfer out) transactions
    public Transaction(double amount, String senderAccountNumber, String receiverAccountNumber, double balance) {
        this.amount = amount;
        this.type = "TRANSFER OUT";
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.balanceAfterTransaction = balance;
        this.transactionNumber = generateTransactionNumber();
    }

    // Constructor for receive (transfer in) transactions
    public Transaction(Transaction transaction, double balance) {
        this.amount = transaction.getAmount();
        this.type = "TRANSFER IN";
        this.senderAccountNumber = transaction.getSenderAccountNumber();
        this.receiverAccountNumber = transaction.getReceiverAccountNumber();
        this.balanceAfterTransaction = balance;
        this.transactionNumber = transaction.getTransactionNumber();
    }

    // Generate a unique transaction number
    private String generateTransactionNumber() {
        transactionCounter++;
        return "tx:" + transactionCounter;
    }

    // Getters for transaction attributes
    public LocalDateTime getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public String getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }
}