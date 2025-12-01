package projects.bank;

import java.io.FileWriter;
import java.io.IOException;

public class Audit {
    private FileWriter writer;

    public Audit(String fileName) throws IOException{
        if (fileName == null) {
            throw new IllegalArgumentException("fileName cannot be null.");
        }
        writer = new FileWriter(fileName);
    }
 
    public void close() {
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(String s) {
        try {
            writer.write(s + System.lineSeparator());
        }
        catch (IOException e) { // fall back to console output
            e.printStackTrace();
        }
    }

    public void recordNoSuchAccount(Transaction t) {
        write(
            String.format(
                "%s ERROR no such account: %s", 
                Utils.timestamp(), 
                t.getAccountID()
            )
        );
    }

     public void recordNonsufficientFunds(Transaction t, Account a) {
        write(
            String.format(
                "%s ERROR nonsufficient funds: balance is %s, but withdrawl amount is %s",
                Utils.timestamp(),
                a.getBalance(),
                t.getAmount()
            )            
        );
    }
    
    public void recordExecute(Transaction t, Account a){
        write(String.format("%s INFO: %s, executed transaction of %s amount, account balance is now: %s",
            Utils.timestamp(),
            t.getAccountID(),
            t.getAmount(),
            a.getBalance()
        ));
    }
}
