
package projects.bank;

public class Withdrawal extends Transaction {
    /*
     * Constructer for the class
     * @param accountID - ID of the account going through a transaction 
     * @param amount - amount credited to the account that 
     */
    public Withdrawal(String accountID, double amount) {
        super(accountID, amount);
    }

    /*
     * Calls the debit method
     * @param account - account the deposit is applied to
     */
    @Override
    public void execute(Account account, Audit audit) {
        account.debit(getAmount());
        audit.recordExecute(this, account);
    }

    /*
     * Checks to make sure execute is allowed to be performed
     * @param - account the deposit must be validated for
     * @return - true if balance is larger than or equal to amount, false if not
     */
    @Override 
    public boolean validate(Account account, Audit audit) {
        if(getAmount() <= account.getBalance()){
            return true;
        } else {
            audit.recordNonsufficientFunds(this, account);
            return false;
        }
    }

    @Override
    public TransactionType getType(){
        return TransactionType.WITHDRAWAL;
    }

}