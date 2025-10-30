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
    
    // TODO javadoc
    abstract void execute(Account account);
    
    // TODO javadoc
    abstract boolean validate(Account account);

    // concrete methods

    // TODO javadoc
    protected Transaction(String accountID, double amount) {
        if(accountID != null){
            this.accountID = accountID;
        } else {
            throw new IllegalArgumentException("Parameter id cannot be null.");
        }
        
        this.amount = amount;
    }

    // TODO javadoc
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