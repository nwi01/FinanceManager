package financesoftware.gui.components;

import financesoftware.base.Dauerauftrag;
import financesoftware.base.Kategorie;
import financesoftware.base.Konto;
import financesoftware.base.User;
import financesoftware.base.Verschluesselung;
import financesoftware.base.Zeitraum;
import financesoftware.gui.base.ManagementBaseComponent;
import financesoftware.tools.GUIHelper;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.InternationalFormatter;

/**
 *
 * @author niels
 */
public class StandingOrderManagementComponent extends ManagementBaseComponent {

    private JComboBox<Konto> konten;
    private JComboBox<Dauerauftrag> auftraege;
    private JButton deleteAuftraege;
    private JTextField startDate;
    private JComboBox intervall;
    private JFormattedTextField money;
    private JTextField to;
    private JRadioButton repeat;
    private JRadioButton untilDate;
    private JTextField untilDateTextField;
    private JSpinner repeatTextField;
    private JComboBox<Kategorie> categories;
    private JTextArea verwendungszweck;

    public StandingOrderManagementComponent() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setVisible(true);
    }

    private JPanel createStandingOrderPanel() {
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
        JLabel labelU = new JLabel("Konto:");
        labelU.setFont(new Font("Dialog", Font.PLAIN, 16));
        panel.add(labelU, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 5;
        panel.add(this.konten, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 8;
        JSeparator sep2 = new JSeparator(JSeparator.HORIZONTAL);
        sep2.setPreferredSize(new Dimension(700, 10));
        panel.add(sep2, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 1;
        JLabel labelC = new JLabel("Dauerauftrag:");
        labelC.setFont(new Font("Dialog", Font.PLAIN, 18));
        panel.add(labelC, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 5;
        panel.add(this.auftraege, constraints);

        constraints.gridx = 9;
        constraints.gridwidth = 1;
        panel.add(this.deleteAuftraege, constraints);

        constraints.gridwidth = 1;

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        JPanel panel2 = new JPanel();
        panel2.add(this.repeat);
        panel2.add(this.repeatTextField);
        panel.add(panel2, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        JPanel panel3 = new JPanel();
        panel3.add(this.untilDate);
        panel3.add(this.untilDateTextField);
        panel.add(panel3, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 8;
        JSeparator sep3 = new JSeparator(JSeparator.HORIZONTAL);
        sep3.setPreferredSize(new Dimension(700, 10));
        panel.add(sep3, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 1;

        panel.add(new JLabel("Kategorie:"), constraints);

        constraints.gridx++;
        panel.add(this.categories, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        panel.add(new JLabel("Startdatum:"), constraints);

        constraints.gridx++;
        panel.add(this.startDate, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 5;
        panel.add(this.intervall, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        panel.add(new JLabel("Adressat:"), constraints);

        constraints.gridx++;
        panel.add(this.to, constraints);

        constraints.gridx = 3;
        panel.add(new JLabel("Verwendungszweck:"), constraints);

        constraints.gridx++;
        constraints.gridwidth = 2;
        constraints.gridheight = 3;
        panel.add(this.verwendungszweck, constraints);

        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridy++;
        constraints.gridx = 0;
        panel.add(new JLabel("Betrag:"), constraints);

        constraints.gridx++;
        panel.add(this.money, constraints);

        return panel;
    }

    private void setAllEnabled(boolean isEnabled) {
//        this.checkBoxNewStandingOrder.setEnabled(isEnabled);
        this.startDate.setEnabled(isEnabled);
        this.money.setEnabled(isEnabled);
        this.to.setEnabled(isEnabled);
        this.intervall.setEnabled(isEnabled);
        this.auftraege.setEnabled(isEnabled);
        this.konten.setEnabled(isEnabled);
        this.repeat.setEnabled(isEnabled);
        this.untilDate.setEnabled(isEnabled);
        this.repeatTextField.setEnabled(isEnabled);
        this.untilDateTextField.setEnabled(isEnabled);
        this.categories.setEnabled(isEnabled);
    }

    @Override
    public void specialAction(ActionEvent event) {
        if (event.getSource() == this.konten) {
            this.startDate.setText("");
            this.money.setText("");
            this.to.setText("");
            this.verwendungszweck.setText("");
            this.auftraege.setModel(new DefaultComboBoxModel(((Konto) this.konten.getSelectedItem()).getDauerauftraege().toArray()));
            if (!((Konto) this.konten.getSelectedItem()).getDauerauftraege().isEmpty()) {
                this.auftraege.setSelectedIndex(0);
            } else {
//                this.checkBoxNewStandingOrder.setSelected(true);
            }
        }

        if (event.getSource() == this.auftraege) {
            Dauerauftrag auf = (Dauerauftrag) this.auftraege.getSelectedItem();
            if (auf != null) {
                this.startDate.setText(Zeitraum.formatDate(auf.getDatum().getStartzeit()));
                this.money.setValue(auf.getBetrag());
                this.to.setText(auf.getAdressat());
                this.intervall.setSelectedItem(auf.getDatum().getIntervall());
                this.verwendungszweck.setText(auf.getVerwendungszweck());
                this.categories.setSelectedItem(auf.getKategorie());
                if (auf.isIsWdh()) {
                    this.repeatTextField.setValue(auf.getDatum().getAnzahlWdh());
                }else{
                    this.untilDateTextField.setText(GUIHelper.getStringRepresantation(auf.getDatum().getEndezeit().getTime()));
                }
                if (auf.isIsWdh()) {
                    this.repeat.doClick();
                } else {
                    this.untilDate.doClick();
                }
            }
        }

        if (event.getSource() == this.startDate) {
            //TODO eventuell den JCalendar benutzen : http://www.toedter.com/en/jcalendar/
        }

        if (event.getSource() == this.repeat) {
            this.untilDateTextField.setEnabled(false);
            this.repeatTextField.setEnabled(true);
        }

        if (event.getSource() == this.untilDate) {
            this.untilDateTextField.setEnabled(true);
            this.repeatTextField.setEnabled(false);
            if (this.untilDate.isSelected()) {
            } else {
                this.untilDateTextField.setText("");
            }
        }

        if (event.getSource() == this.deleteAuftraege) {
            ((Konto) this.konten.getSelectedItem()).getDauerauftraege().remove((Dauerauftrag) this.auftraege.getSelectedItem());
            this.updateContent();
        }
    }

    @Override
    public void saveOrUpdate() {
        Konto k = (Konto) this.konten.getSelectedItem();
        Dauerauftrag d = (Dauerauftrag) this.auftraege.getSelectedItem();
        String startzeit = this.startDate.getText();
        Zeitraum.Intervall i = (Zeitraum.Intervall) this.intervall.getSelectedItem();
        double betrag = Double.parseDouble(this.money.getValue().toString());

//        JOptionPane.showMessageDialog(null, betrag, "Anmeldung fehlgeschlagen", JOptionPane.OK_OPTION);
        String adressat = this.to.getText();
        boolean bWdh = this.repeat.isSelected();
        int wdh = (Integer) this.repeatTextField.getValue();
        String endezeit = this.untilDateTextField.getText();

        if (bWdh) {
            Dauerauftrag neu = new Dauerauftrag(betrag, adressat, startzeit,
                    i, wdh, this.verwendungszweck.getText(), (Kategorie) this.categories.getSelectedItem());
            k.addDauerauftrag(neu);
            k.buchen(neu);
        } else {
            Dauerauftrag neu = new Dauerauftrag(betrag, adressat, startzeit,
                    i, endezeit, this.verwendungszweck.getText(), (Kategorie) this.categories.getSelectedItem());
            k.addDauerauftrag(neu);
            k.buchen(neu);
        }

        this.startDate.setText("");
        this.money.setText("");
        this.to.setText("");

        this.updateContent();
    }

    @Override
    public ArrayList<JPanel> getSectionPanels() {
        ArrayList<JPanel> sectionsD = new ArrayList();
        sectionsD.add(this.createStandingOrderPanel());
        return sectionsD;
    }

    @Override
    public void updateContent() {
        this.konten.setModel(new DefaultComboBoxModel(this.user.getKonten().toArray()));
        if (!this.user.getKonten().isEmpty()) {
            this.auftraege.setModel(new DefaultComboBoxModel(this.user.getKonten().get(0).getDauerauftraege().toArray()));
            this.konten.setSelectedIndex(0);
            if (this.auftraege.getModel().getSize() != 0) {
                this.auftraege.setSelectedIndex(0);
                this.setAllEnabled(true);
            } else {
                this.startDate.setEnabled(true);
                this.money.setEnabled(true);
                this.to.setEnabled(true);
                this.intervall.setEnabled(true);
                this.konten.setEnabled(true);
//                this.checkBoxNewStandingOrder.setSelected(true);
//                this.checkBoxNewStandingOrder.setEnabled(false);                
                this.auftraege.setEnabled(false);
                this.repeat.doClick();
            }
        } else {
            this.setAllEnabled(false);
//            this.checkBoxNewStandingOrder.setSelected(true);
        }
        this.categories.setModel(new DefaultComboBoxModel(this.user.getKategorien().toArray()));
        this.intervall.setModel(new DefaultComboBoxModel(Zeitraum.getIntervallEnums()));

//        this.untilDateTextField.setEnabled(false);
//        this.repeatTextField.setEnabled(true);
    }

    @Override
    public void initFields() {
        this.deleteAuftraege = new JButton("X");
        this.deleteAuftraege.addActionListener(this);

        this.verwendungszweck = new JTextArea();
        this.verwendungszweck.setPreferredSize(new Dimension(200, 100));
        this.verwendungszweck.setRows(3);
        this.categories = new JComboBox();
        this.repeat = new JRadioButton("Wiederholungen:       ");
        this.untilDate = new JRadioButton("bis zum:                     ");
        ButtonGroup group = new ButtonGroup();
        group.add(this.repeat);
        group.add(this.untilDate);
        this.repeat.addActionListener(this);
        this.untilDate.addActionListener(this);
        this.untilDateTextField = new JFormattedTextField(new Date());
        this.untilDateTextField.setPreferredSize(new Dimension(100, 28));
        this.repeatTextField = new JSpinner();
        this.repeatTextField.setPreferredSize(new Dimension(100, 28));

//        this.checkBoxNewStandingOrder = new JCheckBox("(neuen Dauerauftrag anlegen)");
//        this.checkBoxNewStandingOrder.addActionListener(this);
        this.user = GUIHelper.getInstance().getUser();
        this.intervall = new JComboBox();
        this.intervall.addActionListener(this);

        this.startDate = new JFormattedTextField(new Date());
        this.startDate.setPreferredSize(new Dimension(100, 28));
        this.startDate.addActionListener(this);
        this.money = new JFormattedTextField(new Float(0.00));
        this.money.setFormatterFactory(new JFormattedTextField.AbstractFormatterFactory() {
            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                NumberFormat format = DecimalFormat.getInstance();
                format.setMinimumFractionDigits(2);
                format.setMaximumFractionDigits(2);
                format.setRoundingMode(RoundingMode.HALF_UP);
                InternationalFormatter formatter = new InternationalFormatter(format);
                formatter.setAllowsInvalid(false);

                return formatter;
            }
        });

        this.to = new JTextField();

        this.auftraege = new JComboBox();
        this.auftraege.addActionListener(this);

        this.konten = new JComboBox();
        this.konten.addActionListener(this);
    }

}
