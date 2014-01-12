package basicTests;

import financesoftware.base.Buchung;
import financesoftware.base.Kategorie;
import financesoftware.base.Konto;
import financesoftware.base.User;
import financesoftware.base.Verschluesselung;
import financesoftware.base.Zeitraum;
import financesoftware.base.analysis.ChartAnalysis;
import financesoftware.tools.GUIHelper;
import java.awt.Color;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test 1: Speichern eines neuen Users Test 2:
 *
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
    public void testVerschluesselungBasic1() {
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
        temp.addKategorie(new Kategorie("Test", Color.orange));
        System.out.println(Color.orange.getAlpha());
        System.out.println(Color.orange.getRed());
        System.out.println(Color.orange.getGreen());
        System.out.println(Color.orange.getBlue());
        System.out.println("Konto hinzugefuegt.");

        boolean SaveWithAcc = Verschluesselung.save(temp);

        assertTrue(SaveWithAcc);

        System.out.println("Speichern mit einem Konto");
        User LoadWithAcc = Verschluesselung.load("Mike", "testpw");

        assertNotNull(LoadWithAcc);
        System.out.println("Laden mit einem Konto erfolgreich");
        System.out.println(LoadWithAcc.getKategorien().get(0).Farbe().getAlpha());
        System.out.println(LoadWithAcc.getKategorien().get(0).Farbe().getRed());
        System.out.println(LoadWithAcc.getKategorien().get(0).Farbe().getGreen());
        System.out.println(LoadWithAcc.getKategorien().get(0).Farbe().getBlue());
    }

    /**
     * Testet ob ein User mit einer Auswertung gespeichert und geladen werden
     * kann.
     */
    @Test
    public void testVerschluesselung2() {
        User user = GUIHelper.getInstance().getDummyUser();
        user.addAuswertung(new ChartAnalysis("Test", new Zeitraum(new GregorianCalendar(), new GregorianCalendar()), user.getKategorien(), false, user.getKonten().get(0)));
        assertTrue(Verschluesselung.save(user));

        assertNotNull(Verschluesselung.load(user.getName(), user.getPassword()));
    }

    @Test
    public void testVerschluesselung3() {
        User user = GUIHelper.getInstance().getDummyUser();
//        user.addAuswertung(new ChartAnalysis("Test", new Zeitraum(new GregorianCalendar(), new GregorianCalendar()), user.getKategorien(), false, user.getKonten().get(0)));
        user.getKonten().get(0).addBuchung(new Buchung(345.4, "fef", new Zeitraum(new GregorianCalendar()), "Zweck"));
        assertTrue(Verschluesselung.save(user));

        user = Verschluesselung.load(user.getName(), user.getPassword());
        assertNotNull(user);

        assertTrue(!user.getKonten().get(0).getBuchungen().isEmpty());
    }
}
