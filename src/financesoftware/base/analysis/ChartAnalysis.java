package financesoftware.base.analysis;

import financesoftware.base.Kategorie;
import financesoftware.base.Zeitraum;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author nwi01
 */
public class ChartAnalysis extends Analysis {
    private List<JFreeChart> charts;
    
    public ChartAnalysis(String uName, Zeitraum uZeitraum, List<Kategorie> uKategorien, boolean uIsVergleich){
        super(uName, uZeitraum, uKategorien);
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
}
