
package projects.bank;

/**
 * Simple in-memory bank that stores Account objects in a fixed-size array.
 *
 * <p>This Bank uses a fixed capacity (100) internal array. Accounts are stored
 * in the first available null slot. Uniqueness is determined by the Account's
 * accountID (string equality). The API is intentionally minimal and not
 * thread-safe; callers are responsible for synchronization if needed.
 */
public class Bank {
    
    private Account[] accounts;

    /**
     * Create a new Bank with a fixed capacity of 100 accounts.
     *
     * <p>The constructor is package-private to match the project's test usage.
     */
    Bank(){
        accounts = new Account[100];
    }

    /**
     * Add an account to the bank if an account with the same accountID does not
     * already exist and there is capacity.
     *
     * <p>Note: the parameter name "accounts" shadows the internal field name.
     * The method checks for duplicates by calling {@link #findAccount(Account)}.
     *
     * @param accounts the Account to add (must be non-null)
     * @return true if the account was added; false if a duplicate exists or the bank is full
     */
    public boolean addAccount(Account accounts){
        
        if(findAccount(accounts) != -1){
            return false;
        }

        for (int i = 0; i < this.accounts.length; i++) {
            if(this.accounts[i] == null){
                this.accounts[i] = accounts;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of non-null accounts currently stored.
     *
     * @return count of stored accounts (0..capacity)
     */
    public int totalAccounts(){
        int total = 0;
        for(int i = 0; i < this.accounts.length; i++){
            if(this.accounts[i] != null)
                total++;
        }
        return total;
    }

    /**
     * Find the index of the provided account in the internal array.
     *
     * <p>Comparison is performed by calling {@code getAccountID()} on both sides
     * and using {@link String#equals(Object)}. If the provided account is null
     * or no matching account is found, this method returns -1.
     *
     * @param accounts the Account to find (may be null)
     * @return the 0-based index of a matching account, or -1 if not found
     */
    public int findAccount(Account accounts){
        if(accounts != null){
            for(int i = 0; i < this.accounts.length; i++){
                if(this.accounts[i] != null && this.accounts[i].getAccountID().equals(accounts.getAccountID())){
                    return i;
                }
            }
        }
        return -1;

    }
}
