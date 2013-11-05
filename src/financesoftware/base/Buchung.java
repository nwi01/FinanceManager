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
    Date lDatum = new Date();
    double lBetrag = 0.0;
    String lAdressat = "";
    
    //Konstruktor
    public Buchung(double Betrag, String Adressat)
    {
        lBetrag = Betrag;
        lAdressat = Adressat;
    }
    
    //Methoden
    
}
