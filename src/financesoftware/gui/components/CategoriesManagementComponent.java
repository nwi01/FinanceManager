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
 *  TODO: Hier sollten globale Kategorien erstellt/ Verwaltet werden und in der Auswertungs Verwaltung diese zur auswertung hinzugefügt werden können
 * @author niels
 */
public class CategoriesManagementComponent extends ManagementBaseComponent {

    private JComboBox<Kategorie> categoryBox;
    private JCheckBox checkBoxNewCategory;
    private boolean isChecked = false;
    //Kategorie
    private JTextField name;
    private JButton color;

    @Override
    public void specialAction(ActionEvent event) {
        if (event.getSource() == this.categoryBox) {
            this.name.setText(((Kategorie) this.categoryBox.getSelectedItem()).getlName());
            this.color.setBackground(((Kategorie) this.categoryBox.getSelectedItem()).getlFarbe());
        }

        if (event.getSource() == this.checkBoxNewCategory) {
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
        JLabel labelC = new JLabel("Kategorie:");
        labelC.setFont(new Font("Dialog", Font.PLAIN, 18));
        panel.add(labelC, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 5;
        panel.add(this.categoryBox, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        panel.add(this.checkBoxNewCategory, constraints);

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
        this.categoryBox = new JComboBox(GUIHelper.getInstance().getUser().getKategorien().toArray());
        this.categoryBox.addActionListener(this);
        
        this.name = new JTextField();
        
        this.checkBoxNewCategory = new JCheckBox("(neue Kategorie anlegen)");
        this.checkBoxNewCategory.addActionListener(this);
        
        this.color = new JButton();
        this.color.addActionListener(this);
        this.color.setPreferredSize(new Dimension(100, 28));

    }
}
