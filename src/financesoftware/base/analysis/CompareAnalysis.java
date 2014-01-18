package financesoftware.base.analysis;

import financesoftware.base.Kategorie;
import financesoftware.base.Konto;
import financesoftware.base.Zeitraum;
import java.util.List;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author nwi01
 */
@XmlSeeAlso({Analysis.class})
public class CompareAnalysis extends Analysis {
    private List<Konto> accounts;  // Konten
    
    public CompareAnalysis(){
        
    }
    public CompareAnalysis(String uName, Zeitraum uZeitraum, List<Kategorie> uKategorien, boolean uIsVergleich){
        super(uName, uZeitraum, uKategorien);
    }
}
