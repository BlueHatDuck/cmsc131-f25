package projects.bank;

/**
 * Represents a simple bank account.
 *
 * <p>Account instances have an immutable account ID, owner name, and account type.
 * The balance is mutable and initialized via the constructor's initialBalance parameter.
 *
 * <p>This class performs basic null checks in the constructor and will throw
 * {@link IllegalArgumentException} when required constructor parameters are null.
 */
public class Account {
    
    private final String accountID;
    private final String ownerName;
    private double balance;
    private final AccountType accountType;

    /**
     * Create a new Account.
     *
     * @param accountID      unique identifier for the account; must be non-null
     * @param ownerName      name of the account owner; must be non-null
     * @param accountType    type of the account; must be non-null
     * @param initialBalance initial balance for the account (may be negative if allowed by business rules)
     *
     * @throws IllegalArgumentException if {@code accountID}, {@code ownerName}, or {@code accountType} is null
     */
    public Account(String accountID, String ownerName, AccountType accountType, double initialBalance) {

        if(accountID != null){
            this.accountID = accountID;
        } else {
            throw new IllegalArgumentException(
                "The ID parameter should not be null nor empty."
                );
        }
        
        if(ownerName != null){
            this.ownerName = ownerName;
        } else {
            throw new IllegalArgumentException(
                "The name parameter can not be null nor empty."
                );
        }

        this.balance = initialBalance;

        if(accountType != null){
            this.accountType = accountType;
        } else {
            throw new IllegalArgumentException(
                "The Account type can not be null nor empty."
            );
        }
    }

    /**
     * Returns the account's identifier.
     *
     * @return non-null account ID
     */
    public String getAccountID() {
        return accountID;
    }

    /**
     * Returns the owner's name.
     *
     * @return non-null owner name
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Returns the current balance of the account.
     *
     * @return the account balance as a double
     */
    public double getBalance() {
        return balance;
    }

     public AccountType getType() { 
        return accountType; 
    }

    public static Account make(String inputLine){
        if (inputLine == null){
            throw new IllegalArgumentException("Param inputLine can't be null.");
        }
        //.split returns an array based on where you split the values so this array contains 4 values
        String[] tokens = inputLine.split(",");
        AccountType type = AccountType.valueOf(tokens[0].toUpperCase());
        String id = tokens[1];
        String owner = tokens[2];
        double funds = Double.parseDouble(tokens[3]);

        return new Account(id, owner, type, funds);
    }

    public String toCSV(){
        return accountType + "," + accountID + "," + ownerName + "," + balance;
    }

}