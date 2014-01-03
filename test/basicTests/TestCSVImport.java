package basicTests;

import financesoftware.base.Buchung;
import financesoftware.base.CSVImport;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test 1: Speichern eines neuen Users
 * Test 2:
 * @author niels
 */
public class TestCSVImport {
    
    public TestCSVImport() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCSVIMportBasic1(){
        
        List<Buchung> lTest = CSVImport.Import("KSK", "./KSKTestDatei.csv");
        
        if(lTest.size() > 0)
        {
            System.out.println("Import stattgefunden");
            System.out.println(lTest.get(0).getDatum().getStartzeit().getTime().toString());
        }
        else
        {
            System.out.println("Kein Import");
        }
    }
}
