package financesoftware.base;

import java.util.Calendar;

/**
 *
 * @author Mike
 */
public class Dauerauftrag extends Buchung {

    //Member
    Calendar letzteAusfuehrung;
    boolean aktiv; 
    
    
    /**
     * Konstruktor, wenn Anzahl Wdh angegeben
     * @param betrag
     * @param adressat
     * @param startzeit
     * @param intervall
     * @param wdh 
     */
    public Dauerauftrag(double betrag, String adressat, String startzeit, 
                        Zeitraum.Intervall intervall, int wdh) {
        super(betrag, adressat, startzeit, "");  
        Zeitraum uIntervall = new Zeitraum(Zeitraum.parseCalendar(startzeit), intervall, wdh);              
        setDatum(uIntervall);
        this.aktiv = true;
        this.letzteAusfuehrung = this.getDatum().getStartzeit();
    }
    
    /**
     * Konstruktor, wenn Endezeit angegeben
     * @param betrag
     * @param adressat
     * @param startzeit
     * @param intervall
     * @param endezeit 
     */
    public Dauerauftrag(double betrag, String adressat, String startzeit, 
                        Zeitraum.Intervall intervall, String endezeit) {
        super(betrag, adressat, startzeit, "");  
        Zeitraum uIntervall = new Zeitraum(Zeitraum.parseCalendar(startzeit), intervall, 
                                           Zeitraum.parseCalendar(endezeit));              
        setDatum(uIntervall);
        this.aktiv = true;
        this.letzteAusfuehrung = this.getDatum().getStartzeit();
    }

    
    public boolean mussGebuchtWerden() {
        boolean mussGebuchtWerden = false;
        Calendar heute = Calendar.getInstance();
        int tage[] = Zeitraum.IntervallInTage(this.getDatum().getIntervall());
        
        if(tage[0] != 0){
            this.letzteAusfuehrung.add(Calendar.DAY_OF_MONTH, tage[0]);
        }
        else{
            this.letzteAusfuehrung.add(Calendar.MONTH, tage[1]);
        }
        
        if(this.letzteAusfuehrung.compareTo(heute) <= 0){
           mussGebuchtWerden = true; 
        }
        
        return mussGebuchtWerden;
    }
    
    @Override
    public String toString(){
        return this.getAdressat() + ": " + this.getBetrag();
    }
}
