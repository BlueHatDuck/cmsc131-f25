/**
 * TODO list
 * 
 * write accessor for amount
 * 
 * consider validating amount in constructor
 * 
 * javadocs
 */
package projects.bank;

abstract class Transaction {

    private final String accountID;
    private final double amount;

    // abstract methods, to be overridden by subclasses
    
    /*
     * abstract method that will either execute the credit or debit method in account
     * @param - the account that's balnce will change
     */
    abstract void execute(Account account);
    
    /*
     * checks if the execute method should be allowed to be performed
     * @param - the account that the method execute is used on
     * @return - returns true if validation succeds, false otherwise
     */
    abstract boolean validate(Account account);

    // concrete methods

    
    /**
     * Constructs a Transaction for the specified account and amount.
     * @param accountID can't be null, identifies the account associated with this transaction
     * @param amount total being credited or debited
     * @throws IllegalArgumentException if accountID is null
     */
    protected Transaction(String accountID, double amount) {
        if(accountID != null){
            this.accountID = accountID;
        } else {
            throw new IllegalArgumentException("Parameter id cannot be null.");
        }
        
        this.amount = amount;
    }

    
    /**
     * Creates a Transaction instance by passing through the input
     * @param inputLine a line from teh CSV
     * @return a Withdrawal or Deposit constructed from the tokens
     * @throws IllegalArgumentException if inputLine is null or if TYPE is not a valid TransactionType
     */
    protected static Transaction make(String inputLine) {
        if (inputLine == null) {
            throw new IllegalArgumentException("Parameter inputLine cannot be null.");
        }
        String[] tokens = inputLine.split(",");
        TransactionType type = TransactionType.valueOf(tokens[0].toUpperCase());
        String accountID = tokens[1];
        double amount = (double) Double.valueOf(tokens[2]);
        
        if(type.equals(TransactionType.WITHDRAWAL)){
            return new Withdrawal(accountID, amount);
        } else {
            return new Deposit(accountID, amount);
        }

    }

    public String getAccountID() {
        return accountID;
    }

    public double getAmount(){
        return amount;
    }

    abstract TransactionType getType();



}