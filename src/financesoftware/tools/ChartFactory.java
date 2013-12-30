/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.tools;

import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.JFreeChart;

/**
 * TODO Mapping Name <=> Chart
 * @author melanie
 */
public class ChartFactory {

    
    public static ArrayList<String> getAllAvailableCharts() {
        ArrayList<String> charts = new ArrayList();
        charts.add("Kreisdiagramm");
        charts.add("Balkendiagramm1");
        charts.add("Kreisdiagramm2");
        charts.add("Kreisdiagramm3");
        charts.add("Kreisdiagramm4");
        charts.add("Kreisdiagramm5");
        charts.add("Kreisdiagramm6");
        charts.add("Kreisdiagramm7");
        charts.add("Kreisdiagramm8");
        return charts;
    }
    
    public static List<String> getMappedCharts(List<JFreeChart> list){
        ArrayList<String> stringList = new ArrayList();
        
        for(JFreeChart chart : list){
            stringList.add(ChartFactory.getStringFromChart(chart));
        }
        return stringList;
    }
    
    public static String getStringFromChart(JFreeChart chart){
        return "";
    }
}
