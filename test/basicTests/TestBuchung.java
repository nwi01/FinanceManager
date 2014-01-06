/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basicTests;

import financesoftware.base.Buchung;
import financesoftware.base.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author niels
 */
public class TestBuchung {
    User user = new User("TestUser", "test", null, null);
    
    public TestBuchung() {
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
    public void testBuchung1(){        
        Buchung bu = new Buchung(3.5, "Niels", "30.05.2013", "Dies ist der Verwenungszweck");
        assertTrue(bu.getAdressat().equals("Niels"));
        assertTrue(bu.getBetrag() == 3.5);
        //TODO
        
    }
}
