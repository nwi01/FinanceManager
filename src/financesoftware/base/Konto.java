/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
    public Konto() {
    }

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

    public void setBuchungen(List<Buchung> uBuchungen) {
        lBuchungen = uBuchungen;
    }

    public void addBuchung(Buchung buch) {
        setlKontostand(lKontostand + buch.getBetrag());
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
     *
     * @return
     */
    public boolean isOldStyle() {
        return this.isOldStyle;
    }

    /**
     * Alle Betraege aller Buchungen werden addiert
     *
     * @return
     */
    public Double getAllOutgoings() {
        double allOutgoings = 0.;

        for (Buchung buchung : this.getBuchungen()) {
            allOutgoings += buchung.getBetrag();
        }
        return allOutgoings;
    }

    /**
     * Gibt eine HashMap mit einer Kategorie als Schlüssel und einem Double
     * (Anteil) zurueck.
     *
     * @param kategorien
     * @param konto
     * @return
     */
    public HashMap<Kategorie, Double> getAllParts(List<Kategorie> kategorien) {
        List<Buchung> buchungen = this.getBuchungen();
        double allOutgoings = this.getAllOutgoings();

        HashMap<Kategorie, Double> mapping = new HashMap();
        if (kategorien != null) {
            for (Kategorie kategorie : kategorien) {
                mapping.put(kategorie, getPart(kategorie));
            }
        }
        return mapping;
    }

    public double getPart(Kategorie kategorie) {
        double allOutgoings = this.getAllOutgoings();

        double part = 0;
        for (Buchung buchung : this.getBuchungen()) {
            if (buchung.getKategorie().getlName().equals(kategorie.getlName())) {
                part += buchung.getBetrag();
            }
        }
        return part / allOutgoings;
    }

    public void buchen(Dauerauftrag dauerauftrag) {
        boolean buchenPruefen = true;
        Calendar heute = Calendar.getInstance();
        int tage[] = Zeitraum.IntervallInTage(dauerauftrag.getDatum().getIntervall());

        if (dauerauftrag.letzteAusfuehrung == null) {
            if (heute.compareTo(dauerauftrag.getDatum().getStartzeit()) > 0) {
                Buchung neu = new Buchung(dauerauftrag.getBetrag(), dauerauftrag.getAdressat(),
                        dauerauftrag.getDatum().getStartzeit(),
                        dauerauftrag.getVerwendungszweck(), dauerauftrag.getKategorie());
                this.addBuchung(neu);
                Calendar tmp = Calendar.getInstance();
                tmp.setTime(dauerauftrag.getDatum().getStartzeit().getTime());
                dauerauftrag.setLetzteAusfuehrung(tmp);
                buchenPruefen = true;
                dauerauftrag.aktiv = true;
            } else {
                buchenPruefen = false;
            }
        }
        Calendar letzteAusfTmp = Calendar.getInstance();
        if (buchenPruefen) {
            letzteAusfTmp.setTime(dauerauftrag.getLetzteAusfuehrung().getTime());
        }
        while (buchenPruefen) {
            if (tage[0] != 0) {
                letzteAusfTmp.add(Calendar.DAY_OF_MONTH, tage[0]);
            } else {
                letzteAusfTmp.add(Calendar.MONTH, tage[1]);
            }

            if (letzteAusfTmp.compareTo(dauerauftrag.getDatum().getEndezeit()) > 0) {
                buchenPruefen = false;
                dauerauftrag.aktiv = false;
            } else if (letzteAusfTmp.compareTo(heute) <= 0) {
                Calendar tmp2 = Calendar.getInstance();
                tmp2.setTime(letzteAusfTmp.getTime());
                Buchung neu = new Buchung(dauerauftrag.getBetrag(), dauerauftrag.getAdressat(),
                        tmp2, dauerauftrag.getVerwendungszweck(), dauerauftrag.getKategorie());
                this.addBuchung(neu);
                Calendar tmp1 = Calendar.getInstance();
                tmp1.setTime(letzteAusfTmp.getTime());
                dauerauftrag.setLetzteAusfuehrung(tmp1);
                buchenPruefen = true;
            } else {
                buchenPruefen = false;
            }

        }
    }

    /**
     * @return the iban
     */
    public String getIban() {
        return iban;
    }

    /**
     * @param iban the iban to set
     */
    public void setIban(String iban) {
        this.iban = iban;
    }

    /**
     * @return the bic
     */
    public String getBic() {
        return bic;
    }

    /**
     * @param bic the bic to set
     */
    public void setBic(String bic) {
        this.bic = bic;
    }

    /**
     * @param lKontostand the lKontostand to set
     */
    public void setlKontostand(double lKontostand) {
        this.lKontostand = lKontostand;
    }
    
    /**
     * 
     * @return 
     */
    public double getlKontostand()
    {
        return lKontostand;
    }
}
