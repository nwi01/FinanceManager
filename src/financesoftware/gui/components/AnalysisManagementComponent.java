package financesoftware.gui.components;

import financesoftware.base.Kategorie;
import financesoftware.base.Konto;
import financesoftware.base.Zeitraum;
import financesoftware.base.analysis.Analysis;
import financesoftware.base.analysis.ChartAnalysis;
import financesoftware.base.analysis.ChartEnum;
import financesoftware.base.analysis.CompareAnalysis;
import financesoftware.gui.base.ManagementBaseComponent;
import financesoftware.tools.ChartFactoryMapper;
import financesoftware.tools.GUIHelper;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
    private JButton deleteAnalysis;
    private JComboBox<Konto> kontoBox;
    private JTextField name;
    private JTextField from;
    private JTextField to;

    // Diagramm Auswertung
    private JRadioButton isChartAnalysis;
    private JList availChartsList;
    private JList currentChartsList;
    private JButton addChartButton;
    private JButton removeChartButton;
    private List currentCharts;
    private List availCharts;

    //Kategorie Auswahl
    private JList availCategoriesList;
    private JList currentCategoriesList;
    private List availCategories;
    private List currentCategories;
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

        constraints.gridx = 9;
        constraints.gridwidth = 1;
        panel.add(this.deleteAnalysis, constraints);

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

    @Override
    public ArrayList<JPanel> getSectionPanels() {
        ArrayList<JPanel> sectionsD = new ArrayList();
        sectionsD.add(this.createBasicStepPanel());
        sectionsD.add(this.createCategoryChooser());
        sectionsD.add(new JPanel());
        return sectionsD;
    }

    private List<Kategorie> getAvailList(List<Kategorie> list) {
        ArrayList<Kategorie> newList = new ArrayList();
        List<Kategorie> old = this.user.getKategorien();
        ArrayList<String> doppelt = new ArrayList();
        for (Kategorie kat : old) {
            boolean containts = false;
            for (Kategorie kat2 : list) {
                if (kat2.getlName().equals(kat.getlName())) {
                    containts = true;
                }
            }

            if (!containts && !(doppelt.contains(kat.getlName()))) {
                newList.add(kat);
                doppelt.add(kat.getlName());
            }
        }
        return newList;
    }

    @Override
    public void specialAction(ActionEvent event) {
        if (event.getSource() == this.analysisBox) {
            this.analysisBox.setEnabled(true);
            Analysis ana = (Analysis) this.analysisBox.getSelectedItem();
            if (ana != null) {
                this.name.setText(ana.getName());
                this.from.setText(GUIHelper.getStringRepresantation(ana.getZeitraum().getStartzeit().getTime()));
                this.to.setText(GUIHelper.getStringRepresantation(ana.getZeitraum().getEndezeit().getTime()));

                this.currentCategories.clear();
                this.availCategoriesList.removeAll();

                this.currentCategoriesList.setListData(ana.getKategorien().toArray());
                this.currentCategories.addAll(ana.getKategorien());

                List<Kategorie> list = getAvailList(ana.getKategorien());
                this.availCategoriesList.setListData(list.toArray());
                this.availCategories.clear();
                this.availCategories.addAll(list);

                if (ana instanceof ChartAnalysis) {
                    ChartAnalysis chartAna = (ChartAnalysis) ana;
                    this.currentCharts.clear();
                    this.currentCharts.addAll(chartAna.getCharts());
                    this.currentChartsList.setListData(this.currentCharts.toArray());
                    this.isChartAnalysis.doClick();
                } else {
                    CompareAnalysis compareAna = (CompareAnalysis) ana;
                    this.currentCategories.clear();
                    this.currentCategories.addAll(compareAna.getKategorien());
                    this.isCompareAnalysis.doClick();
                }
            }

        }

        if (event.getSource() == this.isChartAnalysis) {
            this.sections.remove(2);
            this.sections.add(2, this.createChartAnalysis());
        }

        if (event.getSource() == this.isCompareAnalysis) {
            this.sections.remove(2);
            this.sections.add(2, this.createCompareAnalysis());
            this.getNext().setEnabled(true);
        }

        if (event.getSource() == this.addChartButton) {
            ChartEnum chart = (ChartEnum) this.availChartsList.getSelectedValue();
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
            Kategorie kat = (Kategorie) this.availCategoriesList.getSelectedValue();
            if (kat != null) {
                this.currentCategories.add(kat);
                this.currentCategoriesList.setListData(this.currentCategories.toArray());

                this.availCategories.remove(kat);
                this.availCategoriesList.setListData(this.availCategories.toArray());
            }
        }

        if (event.getSource() == this.removeCategoryButton) {
            Kategorie kat = (Kategorie) this.currentCategoriesList.getSelectedValue();
            if (kat != null) {
                this.currentCategories.remove(kat);
                this.currentCategoriesList.setListData(this.currentCategories.toArray());

                this.availCategories.add(kat);
                this.availCategoriesList.setListData(this.availCategories.toArray());
            }
        }

        if (event.getSource() == this.kontoBox) {
        }

        if (event.getSource() == this.deleteAnalysis) {
            Analysis ana = ((Analysis) this.analysisBox.getSelectedItem());
            this.user.getAuswertungen().remove(ana);
            this.updateContent();
        }

    }

    @Override
    public void saveOrUpdate() {

        String nameS = this.name.getText();
        Zeitraum zeit = new Zeitraum(Zeitraum.parseCalendar(this.from.getText()), Zeitraum.Intervall.TAEGLICH, Zeitraum.parseCalendar(this.to.getText()));
        List<Kategorie> kat = GUIHelper.copyCategoryList(this.currentCategories);
        if (this.isChartAnalysis.isSelected()) {
            List<ChartEnum> charts = this.currentCharts;
            Konto kon = (Konto) this.kontoBox.getSelectedItem();
            ChartAnalysis ana = new ChartAnalysis(nameS, zeit, kat, false, kon);
            ana.setCharts(charts);
            this.user.addAuswertung(ana);
        } else {

        }
    }

    @Override
    public void initFields() {
        this.deleteAnalysis = new JButton("X");
        this.deleteAnalysis.addActionListener(this);

        //Kategorien
        this.currentCategories = new ArrayList();
        this.availCategories = this.user.copyKategorien();//this.user.getKategorien();
        this.availCategoriesList = new JList();
        this.currentCategoriesList = new JList();
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

        this.from = new JFormattedTextField(new Date());
        this.from.setPreferredSize(new Dimension(100, 28));
        this.to = new JFormattedTextField(new Date());
        this.to.setPreferredSize(new Dimension(100, 28));
        this.name = new JTextField();

        //Diagramme
        this.currentCharts = new ArrayList();
        this.availCharts = ChartFactoryMapper.getAllAvailableCharts();
        this.availChartsList = new JList();
        this.currentChartsList = new JList();
        this.addChartButton = new JButton(">>");
        this.addChartButton.setToolTipText("Zur Auswahl hinzufügen");
        this.addChartButton.addActionListener(this);
        this.removeChartButton = new JButton("<<");
        this.removeChartButton.setToolTipText("Von der Auswahl entfernen");
        this.removeChartButton.addActionListener(this);
        //
        this.analysisBox = new JComboBox();
        this.analysisBox.addActionListener(this);

        this.kontoBox = new JComboBox();
        this.kontoBox.addActionListener(this);
    }

    @Override
    public void updateContent() {
        this.currentCategoriesList.setListData(this.currentCategories.toArray());

        this.availChartsList.setListData(this.availCharts.toArray());
        this.currentChartsList.setListData(this.currentCharts.toArray());

        this.kontoBox.setModel(new DefaultComboBoxModel(this.user.getKonten().toArray()));
        if (this.kontoBox.getModel().getSize() != 0) {
            this.enableOrDisable(true);
        } else {
            this.enableOrDisable(false);
        }
        this.analysisBox.setModel(new DefaultComboBoxModel(this.user.getAuswertungen().toArray()));
        if (!this.user.getAuswertungen().isEmpty()) {
            this.analysisBox.setSelectedIndex(0);
        } else {
            this.analysisBox.setEnabled(false);
            this.availCategoriesList.setListData(this.user.getKategorien().toArray());
            this.availCategories.clear();
            this.availCategories.addAll(this.user.getKategorien());
        }
    }

    private void enableOrDisable(boolean enableOrDisable) {
        this.kontoBox.setEnabled(enableOrDisable);
        this.availChartsList.setEnabled(enableOrDisable);
        this.addChartButton.setEnabled(enableOrDisable);
        this.removeChartButton.setEnabled(enableOrDisable);
        this.currentChartsList.setEnabled(enableOrDisable);

    }
}
