import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = Bank.createAccount();
    }

    // Check the constructor, check the account number and initial balance are set correctly
    @Test
    void testBankAccountConstructor() {
        BankAccount account1 = new BankAccount("12345");
        assertNotNull(account);
        assertNotNull(account1);
        assertEquals("12345", account1.getAccountNumber());
        assertEquals(0.0, account.getBalance(), 0.01);
        assertEquals(0.0, account1.getBalance(), 0.01);
    }

    // Check if creation of an account with a non-unique account number throws an IllegalArgumentException
    @Test
    void testNonUniqueAccountNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount(account.getAccountNumber());
        });
    }

    // Check if the addMoney method correctly increases the balance of the account
    @Test
    void testAddMoney() {
        double initialBalance = account.getBalance();
        double depositAmount = 100.0;

        account.addMoney(depositAmount);

        // Check if account balance is correct after depositing money
        assertEquals(initialBalance + depositAmount, account.getBalance(), 0.01);
    }

    // Check if the removeMoney method correctly decreases the balance of the account
    @Test
    void testRemoveMoney() {
        // Add enough money to be able to withdraw funds
        account.addMoney(200.0);
        double initialBalance = account.getBalance();
        double withdrawAmount = 50.0;

        account.removeMoney(withdrawAmount);

        // Check if account balance is correct after withdrawing money
        assertEquals(initialBalance - withdrawAmount, account.getBalance(), 0.01);
    }

    // Check if removing more money than available in the account throws an IllegalArgumentException
    @Test
    void testRemoveMoneyNotEnoughFunds() {
        double withdrawAmount = 50.0;
        assertThrows(IllegalArgumentException.class, () -> {
            account.removeMoney(withdrawAmount);
        });
    }
}

