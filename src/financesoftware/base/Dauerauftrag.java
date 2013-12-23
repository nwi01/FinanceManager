/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package financesoftware.base;

import java.util.Date;

/**
 *
 * @author Mike
 */
class Dauerauftrag extends Buchung {

    //Member
    private Date lStartzeit;
    private Zeitraum lIntervall;

    //Konstruktor
    public Dauerauftrag(double betrag, String adressat, Date uStartzeit, Zeitraum uIntervall) {
        super(betrag, adressat, uStartzeit);        
        setlStartzeit(uStartzeit);
        setlIntervall(uIntervall);
    }

    public boolean mussGebuchtWerden() {
        return false;
    }

    //Getter_Setter    

    /**
     * @return the lStartzeit
     */
    public Date getlStartzeit() {
        return lStartzeit;
    }

    /**
     * @param lStartzeit the lStartzeit to set
     */
    public void setlStartzeit(Date lStartzeit) {
        this.lStartzeit = lStartzeit;
    }

    /**
     * @return the lIntervall
     */
    public Zeitraum getlIntervall() {
        return lIntervall;
    }

    /**
     * @param lIntervall the lIntervall to set
     */
    public void setlIntervall(Zeitraum lIntervall) {
        this.lIntervall = lIntervall;
    }

}
