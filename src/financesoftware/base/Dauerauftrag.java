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
        super(betrag, adressat, startzeit);  
        Zeitraum uIntervall = new Zeitraum(Zeitraum.parseCalendar(startzeit), intervall, wdh);              
        setDatum(uIntervall);
        this.aktiv = true;
        // buchen
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
        super(betrag, adressat, startzeit);  
        Zeitraum uIntervall = new Zeitraum(Zeitraum.parseCalendar(startzeit), intervall, 
                                           Zeitraum.parseCalendar(endezeit));              
        setDatum(uIntervall);
        this.aktiv = true;
        this.letzteAusfuehrung = this.getDatum().getStartzeit();
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
     * Ueberpruefung, ob Dauerauftrag gebucht werden muss
     * @return boolean: muss gebucht werden?
     */
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
    
    /**
     * bucht die faelligen Buchungen des Dauerauftrags
     */
    public void buchen(){
        boolean buchenPruefen = true;
        Calendar heute = Calendar.getInstance();
        int tage[] = Zeitraum.IntervallInTage(this.getDatum().getIntervall());
        Calendar letzteAusfTmp = this.letzteAusfuehrung;
        
        while(buchenPruefen){        
            if(tage[0] != 0){
                letzteAusfTmp.add(Calendar.DAY_OF_MONTH, tage[0]);
            }
            else{
                letzteAusfTmp.add(Calendar.MONTH, tage[1]);
            }
            
            if(letzteAusfTmp.compareTo(this.getDatum().getEndezeit()) >= 0){
                buchenPruefen = false;
                this.aktiv = false;
            }
            else if(letzteAusfTmp.compareTo(heute) <= 0){
                //Buchen
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
