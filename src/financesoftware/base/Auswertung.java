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
public class Auswertung 
{
    //Member
    private String lName;
    private Zeitraum lZeitraum;
    private List<Kategorie> lKategorien;
    private boolean isVergleich;
    
    /**
     * Konstruktor
     * @param uName
     * @param uZeitraum
     * @param uKategorien
     * @param uIsVergleich
     */
    public Auswertung(String uName, Zeitraum uZeitraum, List<Kategorie> uKategorien, boolean uIsVergleich) 
    {
        lName = uName;
        lZeitraum = uZeitraum;
        lKategorien = uKategorien;
        isVergleich = uIsVergleich;        
    }
    
    //Getter_Setter

    /**
     * @return the lName
     */
    public String getName() {
        return lName;
    }

    /**
     * @param lName the lName to set
     */
    public void setName(String lName) {
        this.lName = lName;
    }

    /**
     * @return the lZeitraum
     */
    public Zeitraum getZeitraum() {
        return lZeitraum;
    }

    /**
     * @param lZeitraum the lZeitraum to set
     */
    public void setZeitraum(Zeitraum lZeitraum) {
        this.lZeitraum = lZeitraum;
    }

    /**
     * @return the lKategorien
     */
    public List<Kategorie> getKategorien() {
        return lKategorien;
    }

    /**
     * @param lKategorien the lKategorien to set
     */
    public void setKategorien(List<Kategorie> lKategorien) {
        this.lKategorien = lKategorien;
    }

    /**
     * @return the isVergleich
     */
    public boolean IsVergleich() {
        return isVergleich;
    }

    /**
     * @param isVergleich the isVergleich to set
     */
    public void setIsVergleich(boolean isVergleich) {
        this.isVergleich = isVergleich;
    }
}
