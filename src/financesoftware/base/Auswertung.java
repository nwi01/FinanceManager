/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package financesoftware.base;

import java.awt.Color;

/**
 *
 * @Verant Niels
 */
public class Auswertung 
{
    //Member
     private String lName = "";
     private Zeitraum lZeitraum;
     private Kategorie lKategorie;
     private boolean istVergleich = false;

     //Konstruktor
     public Auswertung()
     {
     
     }
     
     
     
     
     //Getter/Setter
    /**
     * @return the lName
     */
    public String getlName() {
        return lName;
    }

    /**
     * @param lName the lName to set
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * @return the lZeitraum
     */
    public Zeitraum getlZeitraum() {
        return lZeitraum;
    }

    /**
     * @param lZeitraum the lZeitraum to set
     */
    public void setlZeitraum(Zeitraum lZeitraum) {
        this.lZeitraum = lZeitraum;
    }

    /**
     * @return the lKategorie
     */
    public Kategorie getlKategorie() {
        return lKategorie;
    }

    /**
     * @param lKategorie the lKategorie to set
     */
    public void setlKategorie(Kategorie lKategorie) {
        this.lKategorie = lKategorie;
    }

    /**
     * @return the istVergleich
     */
    public boolean isIstVergleich() {
        return istVergleich;
    }

    /**
     * @param istVergleich the istVergleich to set
     */
    public void setIstVergleich(boolean istVergleich) {
        this.istVergleich = istVergleich;
    }
     
     
     
}
