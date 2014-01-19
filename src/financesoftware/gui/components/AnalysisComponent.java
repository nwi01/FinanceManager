/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.components;

import financesoftware.base.Konto;
import financesoftware.base.User;
import financesoftware.base.analysis.ChartAnalysis;
import financesoftware.base.analysis.CompareAnalysis;
import financesoftware.gui.base.ViewComponent;
import financesoftware.tools.ChartFactoryMapper;
import financesoftware.tools.GUIHelper;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

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
    private JFrame dummy = new JFrame();
    private JPanel comparePanel = new JPanel();

    public AnalysisComponent() {
        super(true);
        initFields();
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        desktopPane.setPreferredSize(new Dimension(1000, 800));
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
//        this.dummy.add(desktopPane);
        this.mainPanel.add(desktopPane, cons);
        this.setVisible(true);
    }

    private void updateDesktopPane() {
        this.mainPanel.remove(this.desktopPane);
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 2;
        cons.gridwidth = 10;
        cons.gridheight = 10;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.anchor = GridBagConstraints.CENTER;
        cons.insets = new Insets(5, 5, 5, 500);
        this.mainPanel.add(desktopPane, cons);
    }

    private void initFields() {
        this.analysisBox = new JComboBox(this.user.getAuswertungen().toArray());
        this.analysisBox.addActionListener(this);
        if (!this.user.getAuswertungen().isEmpty()) {
            this.analysisBox.setSelectedIndex(0);
        }
        this.analysisPanel.setPreferredSize(new Dimension(500, 500));
        this.analysisPanel.add(this.desktopPane);
    }

    private void createChartAnalysis() {
        this.desktopPane.removeAll();
        ChartAnalysis ana = (ChartAnalysis) this.analysisBox.getSelectedItem();
        Konto kon = ana.getKonto();
        List<JFreeChart> charts = ChartFactoryMapper.getInstance().getChartsByAnalysis(ana);
        int xStart = 0;
        int yStart = 0;
        int xEnd = 500;
        int yEnd = 400;
        for (JFreeChart chart : charts) {

            //Setzen der chart werte:
//            ChartFactoryMapper.
            JInternalFrame iframe = new JInternalFrame("Chart", true, true, true, true);
            iframe.setBounds(xStart, yStart, xEnd, yEnd);
            ChartPanel chartPanel = new ChartPanel(chart);
            // default size
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
            chartPanel.setVisible(true);

            xStart += 400;
            xEnd += 300;
            if (xStart >= 800) {
                xStart = 0;
                yStart += 400;
                xEnd = 0;
                yEnd += 400;
            }
            iframe.add(chartPanel);
            iframe.setVisible(true);
            this.desktopPane.add(iframe);
        }
        this.updateDesktopPane();
//        this.setVisible(true);
    }

    private void createCompareAnalysis() {
        this.desktopPane.removeAll();
        int xStart = 0;
        int yStart = 0;
        int xEnd = 500;
        int yEnd = 400;
        JInternalFrame iframe = new JInternalFrame("Vergleich", true, true, true, true);
        iframe.setBounds(xStart, yStart, xEnd, yEnd);

        CompareAnalysis ana = (CompareAnalysis) this.analysisBox.getSelectedItem();
        iframe.add(this.createCompareTable(ana));
        iframe.setVisible(true);
        this.desktopPane.add(iframe);
        this.updateDesktopPane();
    }

    private JTable createCompareTable(CompareAnalysis ana) {
        JTable table = new JTable();
        table.setShowHorizontalLines(true);
        table.setFillsViewportHeight(true);
        String[] columnNames = {"Kategorie", "1. Konto", "2. Konto"};
        table.setModel(new DefaultTableModel(GUIHelper.getCompareData(ana), columnNames));

        return table;
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

    @Override
    public void updateContent() {
        this.analysisBox.setModel(new DefaultComboBoxModel(this.user.getAuswertungen().toArray()));
        if (this.user.getAuswertungen().size() != 0) {
            this.analysisBox.setSelectedIndex(0);
        }
        this.desktopPane.setVisible(false);
        this.desktopPane.repaint();
        this.desktopPane.setVisible(true);
    }

    @Override
    public JComponent getComponent() {
        this.updateContent();
        return this;
    }
}
