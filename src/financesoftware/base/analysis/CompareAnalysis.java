package financesoftware.base.analysis;

import financesoftware.base.Kategorie;
import financesoftware.base.Konto;
import financesoftware.base.Zeitraum;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author nwi01
 */
@XmlSeeAlso({Analysis.class})
public class CompareAnalysis extends Analysis {
    private List<Konto> accounts = new ArrayList();  // Konten
    
    public CompareAnalysis(){
        
    }
    public CompareAnalysis(String uName, Zeitraum uZeitraum, List<Kategorie> uKategorien, boolean uIsVergleich, Konto kon1, Konto kon2){
        super(uName, uZeitraum, uKategorien);
        this.accounts.add(kon1);
        this.accounts.add(kon2);
    }

    /**
     * @return the accounts
     */
    public List<Konto> getAccounts() {
        return accounts;
    }

    /**
     * @param accounts the accounts to set
     */
    public void setAccounts(List<Konto> accounts) {
        this.accounts = accounts;
    }
}
