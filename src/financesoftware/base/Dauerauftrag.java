package financesoftware.base;

/**
 *
 * @author Mike
 */
public class Dauerauftrag extends Buchung {

    //Member

    //Konstruktor
    public Dauerauftrag(double betrag, String adressat, String startzeit, 
                        Zeitraum.Intervall intervall, int wdh) {
        super(betrag, adressat, startzeit);  
        Zeitraum uIntervall = new Zeitraum(Zeitraum.parseCalendar(startzeit), intervall, wdh);              
        setDatum(uIntervall);
    }
    
    public Dauerauftrag(double betrag, String adressat, String startzeit, 
                        Zeitraum.Intervall intervall, String endezeit) {
        super(betrag, adressat, startzeit);  
        Zeitraum uIntervall = new Zeitraum(Zeitraum.parseCalendar(startzeit), intervall, 
                                           Zeitraum.parseCalendar(endezeit));              
        setDatum(uIntervall);
    }

    public boolean mussGebuchtWerden() {
        return false;
    }
}
