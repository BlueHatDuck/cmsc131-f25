/**
 * TODO list
 * 
 * correct the call to Account's debit method. double check its input requirement
 * 
 * implement validation. move the logic from the inner conditional in debit into your validate method
 */
package projects.bank;

public class Withdrawal extends Transaction {

    public Withdrawal(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public void execute(Account account) {
        double amount = super.getAmount();
        account.debit(amount);
    }

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