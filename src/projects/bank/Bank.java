package projects.bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Bank {

    private Account[] accounts;
    private int idxNextAccount;
    private final int newAccountsIncrement = 100;

    public Bank() {
        accounts = new Account[newAccountsIncrement];
    }

    /**
     * Add an account to this Bank.
     * @param acct - Account to add.
     * @return - true if successful, false if unsuccessful.
     * @throws IllegalArgumentException - If acct is null.
     */
    public boolean add(Account acct) {
        if (acct == null) {
            throw new IllegalArgumentException("account must not be null.");
        }
        if (find(acct.getID()) == -1) {
            try {
                accounts[idxNextAccount] = acct;
            } catch(ArrayIndexOutOfBoundsException e) {
                Account[] accountsExtended = new Account[
                    accounts.length + newAccountsIncrement
                ];
                for (int idx = 0; idx < accounts.length; idx++) {
                    accountsExtended[idx] = accounts[idx];
                }
                accountsExtended[idxNextAccount] = acct;
                accounts = accountsExtended;
            }
            idxNextAccount++;
            return true;
        } else {
            return false;
        }

    }

    /**
     * Find an account in this Bank using its unique ID.
     * @param accountID - Unique ID.
     * @return - Reference to account in this Bank, or -1 if no match.
     * @throws IllegalArgumentException if accountID is null.
     */
    public int find(String accountID) {
        if (accountID == null) {
            throw new IllegalArgumentException("accountID must not be null.");
        }
        for (int idxAcct = 0; idxAcct < idxNextAccount; idxAcct++) {
            if (accounts[idxAcct].getID().equals(accountID)) {
                return idxAcct;
            }
        }
        return -1;
    }

    /**
     * @return - Number of accounts in this Bank.
     */
    public int getCount() {
        return idxNextAccount;
    }

    public boolean loadAccounts(String filename){
        boolean result = true;
        File inputFile = new File(filename);
        Scanner scan;
        try {
            scan = new Scanner(inputFile);
            while (scan.hasNextLine()) {
                String csvString = scan.nextLine();
                Account account = Account.make(csvString);
                add(account);
            }
            scan.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
        }
    
    
    public boolean writeAccounts(String filename){
        File file = new File(filename);
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            for (int idx = 0; idx < idxNextAccount; idx++) {
                Account account = accounts[idx];
                String accountCsv = account.toCSV();
                writer.write(accountCsv + System.lineSeparator());
            }
            writer.close();
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Account[] getAccounts(){
        return accounts;
    }
    
    /**
     * Process transactions that are stored in a CSV file.
     * @param fileName - Points to CSV file.
     * @return - Number of transactions that were processed.
     */
    public int processTransactions(String trsFileName, String auditFileName){
        int numTrsProc = 0;
        try {
            Audit audit = new Audit(auditFileName);
            Scanner scan;
            try {
                scan = new Scanner(new File(trsFileName));
                while (scan.hasNextLine()) {
                    Transaction trs = Transaction.make(scan.nextLine());
                    int exists = find(trs.getAccountID());
                    if(exists != -1){
                        Account target = accounts[find(trs.getAccountID())];
                        if(trs.validate(target, audit)){
                            trs.execute(target, audit);
                        }
                    } else {
                        audit.recordNoSuchAccount(trs);
                    }
                    numTrsProc++;
                }
                scan.close();
            } catch (FileNotFoundException e){ 
                e.printStackTrace();
            }
            audit.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        
        return numTrsProc;
    }


}