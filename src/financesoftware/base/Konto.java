/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package financesoftware.base;

import java.util.List;

/**
 *
 * @author Mike
 */
public class Konto 
{
    //Member
    private String lName = "";
    private String KontoNr = "";
    private String lBLZ = "";
    private List<Buchung> lBuchungen;
    private List<Dauerauftrag> lDauerauftraege;
    
    /**
     * Konstruktor
     * @param uName
     * @param uKontoNr
     * @param uBLZ
     * @param uBuchungen
     * @param Dauerauftraege
     */
    public Konto(String uName, String uKontoNr, String uBLZ, List<Buchung> uBuchungen, List<Dauerauftrag> uDauerauftraege)
    {
        lName = uName;
        lBLZ = uBLZ;
        lBuchungen = uBuchungen;
        lDauerauftraege = uDauerauftraege;
        KontoNr = uKontoNr;
    }
    
    //Methoden
    /**
     *
     * @return Kontostand
     */
        public double getAktuellerKontostand()
    {
        return 0.0;
    }
    
    
    //Getter_Setter
    /**
     * @return the Name
     */
    public String getName() {
        return lName;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.lName = Name;
    }

    /**
     * @return the KontoNr
     */
    public String getKontoNr() {
        return KontoNr;
    }

    /**
     * @param KontoNr the KontoNr to set
     */
    public void setKontoNr(String KontoNr) {
        this.KontoNr = KontoNr;
    }

    /**
     * @return the BLZ
     */
    public String getBLZ() {
        return lBLZ;
    }

    /**
     * @param BLZ the BLZ to set
     */
    public void setBLZ(String BLZ) {
        this.lBLZ = BLZ;
    }

    /**
     * @return the Buchungen
     */
    public List<Buchung> getBuchungen() {
        return lBuchungen;
    }

    /**
     * @param Buchungen the Buchungen to set
     */
    public void setBuchungen(List<Buchung> Buchungen) {
        this.lBuchungen = Buchungen;
    }

    /**
     * @return the Dauerauftraege
     */
    public List<Dauerauftrag> getDauerauftraege() {
        return lDauerauftraege;
    }

    /**
     * @param Dauerauftraege the Dauerauftraege to set
     */
    public void setDauerauftraege(List<Dauerauftrag> Dauerauftraege) {
        this.lDauerauftraege = Dauerauftraege;
    }
    
    
    /**
     * TODO: Ordentliche Ausgabe! Wird wirklich benoetigt ;)
     * @return 
     */
    public String toString(){
        return this.lName + ":" + this.KontoNr;
        
    }     
}
