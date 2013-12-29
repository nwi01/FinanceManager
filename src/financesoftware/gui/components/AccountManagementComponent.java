/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.components;

import financesoftware.base.Buchung;
import financesoftware.base.Dauerauftrag;
import financesoftware.base.Konto;
import financesoftware.gui.base.ManagementBaseComponent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author melanie
 */
public class AccountManagementComponent extends ManagementBaseComponent {

    private JTextField name = new JTextField();
    private JPasswordField passwordTF = new JPasswordField();

    //Konto
    private JComboBox<Konto> kontoBox;
    private JComboBox<Buchung> buchungBox;
    private JComboBox<Dauerauftrag> dauerauftragBox;
    private JTextField kontoName;
    private JTextField kontoNummer;
    private JTextField kontoBLZ;
    

    private Konto currentKonto;

    public AccountManagementComponent() {
        super();
    }

    @Override
    public void specialAction(ActionEvent event) {
        if (event.getSource() == this.kontoBox) {
            currentKonto = (Konto) this.kontoBox.getSelectedItem();
            int index = this.user.getKonten().indexOf(currentKonto);
            this.buchungBox = new JComboBox(this.user.getKonten().get(index).getBuchungen().toArray());
            this.dauerauftragBox = new JComboBox(this.user.getKonten().get(index).getDauerauftraege().toArray());
            this.kontoName.setText(currentKonto.getName());
            this.kontoNummer.setText(currentKonto.getKontoNr());
            this.kontoBLZ.setText(currentKonto.getBLZ());
        }
        
        if(event.getSource() == this.dauerauftragBox){
            Dauerauftrag auftrag = (Dauerauftrag)this.dauerauftragBox.getSelectedItem();
            
        }
    }

    @Override
    public void saveOrUpdate() {
    }

    private JPanel createUserManagement() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 500));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
//        panel.setBackground(Color.red);

        //Layout
        GridBagLayout layout = new GridBagLayout();

        panel.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();

//        constraints.weightx = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel labelU = new JLabel("Benutzer:");
        labelU.setFont(new Font("Dialog", Font.PLAIN, 18));
        panel.add(labelU, constraints);

        constraints.gridy++;
        panel.add(new JLabel("Name:"), constraints);

        constraints.gridx++;
        panel.add(this.name, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        panel.add(new JLabel("Password: "), constraints);

        constraints.gridx += 1;
        panel.add(this.passwordTF, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 8;
        JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
        sep.setPreferredSize(new Dimension(700, 10));
        panel.add(sep, constraints);

        constraints.gridy++;
        constraints.gridx = 3;
        constraints.gridwidth = 5;

        panel.add(kontoBox, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        panel.add(new JLabel("Name:"), constraints);

        constraints.gridx++;
        panel.add(this.kontoName, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        panel.add(new JLabel("Konto-Nummer:"), constraints);

        constraints.gridx++;
        panel.add(this.kontoNummer, constraints);

        constraints.gridx++;
        panel.add(new JLabel("BLZ:"), constraints);

        constraints.gridx++;
        panel.add(this.kontoBLZ, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 8;
        constraints.insets = new Insets(5, 5, 200, 5);
//        constraints.anchor = GridBagConstraints.PAGE_END;
        JSeparator sep2 = new JSeparator(JSeparator.HORIZONTAL);
        sep.setPreferredSize(new Dimension(700, 10));
        panel.add(sep2, constraints);

        return panel;
    }

    @Override
    public ArrayList<JPanel> getSectionPanels() {
        ArrayList<JPanel> sectionsD = new ArrayList();
        sectionsD.add(this.createUserManagement());
        return sectionsD;
    }

    @Override
    public void initFields() {
        this.name = new JTextField();
        this.passwordTF = new JPasswordField();

        this.name.setText(this.user.getName());
//        this.passwordTF.setText(this.user.getPassword());
        this.passwordTF.setText("Tesdfwefefefst");

        // Konto Felder
        this.kontoName = new JTextField();
        this.kontoNummer = new JTextField();
        this.kontoBLZ = new JTextField();
        this.kontoBox = new JComboBox(this.user.getKonten().toArray());
        kontoBox.addActionListener(this);
        if (!this.user.getKonten().isEmpty()) {
            this.kontoBox.setSelectedIndex(0);
        }
//
//        this.dauerauftragBox = new JComboBox(currentKonto.getDauerauftraege().toArray());
//        this.dauerauftragBox.addActionListener(this);
//        if (!currentKonto.getDauerauftraege().isEmpty()) {
//            this.dauerauftragBox.setSelectedIndex(0);
//        }
    }

}
