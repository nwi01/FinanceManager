package financesoftware.tools;

import financesoftware.base.Buchung;
import financesoftware.base.Kategorie;
import financesoftware.base.Konto;
import financesoftware.base.User;
import financesoftware.base.Verschluesselung;
import financesoftware.base.Zeitraum;
import financesoftware.base.analysis.Analysis;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.jfree.chart.JFreeChart;

/**
 * Singelton
 *
 * @author nwi01
 */
public class GUIHelper {

    private static GUIHelper instance;
    private User user;

    private GUIHelper() {
    }

    public static GUIHelper getInstance() {
        if (instance == null) {
            instance = new GUIHelper();
        }
        return instance;
    }

    /**
     * Gibt ein Array zurueck, welches die verschiedenen, vom Benutzer
     * angelegten Auswertungen beinhaltet. Muss/ Sollte nicht unbedingt ein
     * String[] sein, keine Ahnung was die JComboBox da verarbeiten kann.
     *
     * @return
     */
    public String[] getAllAnaysisVariants() {
        return new String[]{};
    }

    /**
     * Uebergabeparameter noch nicht sicher Diese Methode soll ein
     * CompareAnalysis Objekt erzeugen und speichern.
     */
    public void createAndSaveCompareAnalysis(String name, Zeitraum zeitraum, List<Kategorie> kategorien, List<Konto> konten) {

    }

    /**
     * Uebergabeparameter noch nicht sicher Diese Methode soll ein ChartAnalysis
     * Objekt erzeugen und speichern.
     */
    public void createAndSaveChartAnalysis(String name, Zeitraum zeitraum, List<Kategorie> kategorien, List<JFreeChart> charts) {

    }

    // ============================================ Buchungen ============================================== \\
    /**
     * Buchung: Enthählt: String[<Buchung>][<Inhalt der Buchung>] Darf nicht
     * null zurueckliefern.
     *
     * @param konto
     * @return
     */
    public static String[][] getBookingData(Konto konto) {
        String[][] result = new String[konto.getBuchungen().size()][3];
        for (int i = 0; i < konto.getBuchungen().size(); i++) {
            result[i][0] = konto.getBuchungen().get(i).getDatum().toString();
            result[i][1] = String.valueOf(konto.getBuchungen().get(i).getBetrag());
            result[i][2] = konto.getBuchungen().get(i).getAdressat();
//            return new String[][]{{"Datum", "Betrag", "Empfänger"}, {"erfe","wdwd","wwww"}};
        }
        return result;
//        return new String[][]{{"Datum", "Betrag", "Empfänger"}};
    }

    /**
     * Wird mit der getBookingData() Methode aufgerufen und gibt ein Array
     * zurueck, welches die Namen "[<Inhalt der Buchung>]" enthaehlt
     *
     * @return
     */
    public static String[] getBookingColumnName(Konto konto) {
        return new String[]{"Datum", "Betrag [Euro]", "Empfänger"};
    }

    public static double getCurrentMoney(Konto konto) {
        return konto.getAktuellerKontostand();
    }

    /**
     * Liefert alle Konten des aktuellen Users zurueck
     *
     * @return
     */
    public static Konto[] getAllBankAccounts() {
        return new Konto[]{new Konto("test1", "wdwd", "wdwd")};
    }

    /**
     *
     * @param date
     * @param value
     * @param to
     * @param konto
     * @return boolean:Wurde gespeichert?
     */
    public static boolean saveNewBooking(String date, String value, String to, Konto konto) {
        if (konto != null) {
            Long valueL = null;
            try {
                valueL = Long.parseLong(value);
            } catch (NumberFormatException e) {
                return false;
            }
            konto.addBuchung(new Buchung(valueL, to, date));
            return true;
        }
        return false;
    }

    private Konto getAccountByName(String name) {
        for (Konto konto : this.user.getKonten()) {
            if (konto.getName().equals(name)) {
                return konto;
            }
        }
        return null;
    }

    // ==================================================================================================== \\
    // ======================================= Auswertung ==================================================\\
    public List<Analysis> getAllAnalysisObjects() {
        return this.user.getAuswertungen();
    }

    public static String getHelpText(String name) {
        String helpText = "";
        File file = new File("./src/data/helpTexts/" + name + ".txt");
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                helpText += sc.nextLine() + "\n";
            }
        } catch (Exception ex) {
            return "Keinen passenden Hilfetext gefunden.";
        }

        return helpText;
    }

    /**
     * Ueberprueft ob der User existiert und eingelogt werden kann Setzt auch
     * den USER TODO
     *
     * @param name
     * @param password
     * @return
     */
    public boolean checkPermission(String name, String password) {
        this.user = Verschluesselung.load(name, password);
//        this.user = new User(name, password);
//        ArrayList<Analysis> list = new ArrayList();
//        ArrayList<Kategorie> cat = new ArrayList();
//        cat.add(new Kategorie("Bla", Color.orange));
//        list.add(new Analysis("Test", new Zeitraum(), cat));
//        list.add(new Analysis("Test2", new Zeitraum(), cat));
//        this.user.setAuswertungen(list);       
        return (this.user != null);
    }

    // Methoden fuer die GUI 
    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    public boolean isUserValid() {
        return (this.user != null);

    }
}
