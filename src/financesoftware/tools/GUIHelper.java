package financesoftware.tools;

import financesoftware.base.Kategorie;
import financesoftware.base.Konto;
import financesoftware.base.User;
import financesoftware.base.Zeitraum;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author nwi01
 */
public class GUIHelper {

    private User user;

    public GUIHelper() {
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
     * Buchung: Enthählt: String[<Buchung>][<Inhalt der Buchung>]
     * Darf nicht null zurueckliefern.
     * @return
     */
    public static String[][] getBookingData(Konto konto) {
        return new String[][]{{"Test", "Bla", "Bla2"},{"Test2", "10.3", "Niels"}};
    }

    /**
     * Wird mit der getBookingData() Methode aufgerufen und gibt ein Array
     * zurueck, welches die Namen "[<Inhalt der Buchung>]" enthaehlt
     *
     * @return
     */
    public static String[] getBookingColumnName(Konto konto) {
         return new String[]{"Datum", "Betrag", "Empfänger"};
    }
    
    public static double getCurrentMoney(){
        return 1.0;
    }
    
    /**
     * Liefert alle Konten des aktuellen Users zurueck
     * @return 
     */
    public static Konto[] getAllBankAccounts(){
        return new Konto[]{new Konto("test1", "wdwd", "wdwd", null, null)};        
    }
    
    /**
     * 
     * @param date
     * @param value
     * @param to
     * @return boolean:Wurde gespeichert?
     */
    public static boolean saveNewBooking(String date, String value, String to){
        return true;
    }
    
    // ==================================================================================================== \\

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
     * Ueberprueft ob der User existiert und eingelogt werden kann TODO
     *
     * @param name
     * @param password
     * @return
     */
    public boolean checkPermission(String name, String password) {
        return true;
    }
}
