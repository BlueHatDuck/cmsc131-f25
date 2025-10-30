package projects.bank;

public class Deposit extends Transaction {

    /*
     * Constructer for the class
     * @param accountID - ID of the account going through a transaction 
     * @param amount - amount credited to the account that 
     */
    public Deposit(String accountID, double amount) {
        super(accountID, amount);  
    }
    
    /*
     * Calls the credit method
     * @param account - account the deposit is applied to
     */
    @Override
    public void execute(Account account) {
        double amount = super.getAmount();
        account.credit(amount);
    }


    /*
     * Checks to make sure execute is allowed to be performed
     * @param - account the deposit must be validated for
     * @return - true if succesful false if not
     */
    @Override 
    public boolean validate(Account account) {
        double amount = super.getAmount();
        if(amount > 0){
            return true;
        } else {
            System.out.println("Amount must be greater than 0!");
            return false;
        }
    }
    
    /*
     * Accesor method
     * @return - returns the type of transaction
     */
    @Override
    public TransactionType getType(){
        return TransactionType.DEPOSIT;
    }

}