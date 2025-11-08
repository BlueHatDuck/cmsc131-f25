/** TODO list
 * 
 * Write test coverage. See Co-op project for examples.
 */
package projects.bank;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest {

    private Account ckgAccount;
    private Transaction defaultDeposit, defaultWithdrawal;
    private Audit audit;

    @BeforeEach
    void setUp() throws IOException {
        ckgAccount = new CheckingAccount("id", "owner", 10.0);
        defaultDeposit = new Deposit("id", 2.51);
        defaultWithdrawal = new Withdrawal("id", 1.75);
        audit = new Audit("data/audit.txt");
    }

    @Test
    void testConstructorDataValidation() {

        /* Tests logic in Transaction class, 
           so I can use either subclass here */

        Exception e = assertThrows(
            IllegalArgumentException.class,
            () -> {new Deposit(null, 0.01);}
        );
        assertEquals(
            "Parameter accountID cannot be null.",
            e.getMessage()
        );
        
        e = assertThrows(
            IllegalArgumentException.class,
            () -> {new Deposit("id", -0.01);}
        );
        assertEquals(
            "Parameter amount must be positive.",
            e.getMessage()
        );
    }

    @Test
    void testMakeThrowsOnNullInput() {
        Exception e = assertThrows(
            IllegalArgumentException.class,
            () -> {Transaction.make(null);}
        );
        assertEquals(
            "Parameter inputLine cannot be null.",
            e.getMessage()
        );
    }

    @Test
    void testMakePreservesData() {
        String[] tokens1 = {"withdrawal", "rp332960", "267.57"};
        String[] tokens2 = {"deposit", "vc906780", "766.53"};

        Transaction tx1 = Transaction.make(String.join(",", tokens1));
        assertEquals(Withdrawal.class, tx1.getClass());
        assertEquals(tokens1[1], tx1.getAccountID());
        assertEquals(Double.valueOf(tokens1[2]), tx1.getAmount(), 0.01);

        Transaction tx2 = Transaction.make(String.join(",", tokens2));
        assertEquals(Deposit.class, tx2.getClass());
        assertEquals(tokens2[1], tx2.getAccountID());
        assertEquals(Double.valueOf(tokens2[2]), tx2.getAmount(), 0.02);
    }

    @Test
    void testValidateDeposit() {
        assertEquals(
            true, 
            defaultDeposit.validate(ckgAccount, audit)
            );
        
    }

    @Test
    void testValidateWithdrawal() {
        assertEquals(
            true, 
            defaultWithdrawal.validate(ckgAccount, audit)
            );

        defaultWithdrawal = new Withdrawal("sdfadf", 100.0);

        assertEquals(
            false, 
            defaultWithdrawal.validate(ckgAccount, audit)
            );
    }

    @Test
    void testExecuteDeposit() {
        defaultDeposit.execute(ckgAccount, audit);
        double currentBal = ckgAccount.getBalance();
        assertEquals(
            12.51,
            currentBal
            );
    }

    @Test
    void testExecuteWithdrawal() {
        defaultWithdrawal.execute(ckgAccount, audit);
        double currentBal = ckgAccount.getBalance();
        assertEquals(
            8.25,
            currentBal
            );
    }

}