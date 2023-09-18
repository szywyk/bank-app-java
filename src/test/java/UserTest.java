import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;
    private BankAccount account;

    @BeforeEach
    void setUp() {
        user = new User("John", "Doe", "johndoe", "password");
        account = user.getAccount();
    }

    @AfterEach
    void clearUsernamesHashSet() {
        User.uniqueUsernames.clear();
    }

    @Test
    void testUserConstructor() {
        // checks if a User is created correctly
        assertNotNull(user);
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("johndoe", user.getUsername());
    }

    @Test
    void testNonUniqueUsername() {
        assertThrows(IllegalArgumentException.class, () -> {
            User user1 = new User("Adam", "Moe", "johndoe", "passwd");
        });
    }

    @Test
    void testDepositMoney() {
        double initialBalance = account.getBalance();
        double depositAmount = 100.0;

        user.depositMoney(depositAmount);

        assertEquals(initialBalance + depositAmount, account.getBalance(), 0.01);
    }

    @Test
    void testWithdrawMoney() {
        account.addMoney(100.0);
        double initialBalance = account.getBalance();
        double withdrawAmount = 50.0;

        user.withdrawMoney(withdrawAmount);

        assertEquals(initialBalance - withdrawAmount, account.getBalance(), 0.01);
    }

    @Test
    void testWithdrawMoneyNotEnoughBalance() {
        double initialBalance = account.getBalance();
        double withdrawAmount = 50.0;

        user.withdrawMoney(withdrawAmount);

        assertEquals(initialBalance, account.getBalance(), 0.01);
    }

    @Test
    void testTransferMoney() {
        BankAccount receiverAccount = Bank.createAccount();
        account.addMoney(300.0);
        double initialSenderBalance = account.getBalance();
        double initialReceiverBalance = receiverAccount.getBalance();
        double transferAmount = 75.0;

        user.transferMoney(receiverAccount.getAccountNumber(), transferAmount);

        assertEquals(initialSenderBalance - transferAmount, account.getBalance(), 0.01);
        assertEquals(initialReceiverBalance + transferAmount, receiverAccount.getBalance(), 0.01);
    }
}