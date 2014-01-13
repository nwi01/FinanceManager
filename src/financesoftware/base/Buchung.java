//Erstellt am 31.10.2013
package financesoftware.base;

import java.awt.Color;
import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mike
 */
public class Buchung implements Serializable {

    //Member
    private Zeitraum lDatum;
    private double lBetrag = 0.0;
    private String lAdressat = "";
    private String lVerwendungszweck;
    private Kategorie kategorie;

    /**
     * Default-Konstruktor
     */
    public Buchung() {
    }

    public Buchung(double Betrag, String Adressat, Zeitraum datum, String Verwendungszweck) {
        lBetrag = Betrag;
        lAdressat = Adressat;
        lDatum = datum;
        lVerwendungszweck = Verwendungszweck;
        this.kategorie = new Kategorie("Import", Color.orange);
    }

    public Buchung(double Betrag, String Adressat, String datum, String Verwendungszweck) {
        lBetrag = Betrag;
        lAdressat = Adressat;
        Calendar startzeit = Zeitraum.parseCalendar(datum);
        lDatum = new Zeitraum(startzeit);
        lVerwendungszweck = Verwendungszweck;
        this.kategorie = new Kategorie("Import", Color.orange);
    }

    public Buchung(double Betrag, String Adressat, Zeitraum datum, String Verwendungszweck, Kategorie kat) {
        lBetrag = Betrag;
        lAdressat = Adressat;
        lDatum = datum;
        lVerwendungszweck = Verwendungszweck;
        this.kategorie = kat;
    }

    public Buchung(double Betrag, String Adressat, String datum, String Verwendungszweck, Kategorie kat) {
        lBetrag = Betrag;
        lAdressat = Adressat;
        Calendar startzeit = Zeitraum.parseCalendar(datum);
        lDatum = new Zeitraum(startzeit);
        lVerwendungszweck = Verwendungszweck;
        this.kategorie = kat;
    }

    public Buchung(double Betrag, String Adressat, Calendar startzeit, String Verwendungszweck, Kategorie kat) {
        lBetrag = Betrag;
        lAdressat = Adressat;
        lDatum = new Zeitraum(startzeit);
        lVerwendungszweck = Verwendungszweck;
        this.kategorie = kat;
    }

    //Methoden    
    /**
     * @return the lDatum
     */
    public Zeitraum getDatum() {
        return lDatum;
    }

    /**
     * @param lDatum the lDatum to set
     */
    public void setDatum(Zeitraum lDatum) {
        this.lDatum = lDatum;
    }

    /**
     * @return the lBetrag
     */
    public double getBetrag() {
        return lBetrag;
    }

    /**
     * @param lBetrag the lBetrag to set
     */
    public void setBetrag(double lBetrag) {
        this.lBetrag = lBetrag;
    }

    /**
     * @return the lAdressat
     */
    public String getAdressat() {
        return lAdressat;
    }

    /**
     * @param lAdressat the lAdressat to set
     */
    public void setAdressat(String lAdressat) {
        this.lAdressat = lAdressat;
    }

    /**
     * 
     * @param uZweck 
     */
    public void setVerwendungszweck(String uZweck) {
        lVerwendungszweck = uZweck;
    }
    
    /**
     * 
     * @return 
     */
    public String getVerwendungszweck() {
        return lVerwendungszweck;
    }
    
    /**
     * @return the kategorie
     */
    public Kategorie getKategorie() {
        return kategorie;
    }

    /**
     * @param kategorie the kategorie to set
     */
    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }
}