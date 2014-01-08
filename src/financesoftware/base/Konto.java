/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mike
 */
public class Konto implements Serializable {

    //Member
    private String lName = "";
    private String KontoNr = "";
    private double lKontostand;
    private String lBLZ = "";
    private List<Buchung> lBuchungen = new ArrayList();
    private List<Dauerauftrag> lDauerauftraege = new ArrayList();
    private String iban;
    private String bic;
    private boolean isOldStyle = true;

    /**
     * Default-Konstruktor
     */
    public Konto()
    {}
    
    /**
     * Konstruktor
     *
     * @param uName
     * @param uKontoNr
     * @param uBLZ
     * @param Kontostand
     * @param uBuchungen
     * @param uDauerauftraege
     */
    public Konto(String uName, String uKontoNr, String uBLZ, double Kontostand, List<Buchung> uBuchungen, List<Dauerauftrag> uDauerauftraege) {
        lName = uName;
        lBLZ = uBLZ;
        lKontostand = Kontostand;
        lBuchungen = uBuchungen;
        lDauerauftraege = uDauerauftraege;
        KontoNr = uKontoNr;
    }

    /**
     * 
     * @param uName
     * @param Kontostand
     * @param iban
     * @param bic 
     */
    public Konto(String uName, double Kontostand, String iban, String bic) {
        lName = uName;
        this.iban = iban;
        this.bic = bic;
        lKontostand = Kontostand;
        this.isOldStyle = false;
    }

    /**
     *
     * @param uName
     * @param uKontoNr
     * @param uBLZ
     * @param Kontostand
     */
    public Konto(String uName, String uKontoNr, String uBLZ, double Kontostand) {
        lName = uName;
        lBLZ = uBLZ;
        KontoNr = uKontoNr;
        lKontostand = Kontostand;
    }

    //Methoden
    /**
     * TODO
     *
     * @return Kontostand
     */
    public double getAktuellerKontostand() {
        return lKontostand;
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

    public void addBuchung(Buchung buch) {
        lKontostand += buch.getBetrag();
        this.lBuchungen.add(buch);
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

    public void addDauerauftrag(Dauerauftrag dau) {
        this.lDauerauftraege.add(dau);
    }

    /**
     * TODO: Ordentliche Ausgabe! Wird wirklich benoetigt ;)
     *
     * @return
     */
    public String toString() {
        return this.lName + ":" + this.KontoNr;

    }
    
    /**
     * Neues oder altes Verfahren?
     * @return 
     */
    public boolean isOldStyle(){
        return this.isOldStyle;
    }
}
