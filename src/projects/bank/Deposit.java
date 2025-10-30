package projects.bank;

public class Deposit extends Transaction {

    public Deposit(String accountID, double amount) {
        super(accountID, amount);  
    }
    
    @Override
    public void execute(Account account) {
        double amount = super.getAmount();
        account.credit(amount);
    }

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

    @Override
    public TransactionType getType(){
        return TransactionType.DEPOSIT;
    }

}