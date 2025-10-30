package projects.bank;

public class CheckingAccount extends Account {
    /*
     * constructur for CheckingAccount
     * @param id - the unique id for each account
     * @param ownerName - name of the account owner
     * @param balance - account balance
     */
    public CheckingAccount(String id, String ownerName, double balance) {
        super(id, ownerName, balance);
    }
    
    @Override
    public AccountType getType() {
        return AccountType.CHECKING;
    }

}