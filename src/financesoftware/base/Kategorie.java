/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.base;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mike
 */
public class Kategorie implements Serializable {

    //Member
    private String lName;
    private int lAlpha;
    private int lRed;
    private int lBlue;
    private int lGreen;

    //Konstruktor
    /**
     * Default-Konstruktor
     */
    public Kategorie() {
    }

    public Kategorie(Kategorie kat) {
        lName = kat.getlName();
        lAlpha = kat.getlAlpha();
        lRed = kat.getlRed();
        lBlue = kat.getlBlue();
        lGreen = kat.getlGreen();
    }

    public Kategorie(String uName, Color uFarbe) {
        lName = uName;
        lAlpha = uFarbe.getAlpha();
        lRed = uFarbe.getRed();
        lBlue = uFarbe.getBlue();
        lGreen = uFarbe.getGreen();
    }
   
    //Getter_Setter
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
     * @return the lFarbe
     */
    public Color Farbe() {
        return new Color(getlRed(), getlGreen(), getlBlue(), getlAlpha());
    }

    /**
     * @param lFarbe the lFarbe to set
     */
    public void setFarbe(Color uFarbe) {
        setlAlpha(uFarbe.getAlpha());
        setlRed(uFarbe.getRed());
        setlBlue(uFarbe.getBlue());
        setlGreen(uFarbe.getGreen());
    }

    @Override
    public String toString() {
        return this.lName;
    }

    /**
     * @return the lAlpha
     */
    public int getlAlpha() {
        return lAlpha;
    }

    /**
     * @param lAlpha the lAlpha to set
     */
    public void setlAlpha(int lAlpha) {
        this.lAlpha = lAlpha;
    }

    /**
     * @return the lRed
     */
    public int getlRed() {
        return lRed;
    }

    /**
     * @param lRed the lRed to set
     */
    public void setlRed(int lRed) {
        this.lRed = lRed;
    }

    /**
     * @return the lBlue
     */
    public int getlBlue() {
        return lBlue;
    }

    /**
     * @param lBlue the lBlue to set
     */
    public void setlBlue(int lBlue) {
        this.lBlue = lBlue;
    }

    /**
     * @return the lGreen
     */
    public int getlGreen() {
        return lGreen;
    }

    /**
     * @param lGreen the lGreen to set
     */
    public void setlGreen(int lGreen) {
        this.lGreen = lGreen;
    }
}
