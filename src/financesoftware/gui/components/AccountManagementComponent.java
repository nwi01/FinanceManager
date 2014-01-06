/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.components;

import financesoftware.base.Buchung;
import financesoftware.base.Dauerauftrag;
import financesoftware.base.Konto;
import financesoftware.base.Verschluesselung;
import financesoftware.gui.base.ManagementBaseComponent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JCheckBox;
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
    private JCheckBox checkBoxNewAccount;
    private boolean isChecked = false;
    private JComboBox<Konto> kontoBox;
    private JComboBox<Buchung> buchungBox;
    private JComboBox<Dauerauftrag> dauerauftragBox;
    private JTextField kontoName;
    private JTextField kontoNummer;
    private JTextField kontoBLZ;
    private JTextField aktKontostand;

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

        if (event.getSource() == this.dauerauftragBox) {
            Dauerauftrag auftrag = (Dauerauftrag) this.dauerauftragBox.getSelectedItem();
        }

        if (event.getSource() == this.checkBoxNewAccount) {
            if (!isChecked) {
                // Felder leeren
                this.kontoName.setText("");
                this.kontoNummer.setText("");
                this.kontoBLZ.setText("");
                this.isChecked = true;
            } else {
                //Event ausloesen
                if (this.kontoBox.getSelectedIndex() != -1) {
                    this.kontoBox.setSelectedIndex(this.kontoBox.getSelectedIndex());
                }
                this.isChecked = false;
            }
        }
    }

    @Override
    public void saveOrUpdate() {
        if (this.isChecked) {
            String nameS = this.kontoName.getText();
            String kontoNrS = this.kontoNummer.getText();
            String kontoBLZS = this.kontoBLZ.getText();
            //TODO: NIELS: GUI anpassen. NumericField einfuegen fuer den Startkontostand.
            double aktKontostand = Double.parseDouble(this.aktKontostand.getText());

            try {
                Konto newKonto = new Konto(nameS, kontoNrS, kontoBLZS, aktKontostand);
                this.user.addKonto(newKonto);
                Verschluesselung.save(user);
                this.kontoBox.addItem(newKonto);
            } catch (Exception e) {
                System.out.println("Speichern fehlgeschlagen");
            }
        } else {
            String userName = this.name.getText();
            String userPw = this.passwordTF.getPassword().toString();

            String nameS = this.kontoName.getText();
            String kontoNrS = this.kontoNummer.getText();
            String kontoBLZS = this.kontoBLZ.getText();

            if (!nameS.equals("") && !kontoNrS.equals("") && !kontoBLZS.equals("")) {
                currentKonto.setName(nameS);
                currentKonto.setKontoNr(kontoNrS);
                currentKonto.setBLZ(kontoBLZS);                
            }

            if (!userName.equals("") && !userPw.equals("")) {
                this.user.setName(userName);
                this.user.setPassword(userPw);
            }
            
            Verschluesselung.save(user);

        }
    }

    private JPanel createUserManagement() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 500));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Layout
        GridBagLayout layout = new GridBagLayout();

        panel.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();

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

        constraints.gridx = 0;
        constraints.gridy++;
        JLabel labelK = new JLabel("Konto:");
        labelK.setFont(new Font("Dialog", Font.PLAIN, 18));
        panel.add(labelK, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 5;
        panel.add(kontoBox, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        panel.add(this.checkBoxNewAccount, constraints);

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
        this.passwordTF.setText("Tesdfwefefefst");

        // Konto Felder
        this.kontoName = new JTextField();
        this.kontoNummer = new JTextField();
        this.kontoBLZ = new JTextField();
        this.kontoBLZ.setPreferredSize(new Dimension(100, 28));
        this.kontoBox = new JComboBox(this.user.getKonten().toArray());
        kontoBox.addActionListener(this);
        if (!this.user.getKonten().isEmpty()) {
            this.kontoBox.setSelectedIndex(0);
        }
        this.checkBoxNewAccount = new JCheckBox("(neues Konto anlegen)");
        this.checkBoxNewAccount.addActionListener(this);

    }

}
