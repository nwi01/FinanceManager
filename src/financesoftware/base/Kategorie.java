/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package financesoftware.base;

import java.awt.Color;

/**
 *
 * @author Mike
 */
class Kategorie 
{
    //Member
    private String lName;
    private Color lFarbe;
    
    //Konstruktor
    public Kategorie(String uName, Color uFarbe)
    {
        lName = uName;
        lFarbe = uFarbe;
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
    public Color getlFarbe() {
        return lFarbe;
    }

    /**
     * @param lFarbe the lFarbe to set
     */
    public void setlFarbe(Color lFarbe) {
        this.lFarbe = lFarbe;
    }
}
