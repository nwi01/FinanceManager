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
public class User 
{
    //Member
    private String lName = "";
    private String lPassword = "";
    private List<Konto> lKonten;
    private List<Auswertung> lAuswertungen;
    
    /**
     * Konstruktor
     * @param uName
     * @param uPassword
     * @param uKonten
     * @param uAuswertungen
     */
        public User(String uName, String uPassword, List<Konto> uKonten, List<Auswertung> uAuswertungen)
    {
        lName = uName;
        lPassword = uPassword; 
        lKonten = uKonten;
        lAuswertungen = uAuswertungen;
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
     * @return the Auswertungen
     */
    public List<Auswertung> getAuswertungen() {
        return lAuswertungen;
    }

    /**
     * @param Auswertungen the Auswertungen to set
     */
    public void setAuswertungen(List<Auswertung> Auswertungen) {
        this.lAuswertungen = Auswertungen;
    }
    
}
