package projects.bank;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class BankTest {
    private Bank bank;

    @BeforeEach
    public void setUp(){
        bank = new Bank();
    }

    @Test
    void testAddAccounts() {
        Account acc1 = new Account("wz13342", "James", AccountType.CHECKING, 1500.0);
        boolean result = bank.addAccount(acc1);
        assertEquals(true, result);
    }

    @Test
    void testDuplicates() {
         Account acc1 = new Account("wz13342", "James", AccountType.CHECKING, 1500.0);
         Account acc2 = new Account("wz13342", "James", AccountType.CHECKING, 1500.0);
        bank.addAccount(acc1);
        boolean result = bank.addAccount(acc2);
        assertEquals(false, result);
    }

    @Test
    void testTotalAccounts(){
        Account acc1 = new Account("wz13342", "James", AccountType.CHECKING, 1500.0);
        Account acc2 = new Account("wz41234", "George", AccountType.SAVINGS, 1000.0);
        bank.addAccount(acc1);
        bank.addAccount(acc2);
        int result = bank.totalAccounts();
        assertEquals(2, result);
    }

    @Test
    void testFindAccounts(){
        Account acc1 = new Account("wz13342", "James", AccountType.CHECKING, 1500.0);
        Account acc2 = new Account("wz41234", "George", AccountType.SAVINGS, 1000.0);
        bank.addAccount(acc1);
        bank.addAccount(acc2);
        String id = acc2.getAccountID();
        int result = bank.findAccount(id);
        assertEquals(1, result);
        int failCase = bank.findAccount("wz32458");
        assertEquals(-1, failCase);
    }


}
