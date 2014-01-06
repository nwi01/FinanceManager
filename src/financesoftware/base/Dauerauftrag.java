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
     * nach Aufruf muss buchen() aufgerufen werden
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
                        Zeitraum.Intervall intervall, String endezeit) {
        super(betrag, adressat, startzeit, "");  
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
    
    
    /**
     * bucht die faelligen Buchungen des Dauerauftrags
     */
    public void buchen(Konto konto){
        boolean buchenPruefen = true;
        Calendar heute = Calendar.getInstance();        
        int tage[] = Zeitraum.IntervallInTage(this.getDatum().getIntervall());
        
        if(this.letzteAusfuehrung == null){
            if(heute.compareTo(this.getDatum().getStartzeit()) > 0){
                Buchung neu = new Buchung(this.getBetrag(),this.getAdressat(),
                                          this.getDatum().getStartzeit(), "verwendungszweck");
                konto.addBuchung(neu);
                this.letzteAusfuehrung = this.getDatum().getStartzeit();
                buchenPruefen = true;
                this.aktiv = true;
            }
            else{
                buchenPruefen = false;
            }
        }              
        
        Calendar letzteAusfTmp = this.letzteAusfuehrung;
        while(buchenPruefen){        
            if(tage[0] != 0){
                letzteAusfTmp.add(Calendar.DAY_OF_MONTH, tage[0]);
            }
            else{
                letzteAusfTmp.add(Calendar.MONTH, tage[1]);
            }
            
            if(letzteAusfTmp.compareTo(this.getDatum().getEndezeit()) > 0){
                buchenPruefen = false;
                this.aktiv = false;
            }
            else if(letzteAusfTmp.compareTo(heute) <= 0){
                Buchung neu = new Buchung(this.getBetrag(),this.getAdressat(),
                                          this.getDatum().getStartzeit(), "verwendungszweck");
                konto.addBuchung(neu);
                this.letzteAusfuehrung = letzteAusfTmp;
                buchenPruefen = true; 
            }
            else{
                buchenPruefen = false;
            }
               
        }     
    }
    
    @Override
    public String toString(){
        return this.getAdressat() + ": " + this.getBetrag();
    }
}
