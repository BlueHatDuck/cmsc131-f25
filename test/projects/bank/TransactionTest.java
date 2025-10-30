/** TODO list
 * 
 * Write test coverage. See Co-op project for examples.
 */
package projects.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest {

    private Account ckgAccount;
    private Transaction defaultDeposit, defaultWithdrawal;

    @BeforeEach
    void setUp() {
        ckgAccount = new CheckingAccount("id", "owner", 10.0);
        defaultDeposit = new Deposit("id", 2.51);
        defaultWithdrawal = new Withdrawal("id", 1.75);
    }

    @Test
    void testConstructorDataValidation() {
        Exception e = assertThrows(
            IllegalArgumentException.class,
            () -> {new Deposit(null, 0);}
        );
        assertEquals(
            "Parameter id cannot be null.",
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
        String inputLine = "deposit,rp332960,511.23";
        Transaction newTrans = Transaction.make(inputLine);
        assertEquals(
            "rp332960",
            newTrans.getAccountID()
        );

        assertEquals(511.23, 
        newTrans.getAmount()
        );

        assertEquals(
        TransactionType.DEPOSIT, 
        newTrans.getType());

    }

    @Test
    void testValidateDeposit() {
        assertEquals(
            true, 
            defaultDeposit.validate(ckgAccount)
            );
        
        defaultDeposit = new Deposit("safsdfs", 0);

        assertEquals(
            false, 
            defaultDeposit.validate(ckgAccount)
            );
    }

    @Test
    void testValidateWithdrawal() {
        assertEquals(
            true, 
            defaultWithdrawal.validate(ckgAccount)
            );
        
        defaultWithdrawal = new Withdrawal("asas", 0);

        assertEquals(
            false, 
            defaultWithdrawal.validate(ckgAccount)
            );

        defaultWithdrawal = new Withdrawal("sdfadf", 100.0);

        assertEquals(
            false, 
            defaultWithdrawal.validate(ckgAccount)
            );
    }

    @Test
    void testExecuteDeposit() {
        defaultDeposit.execute(ckgAccount);
        double currentBal = ckgAccount.getBalance();
        assertEquals(
            12.51,
            currentBal
            );
    }

    @Test
    void testExecuteWithdrawal() {
        defaultWithdrawal.execute(ckgAccount);
        double currentBal = ckgAccount.getBalance();
        assertEquals(
            8.25,
            currentBal
            );
    }

}