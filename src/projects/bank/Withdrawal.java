
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
    public void execute(Account account) {
        double amount = super.getAmount();
        account.debit(amount);
    }

    /*
     * Checks to make sure execute is allowed to be performed
     * @param - account the deposit must be validated for
     * @return - true if amount is greater than zero and balance is larger than or equal to amount, false if either condition is false
     */
    @Override 
    public boolean validate(Account account) {
        double balance = account.getBalance();
        double amount = super.getAmount();
        if(amount > 0){
            if(balance >= amount){
                return true;
            } else {
                System.out.println("The withdrawl amount: " + amount + " is greater than the current balance: " + balance);
                return false;
            }
        } else {
            System.out.println("Amount must be greater than 0!");
            return false;
        }
    }

    @Override
    public TransactionType getType(){
        return TransactionType.WITHDRAWAL;
    }

}