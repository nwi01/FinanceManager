/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.components;

import financesoftware.base.Konto;
import financesoftware.base.User;
import financesoftware.base.analysis.Analysis;
import financesoftware.base.analysis.ChartAnalysis;
import financesoftware.gui.base.ViewComponent;
import financesoftware.tools.ChartFactoryMapper;
import financesoftware.tools.GUIHelper;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author melanie
 */
public class AnalysisComponent extends BaseComponent implements ViewComponent, ActionListener {

    private String chartTitle;
    private JComboBox analysisBox;
    private User user = GUIHelper.getInstance().getUser();
    private JPanel analysisPanel = new JPanel();
    private JDesktopPane desktopPane = new JDesktopPane();
    private JPanel comparePanel = new JPanel();

    public AnalysisComponent() {
        super(true);
        initFields();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        desktopPane.setPreferredSize(dim);
        this.mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.anchor = GridBagConstraints.CENTER;
        cons.insets = new Insets(5, 5, 5, 500);

        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridx = 0;
        cons.gridwidth = 1;
        JLabel label = new JLabel("Auswertung:");
        label.setFont(new Font("Dialog", Font.PLAIN, 18));
        this.mainPanel.add(label, cons);

        cons.gridx = 3;
        cons.gridwidth = 5;
        this.mainPanel.add(this.analysisBox, cons);

        cons.gridy++;
        cons.gridx = 0;
        cons.gridwidth = 8;
        JSeparator sep3 = new JSeparator(JSeparator.HORIZONTAL);
        sep3.setPreferredSize(new Dimension(700, 10));
        this.mainPanel.add(sep3, cons);

        cons.gridx = 0;
        cons.gridy++;
        cons.gridwidth = 10;
        cons.gridheight = 10;
        this.mainPanel.add(analysisPanel, cons);

        this.createTestIFrames1(desktopPane);
        this.createTestIFrames2(desktopPane);

        this.setVisible(true);
    }

    private void initFields() {
        this.analysisBox = new JComboBox(this.user.getAuswertungen().toArray());
        this.analysisBox.addActionListener(this);
        if(!this.user.getAuswertungen().isEmpty()){
            this.analysisBox.setSelectedIndex(0);
        }
        this.analysisPanel.setPreferredSize(new Dimension(500, 500));
    }

    private void createChartAnalysis() {
        this.desktopPane.removeAll();
        ChartAnalysis ana = (ChartAnalysis) this.analysisBox.getSelectedItem();
        Konto kon = ana.getKonto();
        List<JFreeChart> charts = ChartFactoryMapper.getInstance().getChartsByAnalysis(ana);
        int xStart = 0;
        int yStart = 0;
        int xEnd = 100;
        int yEnd = 100;
        for (JFreeChart chart : charts) {

            JInternalFrame iframe = new JInternalFrame("Chart", true, true, true, true);
            iframe.setBounds(xStart, yStart, xEnd, yEnd);
            ChartPanel chartPanel = new ChartPanel(chart);
            // default size
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
            chartPanel.setVisible(true);

            xStart += 100;
            xEnd += 100;
            if (xStart > 500) {
                xStart = 0;
                yStart += 100;
                xEnd = 0;
                yEnd += 100;
            }
            iframe.add(chartPanel);
            this.desktopPane.add(iframe);
        }
        this.analysisPanel.removeAll();
        this.analysisPanel.add(this.desktopPane);
    }

    private void createCompareAnalysis() {

        this.analysisPanel.removeAll();
        this.analysisPanel.add(this.comparePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.analysisBox) {
            if (analysisBox.getSelectedItem() instanceof ChartAnalysis) {
                this.createChartAnalysis();
            } else {
                this.createCompareAnalysis();
            }
        }
    }

    private void createTestIFrames1(JDesktopPane desktopPane) {
        JInternalFrame iframe = new JInternalFrame("Chart 1", true, true, true, true);

//        iframe.setBounds((int) (4 * 100), (int) (4 * 100),
//                100 + (int) (4 * 400), 100 + (int) (4 * 300));
        iframe.setBounds(100, 100, 300, 300);

        iframe.add(createTestPie());
        iframe.setVisible(true);
//        iframe.setSize( /* width = */ 200, /* height = */ 100 );

        desktopPane.add(iframe);
    }

    private void createTestIFrames2(JDesktopPane desktopPane) {
        JInternalFrame iframe = new JInternalFrame("Chart 1", true, true, true, true);

//        iframe.setBounds((int) (4 * 100), (int) (4 * 100),
//                100 + (int) (4 * 400), 100 + (int) (4 * 300));
        iframe.setBounds(300, 300, 500, 500);

        iframe.add(createTestPie());
        iframe.setVisible(true);
//        iframe.setSize( /* width = */ 200, /* height = */ 100 );

        desktopPane.add(iframe);
    }

    private JComponent createTestPie() {
        PieDataset dataset = createDataset();
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, chartTitle);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

        chartPanel.setVisible(true);
        return chartPanel;
    }

    /**
     * * Creates a sample dataset
     */
    private PieDataset createDataset() {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Allgemein", 29);
        result.setValue("Essen", 20);
        result.setValue("Freundin", 51);
        return result;

    }

    private JFreeChart createChart(PieDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createPieChart3D(title, // chart title
                dataset, // data
                true, // include legend
                true,
                false);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;

    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel();

        GridLayout layout = new GridLayout(1, 3);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel label = new JLabel("Auswertungen:");
        panel.add(label);

        panel.add(createAnalysisComboBox());
        panel.setVisible(true);
        return panel;
    }

    private JPanel createAnalysisComboBox() {
        JPanel panel = new JPanel();
        JComboBox comboBox = new JComboBox(GUIHelper.getInstance().getAllAnaysisVariants());
        return panel;
    }

    @Override
    public void updateContent() {
        this.analysisBox.setModel(new DefaultComboBoxModel(this.user.getAuswertungen().toArray()));
    }

    @Override
    public JComponent getComponent() {
        return this;
    }
}
