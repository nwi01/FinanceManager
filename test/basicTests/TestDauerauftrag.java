/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basicTests;

import financesoftware.base.Buchung;
import financesoftware.base.Dauerauftrag;
import financesoftware.base.Kategorie;
import financesoftware.base.Konto;
import financesoftware.base.User;
import financesoftware.base.Zeitraum;
import java.awt.Color;
import static java.awt.Color.blue;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Kristina
 */
public class TestDauerauftrag {
    User user = new User("TestUser", "test", null, null);
    
    public TestDauerauftrag() {
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
    public void testDauerauftrag1(){    
        Zeitraum.Intervall intervall1 = Zeitraum.Intervall.WOECHENTLICH;
        Konto k = new Konto("Konto1", "123456", "51236", 10.0);
        Kategorie kat1 = new Kategorie("abc", blue);
        
        // zweimal gebucht, letzte Ausfuehrung am 11.Jan 2014
        Dauerauftrag d = new Dauerauftrag(5.30, "Hans", "04.01.2014", intervall1, 5, "verwendung",kat1);
        k.addDauerauftrag(d);
        assertTrue(k.getDauerauftraege().size() == 1);
        k.buchen(d);
        assertTrue(k.getBuchungen().size() == 3);
        assertTrue(d.getAdressat().equals("Hans"));
        assertTrue(d.getBetrag() == 5.3);
        assertTrue(d.getAktiv() == true);
        assertTrue(d.getDatum().getAnzahlWdh() == 5);
        assertTrue(d.getVerwendungszweck().equals("verwendung"));
        System.out.println(d.getDatum().getEndezeit().getTime());
        System.out.println(d.getDatum().getIntervall());
        System.out.println(d.getDatum().getStartzeit().getTime());
        System.out.println(d.getLetzteAusfuehrung().getTime());
        
        // keinmal gebucht, aktiv auf false
        Dauerauftrag d1 = new Dauerauftrag(5.30, "Hans", "31.01.2014", intervall1, 5, "verwendung",kat1);
        k.addDauerauftrag(d1);
        assertTrue(k.getDauerauftraege().size() == 2);
        k.buchen(d1);
        assertTrue(k.getBuchungen().size() == 3);         
        assertTrue(d1.getAktiv() == false);
    }
    
}