package financesoftware.base;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Mike
 */
public class Dauerauftrag extends Buchung implements Serializable{

    //Member
    Calendar letzteAusfuehrung;
    boolean aktiv; 
    
    /**
     * Default-Konstruktor
     */
    public Dauerauftrag()
    {}
    
    /**
     * Konstruktor, wenn Anzahl Wdh angegeben
     * nach Aufruf muss buchen() aufgerufen werden
     * @param betrag
     * @param adressat
     * @param startzeit
     * @param intervall
     * @param wdh 
     * @param verwendung
     * @param kat
     */    
    public Dauerauftrag(double betrag, String adressat, String startzeit, 
                        Zeitraum.Intervall intervall, int wdh, String verwendung, Kategorie kat) {
        super(betrag, adressat, startzeit, verwendung, kat);  
        Zeitraum uIntervall = new Zeitraum(Zeitraum.parseCalendar(startzeit), intervall, wdh);              
        setDatum(uIntervall);
        this.aktiv = false;
        this.letzteAusfuehrung = null;
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
                        Zeitraum.Intervall intervall, String endezeit, String verwendung, Kategorie kat) {
        super(betrag, adressat, startzeit, verwendung, kat);  
        Zeitraum uIntervall = new Zeitraum(Zeitraum.parseCalendar(startzeit), intervall, 
                                           Zeitraum.parseCalendar(endezeit));              
        setDatum(uIntervall);
        this.aktiv = false;
        this.letzteAusfuehrung = null;
    }
    
    // Getter & Setter
    
    /**
     * @return aktiv
     */
    public boolean getAktiv(){
        return this.aktiv;
    }
    
    /**
     * @param aktiv the aktiv to set
     */
    public void setAktiv(boolean aktiv){
        this.aktiv = aktiv;
    }
    
    /**
     * @return letzteAusfuehrung
     */
    public Calendar getLetzteAusfuehrung(){
        return this.letzteAusfuehrung;
    }
    
    /**
     * @param letzteAusfuehrung the letzteAusfuehrung to set
     */
    public void setLetzteAusfuehrung(Calendar letzteAusfuehrung){
        this.letzteAusfuehrung = letzteAusfuehrung;
    }
    
    // Getter & Setter Ende
    
    @Override
    public String toString(){
        return this.getAdressat() + ": " + this.getBetrag();
    }
}
