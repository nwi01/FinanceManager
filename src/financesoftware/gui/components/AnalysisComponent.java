/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.components;

import financesoftware.gui.base.ViewComponent;
import financesoftware.tools.GUIHelper;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
public class AnalysisComponent extends BaseComponent implements ViewComponent {

    private GUIHelper helper = new GUIHelper();
    private String chartTitle;

    public AnalysisComponent() {
        super(true);
//        GridBagLayout layout = new GridBagLayout();
//        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.gridwidth = 1;
//        constraints.gridheight = GridBagConstraints.REMAINDER;
//        constraints.fill = GridBagConstraint
//        layout.setConstraints(mainPanel, constraints);
//        GridLayout layout = new GridLayout();
        JDesktopPane desktopPane = new JDesktopPane();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        desktopPane.setPreferredSize(dim);
//        desktopPane.setSize(dim);
//        FlowLayout layout = new FlowLayout();
//        desktopPane.setLayout(layout);
//        mainPanel.add(createTopPanel());
        mainPanel.add(desktopPane);
//        this.add(desktopPane);

        this.createTestIFrames1(desktopPane);
        this.createTestIFrames2(desktopPane);



        this.setVisible(true);
    }

//    private JPanel createMainAnalysisPanel() {
//        JDesktopPane pane = new JDesktopPane();        
//        panel.add(createTestPie());
//        return panel;
//    }
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
        JComboBox comboBox = new JComboBox(helper.getAllAnaysisVariants());
        return panel;
    }

    @Override
    public void updateContent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
