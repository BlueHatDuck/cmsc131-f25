
package projects.bank;

public class Deposit extends Transaction {

    public Deposit(String accountID, double amount) {
        super(accountID, amount);
        
    }

    @Override
    public void execute(Account accont) {
        // TODO
        throw new UnsupportedOperationException("Student must implement.");
    }

    @Override 
    public boolean validate(Account account) {
        // TODO
        throw new UnsupportedOperationException("Student must implement.");
    }

}
