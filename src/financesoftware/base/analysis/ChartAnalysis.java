package financesoftware.base.analysis;

import financesoftware.base.Kategorie;
import financesoftware.base.Konto;
import financesoftware.base.Zeitraum;
import java.util.List;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author nwi01
 */
public class ChartAnalysis extends Analysis {
    private List<JFreeChart> charts;
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
    public List<JFreeChart> getCharts() {
        return charts;
    }

    /**
     * @param charts the charts to set
     */
    public void setCharts(List<JFreeChart> charts) {
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
