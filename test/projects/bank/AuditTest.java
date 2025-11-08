package projects.bank;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AuditTest {

    private String fileName;
    private Audit audit;

    @BeforeEach
    void setup() throws IOException {
        fileName = "test/audittest.log";
        audit = new Audit(fileName);
    }

    @Test
    void testRecordNoSuchAccount() {
        Transaction t = new Withdrawal("id", 10);
        audit.recordNoSuchAccount(t);
        audit.close();

        // compare audit file to expected result
        Scanner scan;
        try {
            scan = new Scanner(new File(fileName));

            // read the transaction line
            assertEquals(true, scan.hasNextLine());
            String line = scan.nextLine();
            assertEquals(
                true,
                // avoiding timestamps
                line.contains("ERROR no such account: " + t.getAccountID())
            );

            // confirm end of file
            assertEquals(false, scan.hasNextLine());
            scan.close();
        } catch (IOException e) {e.printStackTrace();}
    }


}
