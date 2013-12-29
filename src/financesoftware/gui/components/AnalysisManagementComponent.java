package financesoftware.gui.components;

import financesoftware.gui.base.ManagementBaseComponent;
import financesoftware.gui.base.ViewComponent;
import financesoftware.tools.GUIHelper;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author nwi01
 */
public class AnalysisManagementComponent extends ManagementBaseComponent {

    private JRadioButton editAnalysisB = new JRadioButton("Auswertung bearbeiten");
    private JRadioButton newAnalysisB = new JRadioButton("neue Auswertung");
    private JPanel newAnalysisComboBoxPanel = new JPanel();
    private JComboBox comboBoxAnalysis;

    public AnalysisManagementComponent() {
        super();
    }

    /**
     * Erster Schritt: Auswahl Auswertung bearbeiten oder neue anlegen
     */
    private JPanel createBasicStepPanel() {
        JPanel panel = new JPanel();
        BorderLayout layout = new BorderLayout();
        panel.setLayout(layout);

        newAnalysisComboBoxPanel = new JPanel();
        newAnalysisComboBoxPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        BorderLayout layout2 = new BorderLayout();

        newAnalysisComboBoxPanel.setLayout(layout2);

        comboBoxAnalysis = new JComboBox(GUIHelper.getInstance().getAllAnalysisObjects().toArray());
        comboBoxAnalysis.addActionListener(this);

        newAnalysisComboBoxPanel.add(comboBoxAnalysis, BorderLayout.CENTER);
        newAnalysisComboBoxPanel.setVisible(false);

        JPanel topOuter = new JPanel();
        GridLayout layoutTopOuter = new GridLayout(2, 2);
        topOuter.setLayout(layoutTopOuter);

        // neue Auswertung anlegen
        JPanel newAnalysisPanel = new JPanel();
        newAnalysisPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridLayout layoutNewAnalysis = new GridLayout(2, 1);
        newAnalysisPanel.setLayout(layoutNewAnalysis);

        ButtonGroup group = new ButtonGroup();
        editAnalysisB = new JRadioButton("Auswertung bearbeiten");
        editAnalysisB.addActionListener(this);
        newAnalysisB = new JRadioButton("neue Auswertung");
        newAnalysisB.addActionListener(this);
        group.add(editAnalysisB);
        group.add(newAnalysisB);

        newAnalysisPanel.add(editAnalysisB);
        newAnalysisPanel.add(newAnalysisB);

        topOuter.add(newAnalysisPanel);
        topOuter.add(new JLabel());
        topOuter.add(newAnalysisComboBoxPanel);

        panel.add(topOuter, BorderLayout.PAGE_START);

        // Mitte
        JPanel middle = new JPanel();

//        panel.add(middle, BorderLayout.CENTER);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.mainPanel.setVisible(false);

        if (e.getSource() == this.newAnalysisB) {
            this.newAnalysisComboBoxPanel.setVisible(false);
        }

        if (e.getSource() == this.editAnalysisB) {
            this.newAnalysisComboBoxPanel.setVisible(true);
        }

        if (e.getSource() == this.comboBoxAnalysis) {

        }
        this.mainPanel.setVisible(true);
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    @Override
    public void updateContent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<JPanel> getSectionPanels() {
            ArrayList<JPanel> sectionsD = new ArrayList();
        sectionsD.add(this.createBasicStepPanel());
        JPanel panel = new JPanel();
        panel.add(new JLabel("efefe"));

//        this.mainPanel.add(panel, BorderLayout.CENTER);
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("efefe2222222"));
        sectionsD.add(panel);
        sectionsD.add(panel2);
        return sectionsD;
    }

    @Override
    public void specialAction(ActionEvent event) {

    }

    @Override
    public void saveOrUpdate() {

    }

    @Override
    public void initFields() {
       
    }
}
