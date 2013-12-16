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
    private Date lDatum = new Date();  // TODO mit Zeitraum Objekt ersetzen ....
    private double lBetrag = 0.0;
    private String lAdressat = "";
    
    //Konstruktor
    public Buchung(double Betrag, String Adressat)
    {
        lBetrag = Betrag;
        lAdressat = Adressat;
    }
    
    //Methoden    
}
