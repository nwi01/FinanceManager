package financesoftware.base;

/**
 *
 * @author Mike
 */
class Dauerauftrag extends Buchung {

    //Member
    private Zeitraum lIntervall;

    //Konstruktor
    public Dauerauftrag(double betrag, String adressat,  Zeitraum uIntervall) {
        super(betrag, adressat, uIntervall);        
        setlIntervall(uIntervall);
    }

    public boolean mussGebuchtWerden() {
        return false;
    }

    //Getter_Setter    

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
