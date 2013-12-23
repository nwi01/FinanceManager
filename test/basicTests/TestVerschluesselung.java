package basicTests;

import financesoftware.base.User;
import financesoftware.base.Verschluesselung;
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
        User us1 = new User("TestUser", "test", null, null);
        // User speichern
        //TODO Temp. Speicherort für tests, damit der Dummy-User nicht vergammelt
        //Vll. angaben des speicherortes in xml speichern und immer auslesen ?
        assertTrue(Verschluesselung.save("TestUser", "test", us1));
    }
}
