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
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
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
    private JComboBox<Konto> kontoBox;
    private JComboBox<Buchung> buchungBox;
    private JComboBox<Dauerauftrag> dauerauftragBox;
    private JTextField kontoName;
    private JTextField kontoNummer;
    private JTextField kontoBLZ;
    private JTextField kontoIBAN;
    private JTextField kontoBIC;
    private JTextField kontoStand;

    private JRadioButton oldStyle;
    private JRadioButton newStyle;

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

            if (currentKonto.isOldStyle()) {
                this.oldStyle.setSelected(true);
            } else {
                this.newStyle.setSelected(true);
            }
        }

        if (event.getSource() == this.dauerauftragBox) {
            Dauerauftrag auftrag = (Dauerauftrag) this.dauerauftragBox.getSelectedItem();
        }

        if (event.getSource() == this.checkBoxNewAccount) {
            if (!checkBoxNewAccount.isSelected()) {
                if (this.kontoBox.getModel().getSize() != 0) {
                    this.kontoName.setText(((Konto) this.kontoBox.getSelectedItem()).getName());
                    this.kontoNummer.setText(((Konto) this.kontoBox.getSelectedItem()).getKontoNr());
                    this.kontoBLZ.setText(((Konto) this.kontoBox.getSelectedItem()).getBLZ());
                } else {
                    // Felder leeren
                    this.kontoName.setText("");
                    this.kontoNummer.setText("");
                    this.kontoBLZ.setText("");
                }

            } else {
                //Event ausloesen
                if (this.kontoBox.getSelectedIndex() != -1) {
                    this.kontoBox.setSelectedIndex(this.kontoBox.getSelectedIndex());
                }
            }
        }

        if (event.getSource() == this.oldStyle) {
            enableOrDisableOldStyle(true);
        }

        if (event.getSource() == this.newStyle) {
            enableOrDisableOldStyle(false);
        }

    }

    private void enableOrDisableOldStyle(boolean setOldStyleEnabled) {
        this.kontoIBAN.setEnabled(!setOldStyleEnabled);
        this.kontoBIC.setEnabled(!setOldStyleEnabled);

        this.kontoNummer.setEnabled(setOldStyleEnabled);
        this.kontoBLZ.setEnabled(setOldStyleEnabled);
    }

    @Override
    public void saveOrUpdate() {
        if (this.checkBoxNewAccount.isSelected()) {
            String nameS = this.kontoName.getText();
            String kontoNrS = this.kontoNummer.getText();
            String kontoBLZS = this.kontoBLZ.getText();
            String kontoStandS = this.kontoStand.getText();

            try {
                if (!(nameS.isEmpty() && kontoNrS.isEmpty() && kontoBLZS.isEmpty() && kontoStandS.isEmpty())) {
                    Konto newKonto = new Konto(nameS, kontoNrS, kontoBLZS, Double.parseDouble(kontoStandS));
                    this.user.addKonto(newKonto);
                    this.kontoBox.setVisible(true);
                    this.kontoBox.setModel(new DefaultComboBoxModel(this.user.getKonten().toArray()));
                    this.kontoBox.setSelectedItem(newKonto);
                }
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
            this.kontoBox.setVisible(true);
            Verschluesselung.save(user);
        }
        this.checkBoxNewAccount.setEnabled(true);
    }

    private JPanel createUserManagement() {
        JPanel panel = new JPanel();
//        panel.setBackground(Color.red);
//        panel.setPreferredSize(new Dimension(800, 500));
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
        panel.add(this.oldStyle, constraints);

        constraints.gridy++;
        panel.add(this.newStyle, constraints);

        constraints.gridy++;
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
        panel.add(new JLabel("IBAN:"), constraints);

        constraints.gridx++;
        panel.add(this.kontoIBAN, constraints);

        constraints.gridx++;
        panel.add(new JLabel("BIC:"), constraints);

        constraints.gridx++;
        panel.add(this.kontoBIC, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        panel.add(new JLabel("Konto-Stand:"), constraints);

        constraints.gridx++;
        panel.add(this.kontoStand, constraints);

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

        this.checkBoxNewAccount = new JCheckBox("(neues Konto anlegen)");
        this.checkBoxNewAccount.addActionListener(this);

        // Konto Felder
        this.oldStyle = new JRadioButton("(Alte Variante)");
        this.oldStyle.addActionListener(this);
        this.newStyle = new JRadioButton("(Neue Variante)");
        this.newStyle.addActionListener(this);

        ButtonGroup group = new ButtonGroup();
        group.add(this.oldStyle);
        group.add(this.newStyle);

        this.kontoStand = new JFormattedTextField(NumberFormat.getNumberInstance());
        this.kontoStand.setPreferredSize(new Dimension(100, 28));
        this.kontoIBAN = new JTextField();
        this.kontoBIC = new JTextField();
        this.kontoName = new JTextField();
        this.kontoNummer = new JTextField();
        this.kontoBLZ = new JTextField();
        this.kontoBLZ.setPreferredSize(new Dimension(100, 28));
        this.kontoBox = new JComboBox(this.user.getKonten().toArray());
        kontoBox.addActionListener(this);
        if (!this.user.getKonten().isEmpty()) {
            this.kontoBox.setSelectedIndex(0);
        } else {
            this.checkBoxNewAccount.setSelected(true);
            this.checkBoxNewAccount.setEnabled(false);
            this.kontoBox.setVisible(false);
        }

        this.newStyle.setSelected(true);
        enableOrDisableOldStyle(false);

    }

    @Override
    public void updateContent() {
    }

}
