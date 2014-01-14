package basicTests;

import financesoftware.base.Buchung;
import financesoftware.base.CSVImport;
import financesoftware.base.Konto;
import financesoftware.base.User;
import financesoftware.tools.GUIHelper;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
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
        /*
        Es wir ein User mit einem Konto mit der Nummer 45614 benoetigt.
        */
        
        User lUser = new User("test", "test");
        lUser.addKonto(new Konto("allgKonto", "45614", "31251220", 100.00));
        GUIHelper.getInstance().setUser(lUser);
        
        
        boolean lKSKTest = CSVImport.Import("KSK", "./KSKTestDatei.csv");
        assertTrue(lKSKTest);
        System.out.println("KSKImport stattgefunden");
        System.out.println(lUser.getKonten().get(0).getAktuellerKontostand());
        assertTrue(lUser.getKonten().get(0).getBuchungen().get(0).getBetrag() == -555.78);
        
        
        /*
        boolean lPBTest = CSVImport.Import("PB", "./PBTestDatei.csv");
        assertTrue(lPBTest);
        System.out.println("PBImport stattgefunden");
        assertTrue(lUser.getKonten().get(0).getAktuellerKontostand() == -555.78);
       */
                
        boolean lVBTest = CSVImport.Import("VB", "./VBTestDatei.csv");
        assertTrue(lVBTest);
        System.out.println("VBImport stattgefunden");
        assertTrue(lUser.getKonten().get(0).getBuchungen().get(1).getBetrag() == -555.78);
    }
}
