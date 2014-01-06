package financesoftware.gui.components;

import financesoftware.base.Konto;
import financesoftware.base.analysis.ChartAnalysis;
import financesoftware.gui.base.ManagementBaseComponent;
import financesoftware.tools.ChartFactory;
import financesoftware.tools.GUIHelper;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author nwi01
 */
public class AnalysisManagementComponent extends ManagementBaseComponent {

    private JComboBox analysisBox;
    private JComboBox<Konto> kontoBox;
    private JCheckBox checkBoxNewAnalysis;
    private boolean isChecked = false;
    private JTextField name;
    private JTextField from;
    private JTextField to;

    // Diagramm Auswertung
    private JRadioButton isChartAnalysis;
    private JList availChartsList;
    private JList currentChartsList;
    private JButton addChartButton;
    private JButton removeChartButton;
    private List<String> currentCharts;
    private List<String> availCharts;

    //Kategorie Auswahl
    private JList availCategoriesList;
    private JList currentCategoriesList;
    private List<String> availCategories;
    private List<String> currentCategories;
    private JButton addCategoryButton;
    private JButton removeCategoryButton;

    private JRadioButton isCompareAnalysis;

    public AnalysisManagementComponent() {
        super();
    }

    /**
     * Erster Schritt: Auswahl Auswertung bearbeiten oder neue anlegen
     */
    private JPanel createBasicStepPanel() {
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 8;
        JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
        sep.setPreferredSize(new Dimension(700, 10));
        panel.add(sep, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 1;
        JLabel labelU = new JLabel("Auswertung:");
        labelU.setFont(new Font("Dialog", Font.PLAIN, 18));
        panel.add(labelU, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 5;
        panel.add(this.analysisBox, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        panel.add(this.checkBoxNewAnalysis, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 8;
        JSeparator sep2 = new JSeparator(JSeparator.HORIZONTAL);
        sep2.setPreferredSize(new Dimension(700, 10));
        panel.add(sep2, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 1;
        panel.add(new JLabel("Name:"), constraints);

        constraints.gridx++;
        panel.add(this.name, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        panel.add(new JLabel("Von:"), constraints);

        constraints.gridx++;
        panel.add(this.from, constraints);

        constraints.gridx++;
        panel.add(new JLabel("bis"), constraints);

        constraints.gridx++;
        panel.add(this.to, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 8;
        JSeparator sep3 = new JSeparator(JSeparator.HORIZONTAL);
        sep3.setPreferredSize(new Dimension(700, 10));
        panel.add(sep3, constraints);

        // Buttons sollen in einer Gruppe sein, damit nur einer ausgewaehlt werden kann
        ButtonGroup group = new ButtonGroup();
        group.add(this.isChartAnalysis);
        group.add(this.isCompareAnalysis);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        panel.add(this.isChartAnalysis, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        panel.add(this.isCompareAnalysis, constraints);

        return panel;
    }

    private JPanel createChartAnalysis() {
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 8;
        JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
        sep.setPreferredSize(new Dimension(700, 10));
        panel.add(sep, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        panel.add(new JLabel("Konto:"), constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 5;
        panel.add(this.kontoBox, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 8;
        JSeparator sep2 = new JSeparator(JSeparator.HORIZONTAL);
        sep2.setPreferredSize(new Dimension(700, 10));
        panel.add(sep2, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 1;
        JLabel labelU = new JLabel("Auswahl mit Diagramme:");
        labelU.setFont(new Font("Dialog", Font.PLAIN, 18));
        panel.add(labelU, constraints);

        constraints.gridx = 0;
        constraints.gridy += 2;
        constraints.gridwidth = 2;
        panel.add(new JLabel("Verfügbare Diagramme:"), constraints);

        constraints.gridx += 3;
        panel.add(new JLabel("Auswahl:"), constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 8;
        panel.add(this.availChartsList, constraints);

        constraints.gridx += 2;
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        panel.add(this.addChartButton, constraints);

        constraints.gridy += 2;
        panel.add(this.removeChartButton, constraints);

        constraints.gridx += 2;
        constraints.gridy -= 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 8;
        panel.add(this.currentChartsList, constraints);

        return panel;
    }

    private JPanel createCompareAnalysis() {
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 8;
        JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
        sep.setPreferredSize(new Dimension(700, 10));
        panel.add(sep, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 1;
        JLabel labelU = new JLabel("Auswertung mit Vergleich:");
        labelU.setFont(new Font("Dialog", Font.PLAIN, 18));
        panel.add(labelU, constraints);

        return panel;
    }

    private JPanel createCategoryChooser() {
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 8;
        JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
        sep.setPreferredSize(new Dimension(700, 10));
        panel.add(sep, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 1;
        JLabel labelU = new JLabel("Auswahl der Kategorien:");
        labelU.setFont(new Font("Dialog", Font.PLAIN, 18));
        panel.add(labelU, constraints);

        constraints.gridx = 0;
        constraints.gridy += 2;
        constraints.gridwidth = 2;
        panel.add(new JLabel("Verfügbare Kategorien:"), constraints);

        constraints.gridx += 3;
        panel.add(new JLabel("Auswahl:"), constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 8;
        panel.add(this.availCategoriesList, constraints);

        constraints.gridx += 2;
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        panel.add(this.addCategoryButton, constraints);

        constraints.gridy += 2;
        panel.add(this.removeCategoryButton, constraints);

        constraints.gridx += 2;
        constraints.gridy -= 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 8;
        panel.add(this.currentCategoriesList, constraints);

        return panel;
    }


    public void updateContent() {     
    }

    @Override
    public ArrayList<JPanel> getSectionPanels() {
        ArrayList<JPanel> sectionsD = new ArrayList();
        sectionsD.add(this.createBasicStepPanel());
        sectionsD.add(new JPanel());
        sectionsD.add(this.createCategoryChooser());
        return sectionsD;
    }

    @Override
    public void specialAction(ActionEvent event) {
        if (event.getSource() == this.analysisBox) {

        }

        if (event.getSource() == this.checkBoxNewAnalysis) {
            if (!isChecked) {
                this.isChecked = true;
//                this.name.setText("");
            } else {
//                this.categoryBox.setSelectedIndex(this.categoryBox.getSelectedIndex());
                this.isChecked = false;
            }
        }

        if (event.getSource() == this.isChartAnalysis) {
            this.sections.remove(1);
            this.sections.add(1, this.createChartAnalysis());
            this.currentCharts = ChartFactory.getMappedCharts(((ChartAnalysis)this.analysisBox.getSelectedItem()).getCharts());
        }

        if (event.getSource() == this.isCompareAnalysis) {
            this.sections.remove(1);
            this.sections.add(1, this.createCompareAnalysis());
        }

        if (event.getSource() == this.addChartButton) {
            String chart = (String) this.availChartsList.getSelectedValue();
            if (chart != null) {
                this.currentCharts.add(chart);
                this.currentChartsList.setListData(this.currentCharts.toArray());

                this.availCharts.remove(chart);
                this.availChartsList.setListData(this.availCharts.toArray());
            }
        }

        if (event.getSource() == this.removeChartButton) {
            String chart = (String) this.currentChartsList.getSelectedValue();
            if (chart != null) {
                this.currentCharts.remove(chart);
                this.currentChartsList.setListData(this.currentCharts.toArray());

                this.availCharts.add(chart);
                this.availChartsList.setListData(this.availCharts.toArray());
            }
        }
        
                if (event.getSource() == this.addCategoryButton) {
            String chart = (String) this.availCategoriesList.getSelectedValue();
            if (chart != null) {
                this.currentCategories.add(chart);
                this.currentCategoriesList.setListData(this.currentCategories.toArray());

                this.availCategories.remove(chart);
                this.availCategoriesList.setListData(this.availCategories.toArray());
            }
        }

        if (event.getSource() == this.removeCategoryButton) {
            String chart = (String) this.currentCategoriesList.getSelectedValue();
            if (chart != null) {
                this.currentCategories.remove(chart);
                this.currentCategoriesList.setListData(this.currentCategories.toArray());

                this.availCategories.add(chart);
                this.availCategoriesList.setListData(this.availCharts.toArray());
            }
        }
               
        if (event.getSource() == this.kontoBox) {
        }

    }

    @Override
    public void saveOrUpdate() {
        if(isChecked){
            this.analysisBox.setVisible(true);
            //TODO 
        }
    }

    @Override
    public void initFields() {
        this.kontoBox = new JComboBox(GUIHelper.getInstance().getUser().getKonten().toArray());
        this.kontoBox.addActionListener(this);
        //Diagramme
        this.currentCharts = new ArrayList();
        this.availCharts = ChartFactory.getAllAvailableCharts();
        this.availChartsList = new JList(this.availCharts.toArray());
        this.currentChartsList = new JList(this.currentCharts.toArray());
        this.addChartButton = new JButton(">>");
        this.addChartButton.setToolTipText("Zur Auswahl hinzufügen");
        this.addChartButton.addActionListener(this);
        this.removeChartButton = new JButton("<<");
        this.removeChartButton.setToolTipText("Von der Auswahl entfernen");
        this.removeChartButton.addActionListener(this);

        //Kategorien
        this.currentCategories = new ArrayList();
        this.availCategories = GUIHelper.getInstance().getUser().getKategorienString();
        this.availCategoriesList = new JList(this.availCategories.toArray());
        this.currentCategoriesList = new JList(this.currentCategories.toArray());
        this.addCategoryButton = new JButton(">>");
        this.addCategoryButton.setToolTipText("Zur Auswahl hinzufügen");
        this.addCategoryButton.addActionListener(this);
        this.removeCategoryButton = new JButton("<<");
        this.removeCategoryButton.setToolTipText("Von der Auswahl entfernen");
        this.removeCategoryButton.addActionListener(this);

        this.isChartAnalysis = new JRadioButton("(Auswertung mit Diagrammen)");
        this.isChartAnalysis.addActionListener(this);
        this.isCompareAnalysis = new JRadioButton("(Vergleich von Konten)");
        this.isCompareAnalysis.addActionListener(this);

        this.from = new JTextField();
        this.from.setPreferredSize(new Dimension(100, 28));
        this.to = new JTextField();
        this.to.setPreferredSize(new Dimension(100, 28));
        this.name = new JTextField();
        this.checkBoxNewAnalysis = new JCheckBox("(neue Auswertung anlegen)");
        this.checkBoxNewAnalysis.addActionListener(this);
        this.analysisBox = new JComboBox(GUIHelper.getInstance().getUser().getAuswertungen().toArray());
        this.analysisBox.addActionListener(this);
        if (!GUIHelper.getInstance().getUser().getAuswertungen().isEmpty()) {
            this.analysisBox.setSelectedIndex(0);
        }
        else{
            this.analysisBox.setVisible(false);
            this.checkBoxNewAnalysis.setSelected(true);
            this.checkBoxNewAnalysis.setEnabled(false);
        }
    }
}
