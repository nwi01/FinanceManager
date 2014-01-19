/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.tools;

import financesoftware.base.Buchung;
import financesoftware.base.Kategorie;
import financesoftware.base.Konto;
import financesoftware.base.User;
import financesoftware.base.analysis.Analysis;
import financesoftware.base.analysis.ChartAnalysis;
import financesoftware.base.analysis.ChartEnum;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 * TODO Mapping Name <=> Chart
 *
 * @author melanie
 */
public class ChartFactoryMapper {

    private final User user;
    private static ChartFactoryMapper instance;
    private boolean accountCompare = true;

    public ChartFactoryMapper(User user) {
        this.user = user;
    }

    public static ChartFactoryMapper getInstance() {
        if (instance == null) {
            instance = new ChartFactoryMapper(GUIHelper.getInstance().getUser());
        }
        return instance;
    }
    
    private List<Kategorie> getRealList(List<Kategorie> list){
        List<Kategorie> real = new ArrayList(this.user.getKategorien());
        List<Kategorie> realContainer = this.user.getKategorien();
        for(Kategorie kat : list){            
                for(Kategorie kat2 : real){
                    if(kat.getlName().equals(kat2.getlName())){
                        realContainer.add(kat2.copy());
                    }
                }
        }
        return realContainer;
    }

    /**
     *
     * @param ana
     * @param chartNames
     * @return
     */
    public List<JFreeChart> getChartsByAnalysis(ChartAnalysis ana) {
        Konto konto = ana.getKonto();
        List<Kategorie> newList = getRealList(ana.getKategorien());
        HashMap<Kategorie, Double> mappingCategory = konto.getAllParts(newList);

        ArrayList<JFreeChart> charts = new ArrayList();
//        if
        for (ChartEnum chartName : ana.getCharts()) {
            charts.add(this.getChartByName(chartName, mappingCategory));
        }
        return charts;
    }

    private JFreeChart getChartByName(ChartEnum chart, HashMap<Kategorie, Double> mapping) {
        JFreeChart chartInstance = null;
        switch (chart) {
            case PIECHART: {

                DefaultPieDataset dataSet = new DefaultPieDataset();
                for (Kategorie kat : mapping.keySet()) {
                    dataSet.setValue(kat.getlName(), mapping.get(kat));
                }

                chartInstance = org.jfree.chart.ChartFactory.createPieChart3D("", dataSet, true, true, false);
                PiePlot3D plot = (PiePlot3D) chartInstance.getPlot();
                plot.setStartAngle(290);
                plot.setDirection(Rotation.CLOCKWISE);
                plot.setForegroundAlpha(0.5f);

                for (Kategorie kat : mapping.keySet()) {
                    plot.setSectionPaint(kat.getlName(), kat.Farbe());
                    plot.getSectionPaint(kat.getlName());
                }

                return chartInstance;
            }

            case BARCHART: {
                DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
                for (Kategorie kat : mapping.keySet()) {
                    dataSet.addValue(mapping.get(kat), kat.getlName(), "Ausgaben");
                }

                chartInstance = org.jfree.chart.ChartFactory.createBarChart("", "", "", dataSet);
                CategoryPlot plot = (CategoryPlot) chartInstance.getPlot();
                CategoryItemRenderer ren = plot.getRenderer();
//                plot.setStartAngle(290);
//                plot.setDirection(Rotation.CLOCKWISE);
                plot.setForegroundAlpha(0.5f);
                int count = 0;
                for (Kategorie kat : mapping.keySet()) {
//                    plot. (kat.getlName(), kat.Farbe());
                    ren.setSeriesPaint(count, kat.Farbe());
                    count++;
//                    plot.set
                }
                return chartInstance;
            }
        }
        return null;
    }

    public static ArrayList<ChartEnum> getAllAvailableCharts() {
        ArrayList<ChartEnum> charts = new ArrayList();
        ChartEnum[] array = ChartEnum.values();

        for (ChartEnum enumDummy : array) {
            charts.add(enumDummy);
        }
        return charts;
    }

    public static List<String> getMappedCharts(List<JFreeChart> list) {
        ArrayList<String> stringList = new ArrayList();

        for (JFreeChart chart : list) {
            stringList.add(ChartFactoryMapper.getStringFromChart(chart));
        }
        return stringList;
    }

    public static String getStringFromChart(JFreeChart chart) {
        return "";
    }

    /**
     * @return the accountCompare
     */
    public boolean isAccountCompare() {
        return accountCompare;
    }

    /**
     * @param accountCompare the accountCompare to set
     */
    public void setAccountCompare(boolean accountCompare) {
        this.accountCompare = accountCompare;
    }
}
