//Erstellt am 31.10.2013
package financesoftware.base;

import java.util.Date;

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
    
    //Konstruktor
    public Buchung(double Betrag, String Adressat, Zeitraum datum)
    {
        lBetrag = Betrag;
        lAdressat = Adressat;
        lDatum = datum;
    }
    
     public Buchung(double Betrag, String Adressat, String datum)
    {
        lBetrag = Betrag;
        lAdressat = Adressat;
        lDatum = Zeitraum.parseDate(datum);
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
}
