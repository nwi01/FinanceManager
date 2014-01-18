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
public class ChartAnalysis extends Analysis {
    private List<ChartEnum> charts = new ArrayList();
    private Konto konto;
    
    public ChartAnalysis(){
        
    }
    public ChartAnalysis(String uName, Zeitraum uZeitraum, List<Kategorie> uKategorien, boolean uIsVergleich, Konto konto){
        super(uName, uZeitraum, uKategorien);
        this.konto = konto;
    }

    /**
     * @return the charts
     */
    public List<ChartEnum> getCharts() {
        return charts;
    }

    /**
     * @param charts the charts to set
     */
    public void setCharts(List<ChartEnum> charts) {
        this.charts = charts;
    }       

    /**
     * @return the konto
     */
    public Konto getKonto() {
        return konto;
    }

    /**
     * @param konto the konto to set
     */
    public void setKonto(Konto konto) {
        this.konto = konto;
    }
}
