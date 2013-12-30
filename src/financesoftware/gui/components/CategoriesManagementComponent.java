/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.components;

import financesoftware.base.Kategorie;
import financesoftware.base.analysis.Analysis;
import financesoftware.gui.base.ManagementBaseComponent;
import financesoftware.tools.GUIHelper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author niels
 */
public class CategoriesManagementComponent extends ManagementBaseComponent {

    private JComboBox<Kategorie> categoryBox;
    private JComboBox<Analysis> analysisBox;
    private JCheckBox checkBoxNewCategoriy;
    private boolean isChecked = false;
    //Kategorie
    private JTextField name;
    private JButton color;

    @Override
    public void specialAction(ActionEvent event) {
        if (event.getSource() == this.analysisBox) { 
            this.categoryBox.setModel(new DefaultComboBoxModel( ((Analysis) this.analysisBox.getSelectedItem()).getKategorien().toArray()));
            if (!((Analysis) this.analysisBox.getSelectedItem()).getKategorien().isEmpty()) {
                this.categoryBox.setSelectedIndex(0);
            }
        }

        if (event.getSource() == this.categoryBox) {
            this.name.setText(((Kategorie) this.categoryBox.getSelectedItem()).getlName());
            this.color.setBackground(((Kategorie) this.categoryBox.getSelectedItem()).getlFarbe());
        }

        if (event.getSource() == this.checkBoxNewCategoriy) {
            if (!isChecked) {
                this.isChecked = true;
                this.name.setText("");
            } else {
                this.categoryBox.setSelectedIndex(this.categoryBox.getSelectedIndex());
                this.isChecked = false;
            }
        }

        if (event.getSource() == this.color) {
            Color col = JColorChooser.showDialog(this, "Farbe auswählen", Color.yellow);
            this.color.setBackground(col);
        }
    }

    @Override
    public void saveOrUpdate() {

    }

    private JPanel createCategoriesPanel() {
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
        labelU.setFont(new Font("Dialog", Font.PLAIN, 16));
        panel.add(labelU, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 5;
        panel.add(this.analysisBox, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 8;
        JSeparator sep2 = new JSeparator(JSeparator.HORIZONTAL);
        sep2.setPreferredSize(new Dimension(700, 10));
        panel.add(sep2, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 1;
        JLabel labelC = new JLabel("Kategorie:");
        labelC.setFont(new Font("Dialog", Font.PLAIN, 18));
        panel.add(labelC, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 5;
        panel.add(this.categoryBox, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        panel.add(this.checkBoxNewCategoriy, constraints);

        constraints.gridy++;
        panel.add(new JLabel("Name:"), constraints);

        constraints.gridx++;
        panel.add(this.name, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        panel.add(new JLabel("Farbe:"), constraints);

        constraints.gridx++;
        panel.add(this.color, constraints);

        return panel;
    }

    @Override
    public ArrayList<JPanel> getSectionPanels() {
        ArrayList<JPanel> sectionsD = new ArrayList();
        sectionsD.add(this.createCategoriesPanel());
        return sectionsD;
    }

    @Override
    public void initFields() {
        this.categoryBox = new JComboBox();
        this.categoryBox.addActionListener(this);
        
        this.name = new JTextField();
        
        this.checkBoxNewCategoriy = new JCheckBox("(neue Kategorie anlegen)");
        this.checkBoxNewCategoriy.addActionListener(this);
        
        this.color = new JButton();
        this.color.addActionListener(this);
        this.color.setPreferredSize(new Dimension(100, 28));

        this.analysisBox = new JComboBox(GUIHelper.getInstance().getUser().getAuswertungen().toArray());
        this.analysisBox.addActionListener(this);
        if (!GUIHelper.getInstance().getUser().getAuswertungen().isEmpty()) {
            this.analysisBox.setSelectedIndex(0);
        }

    }
}
