package projects.bank;  // TODO correct package declartion

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest{
    private Account account;

    @BeforeEach
    void setUp(){
        account = new Account("w132629", "James", AccountType.SAVINGS, 0);
    }

    @Test
    void constructorThrowsForInvalidID() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {new Account(null, "James", AccountType.SAVINGS, 0);} // TODO use enum for account type
        );
        assertEquals(
            "The ID parameter should not be null nor empty.", 
            exception.getMessage()
        );
    }

    @Test
    void constructorThrowsForInvalidName(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {new Account("w132629", null, AccountType.SAVINGS, 0);} // TODO use enum for account type
        );
        assertEquals(
            "The name parameter can not be null nor empty.", 
            exception.getMessage()
        );
    }

    @Test
    void constructorThrowsForInvalidType(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {new Account("w132629", "James", null, 0);}
        );
        assertEquals(
            "The Account type can not be null nor empty.", 
            exception.getMessage()
        );
    }
}