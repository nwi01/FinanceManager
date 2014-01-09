//Erstellt am 31.10.2013
package financesoftware.base;

import java.util.Calendar;

/**
 *
 * @author Mike
 */
public class Buchung 
{
    //Member
    private Zeitraum lDatum;  // TODO mit Zeitraum Objekt ersetzen ....
    private double lBetrag = 0.0;
    private String lAdressat = "";
    private String lVerwendungszweck;
    
    /**
     * Default-Konstruktor
     */
    public Buchung()
    {}
    
    public Buchung(double Betrag, String Adressat, Zeitraum datum, String Verwendungszweck)
    {
        lBetrag = Betrag;
        lAdressat = Adressat;
        lDatum = datum;
        lVerwendungszweck = Verwendungszweck;
    }
    
     public Buchung(double Betrag, String Adressat, String datum,  String Verwendungszweck)
    {
        lBetrag = Betrag;
        lAdressat = Adressat;
        Calendar startzeit = Zeitraum.parseCalendar(datum);
        lDatum = new Zeitraum(startzeit);
        lVerwendungszweck = Verwendungszweck;
    }
     
     public Buchung(double Betrag, String Adressat, Calendar startzeit,  String Verwendungszweck)
    {
        lBetrag = Betrag;
        lAdressat = Adressat;
        lDatum = new Zeitraum(startzeit);
        lVerwendungszweck = Verwendungszweck;
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
    
    public String getVerwendungszweck(){
        return lVerwendungszweck;
    }
}
