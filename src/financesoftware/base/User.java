/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package financesoftware.base;

import financesoftware.base.analysis.Analysis;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mike
 */
@XmlRootElement
public class User implements Serializable
{
    //Member
    private String lName = "";
    private String lPassword = "";
    private List<Konto> lKonten = new ArrayList();
    private List<Analysis> lAuswertungen = new ArrayList();
    private List<Kategorie> kategorien = new ArrayList();
    
    /**
     * Default-Konstruktor
     */
    public User()
    {}
    
    /**
     * Konstruktor
     * @param uName
     * @param uPassword
     * @param uKonten
     * @param uAuswertungen
     */
     public User(String uName, String uPassword, List<Konto> uKonten, List<Analysis> uAuswertungen)
     {
        lName = uName;
        lPassword = uPassword; 
        lKonten = uKonten;
        lAuswertungen = uAuswertungen;
        this.createDefaultCategories();        
    }
     
     public User(String uName, String uPassword)
     {
        lName = uName;
        lPassword = uPassword;
        this.createDefaultCategories();
    }
     
     /**
      * Standard Kategorien
      * TODO: Sollte aktiviert werden, wenn die verschluesselung klappt
      */
     private void createDefaultCategories(){
//         this.kategorien.add(new Kategorie("Essen", Color.ORANGE));
//         this.kategorien.add(new Kategorie("Auto", Color.blue));
//         this.kategorien.add(new Kategorie("Elektronik", Color.gray));
//         this.kategorien.add(new Kategorie("Pflanzen", Color.green));
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
     * @return the Password
     */
    public String getPassword() {
        return lPassword;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.lPassword = Password;
    }

    /**
     * @return the Konten
     */
    public List<Konto> getKonten() {
        return lKonten;
    }

    /**
     * @param Konten the Konten to set
     */
    public void setKonten(List<Konto> Konten) {
        this.lKonten = Konten;
    }
    
    /**
     * @return the Analysisen
     */
    public List<Analysis> getAuswertungen() {
        return lAuswertungen;
    }

    /**
     * @param Auswertungen the Analysisen to set
     */
    public void setAuswertungen(List<Analysis> Auswertungen) {
        this.lAuswertungen = Auswertungen;
    }

    /**
     * @return the kategorien
     */
    public List<Kategorie> getKategorien() {
        return kategorien;
    }

     public ArrayList<String> getKategorienString() {
         ArrayList<String> list = new ArrayList();
         for(Kategorie kat : this.kategorien){
             list.add(kat.toString());
         }
        return list;
    }
     
     public void addKategorie(Kategorie kat){
         this.kategorien.add(kat);
     }
    /**
     * @param kategorien the kategorien to set
     */
    public void setKategorien(List<Kategorie> kategorien) {
        this.kategorien = kategorien;
    }
    
    public void addKonto(Konto konto){
        this.lKonten.add(konto);
    }
    
        public void addAuswertung(Analysis ana){
        this.lAuswertungen.add(ana);
    }
}
