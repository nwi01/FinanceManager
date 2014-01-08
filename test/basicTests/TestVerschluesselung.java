

import javaverschluesselungsstream.Konto;
import javaverschluesselungsstream.User;
import javaverschluesselungsstream.Verschluesselung;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test 1: Speichern eines neuen Users
 * Test 2:
 * @author niels
 */
public class TestVerschluesselung {
    
    public TestVerschluesselung() {
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
    public void testVerschluesselungBasic1(){
        //erzeugen eines neuen Users
        //Benutzername gekuerzt aufgrund des bestehenden Problems
        User us1 = new User("Mike", "testpw", null, null);
        // User speichern
        //TODO Temp. Speicherort für tests, damit der Dummy-User nicht vergammelt
        //Vll. angaben des speicherortes in xml speichern und immer auslesen ?
        boolean funkt = Verschluesselung.save(us1);
      
        assertTrue(funkt);
      
        System.out.println("Einfaches Speichern");
        User temp = Verschluesselung.load("Mike", "testpw");
            
        assertNotNull(temp);
            
        System.out.println("Einfaches Laden.");
        System.out.println(temp.getName());
            
        temp.addKonto(new Konto("KSK", "56464", "31251220", 0.0));
        System.out.println("Konto hinzugefuegt.");
                
        boolean SaveWithAcc = Verschluesselung.save(temp);
                 
        assertTrue(SaveWithAcc);

        System.out.println("Speichern mit einem Konto");
        User LoadWithAcc = Verschluesselung.load("Mike", "testpw");
         
        assertNotNull(LoadWithAcc);
        System.out.println("Laden mit einem Konto erfolgreich");
    }
}
