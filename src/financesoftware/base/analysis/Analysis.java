/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package financesoftware.base.analysis;

import financesoftware.base.Kategorie;
import financesoftware.base.Konto;
import financesoftware.base.Zeitraum;
import financesoftware.tools.GUIHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Niels
 */
public abstract class Analysis implements Serializable
{
    //Member
    private String name;
    private Zeitraum zeitraum;
    private List<Kategorie> categories;    
    
    /**
     * Konstruktor
     * @param uName
     * @param uZeitraum
     * @param uKategorien
     * @param uIsVergleich
     */
    public Analysis(String uName, Zeitraum uZeitraum, List<Kategorie> uKategorien) 
    {
        name = uName;
        zeitraum = uZeitraum;
        categories = uKategorien;       
    }
    
    //Getter_Setter

    /**
     * @return the lName
     */
    public String getName() {
        return name;
    }

    /**
     * @param lName the lName to set
     */
    public void setName(String lName) {
        this.name = lName;
    }

    /**
     * @return the lZeitraum
     */
    public Zeitraum getZeitraum() {
        return zeitraum;
    }

    /**
     * @param lZeitraum the lZeitraum to set
     */
    public void setZeitraum(Zeitraum lZeitraum) {
        this.zeitraum = lZeitraum;
    }

    /**
     * @return the lKategorien
     */
    public List<Kategorie> getKategorien() {
        return categories;
    }

    /**
     * @param lKategorien the lKategorien to set
     */
    public void setKategorien(List<Kategorie> lKategorien) {
        this.categories = lKategorien;
    }
    
    @Override
    public String toString(){
       return this.name; 
    }
}
