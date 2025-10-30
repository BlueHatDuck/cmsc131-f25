package projects.bank;

public class SavingsAccount extends Account {
    /*
     * constructur for SavingsAccount
     * @param id - the unique id for each account
     * @param ownerName - name of the account owner
     * @param balance - account balance
     */
    public SavingsAccount(String id, String ownerName, double balance) {
        super(id, ownerName, balance);
    }

    @Override
    public AccountType getType() {
        return AccountType.SAVINGS;
    }

}