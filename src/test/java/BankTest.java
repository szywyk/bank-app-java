import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    private BankAccount account1;
    private BankAccount account2;

    @BeforeEach
    void setUp() {
        account1 = Bank.createAccount();
        account2 = Bank.createAccount();
    }

    @AfterEach
    void clearAccountsHashMap() {
        Bank.accounts.clear();
    }

    @Test
    void testCreateAccount() {
        // Make sure the accounts are created
        assertNotNull(account1);
        assertNotNull(account2);

        // Check if accounts have different account numbers
        assertNotEquals(account1.getAccountNumber(), account2.getAccountNumber());
    }

    @Test
    void testAccountNumberExistsForExistingAccount() {
        // Check accountNumberExists method for created accounts
        assertTrue(Bank.accountNumberExists(account1.getAccountNumber()));
        assertTrue(Bank.accountNumberExists(account2.getAccountNumber()));
    }

    @Test
    void testAccountNumberExistsForNonExistingAccount() {
        // Check accountNumberExists method for invalid account number
        assertFalse(Bank.accountNumberExists("invalidAccountNumber"));
    }

    @Test
    void testFindAccount() {
        // Check if account can be found by number
        assertEquals(account1, Bank.findAccount(account1.getAccountNumber()));
        assertEquals(account2, Bank.findAccount(account2.getAccountNumber()));
    }

    @Test
    void testFindAccountNonExisting() {
        // Check if invalid account number in findAccount method generated an exception
        assertThrows(IllegalArgumentException.class, () -> {
            Bank.findAccount("invalidAccountNumber");
        });
    }

    @Test
    void testTransferFunds() {
        // Make balance more than 0 to be able to transfer funds
        account1.addMoney(1000.0);
        account2.addMoney(1000.0);

        double initialBalance1 = account1.getBalance();
        double initialBalance2 = account2.getBalance();
        double transferAmount = 220.0;

        // Transfer funds
        Bank.transferFunds(account1.getAccountNumber(), account2.getAccountNumber(), transferAmount);

        // Check if funds were successfully transferred
        assertEquals(initialBalance1 - transferAmount, account1.getBalance(), 0.01);
        assertEquals(initialBalance2 + transferAmount, account2.getBalance(), 0.01);
    }

    @Test
    void testInvalidAccountTransferFunds() {
        // Make balance more than 0 to be able to transfer funds
        account1.addMoney(1000.0);
        double initialBalance = account1.getBalance();

        // Try to transfer funds to an invalid account number
        Bank.transferFunds(account1.getAccountNumber(), "invalidAccount", 100.0);

        // Check if money was not transferred
        assertEquals(initialBalance, account1.getBalance(), 0.01);
    }

    @Test
    void testTransferFundsNotEnoughMoney() {
        account1.addMoney(1000.0);
        account2.addMoney(500.0);

        // Make transfer amount more than account balance to not be able to transfer funds
        double initialBalance1 = account1.getBalance();
        double initialBalance2 = account2.getBalance();
        double transferAmount = 2400.0;

        // Initialize transfer of too much funds
        Bank.transferFunds(account1.getAccountNumber(), account2.getAccountNumber(), transferAmount);

        // Check if funds were not transferred
        assertEquals(initialBalance1, account1.getBalance(), 0.01);
        assertEquals(initialBalance2, account2.getBalance(), 0.01);
    }

}