package projects.bank;

public class Withdrawal extends Transaction {

    public Withdrawal(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public void execute(Account account) {
        account.debit();
    }

    @Override 
    public boolean validate(Account account) {
        // TODO
        throw new UnsupportedOperationException("Student must implement.");
    }

}