
package projects.bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Bank {
    
    private Account[] accounts;
    private static int totalAcc = 0;
    
    Bank(){
        accounts = new Account[500];
    }

    
    public boolean addAccount(Account account){
        
        if(findAccount(account.getAccountID()) != -1){
            return false;
        }

        for (int i = 0; i < this.accounts.length; i++) {
            if(this.accounts[i] == null){
                this.accounts[i] = account;
                totalAcc++;
                return true;
            }
        }
        return false;
    }

   
    public int totalAccounts(){
        return totalAcc;
    }

    
    public int findAccount(String id){
        if(id == null){
            throw new IllegalArgumentException("accountID shouldn't be null.");
        }

        for(int i = 0; i < totalAccounts(); i++){
            if(accounts[i].getAccountID().equals(id)){
                return i;
            }
        }

        return -1;

    }

    public boolean loadAccounts(String fileName){
        File accountsFile = new File(fileName);

        Scanner scan;

        try {
            scan = new Scanner(accountsFile);
            while (scan.hasNextLine()) {
                String curString = scan.nextLine();
                Account a = Account.make(curString);
                
            }
            scan.close();
            return true;
        } catch(FileNotFoundException e) {
            
            e.printStackTrace(); // report error and move on
            return false;
        }
    }

    public boolean writeAccounts( String fileName ) {
        File file = new File(fileName);
        FileWriter writer;

        try {
            writer = new FileWriter(file);
            for (int i = 0; i < totalAcc; i++) {
                String line = accounts[i].toCSV();
                writer.write(line + "\n");
            }
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

}