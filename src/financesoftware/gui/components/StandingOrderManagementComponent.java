package financesoftware.gui.components;

import financesoftware.base.Dauerauftrag;
import financesoftware.base.Kategorie;
import financesoftware.base.Konto;
import financesoftware.base.User;
import financesoftware.base.Zeitraum;
import financesoftware.gui.base.ManagementBaseComponent;
import financesoftware.tools.GUIHelper;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author niels
 */
public class StandingOrderManagementComponent extends ManagementBaseComponent {

    private JComboBox<Konto> konten;
    private JComboBox<Dauerauftrag> auftraege;
    private JCheckBox checkBoxNewStandingOrder;
    private JTextField startDate;
    private JComboBox intervall;
    private JTextField money;
    private JTextField to;
    private JRadioButton repeat;
    private JRadioButton untilDate;
    private JTextField untilDateTextField;
    private JSpinner repeatTextField;
    private JComboBox<Kategorie> categories;

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

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(5, 5, 20, 5);
        panel.add(this.checkBoxNewStandingOrder, constraints);

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

        constraints.gridy++;
        constraints.gridx = 0;
        panel.add(new JLabel("Betrag:"), constraints);

        constraints.gridx++;
        panel.add(this.money, constraints);

        return panel;
    }

    @Override
    public void updateContent() {
        this.konten.setModel(new DefaultComboBoxModel(this.user.getKonten().toArray()));
        if (!this.user.getKonten().isEmpty()) {
            if (this.auftraege.getModel().getSize() != 0) {
                this.setAllEnabled(true);
            } else {
                this.startDate.setEnabled(true);
                this.money.setEnabled(true);
                this.to.setEnabled(true);
                this.intervall.setEnabled(true);
                this.konten.setEnabled(true);
            }
        } else {
            this.setAllEnabled(false);
            this.checkBoxNewStandingOrder.setSelected(true);
        }
        this.categories.setModel(new DefaultComboBoxModel(this.user.getKategorien().toArray()));
    }

    private void setAllEnabled(boolean isEnabled) {
        this.checkBoxNewStandingOrder.setEnabled(isEnabled);
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
            this.auftraege.setModel(new DefaultComboBoxModel(((Konto) this.konten.getSelectedItem()).getDauerauftraege().toArray()));
            if (!((Konto) this.konten.getSelectedItem()).getDauerauftraege().isEmpty()) {
                this.auftraege.setSelectedIndex(0);
            } else {
                this.checkBoxNewStandingOrder.setSelected(true);
            }
        }

        if (event.getSource() == this.auftraege) {
            Dauerauftrag auf = (Dauerauftrag) this.auftraege.getSelectedItem();
            if (auf != null) {
                this.startDate.setText(auf.getDatum().toString());
                this.money.setText(auf.getBetrag() + "");
                this.to.setText(auf.getAdressat());
                this.intervall.setSelectedItem(auf.getDatum().getIntervall());
            }
        }

        if (event.getSource() == this.startDate) {
            //TODO eventuell den JCalendar benutzen : http://www.toedter.com/en/jcalendar/
        }

        if (event.getSource() == this.checkBoxNewStandingOrder) {
            if (!this.checkBoxNewStandingOrder.isSelected()) {
                this.startDate.setText("");
                this.money.setText("");
                this.to.setText("");
            } else {
                this.auftraege.setSelectedIndex(this.auftraege.getSelectedIndex());
            }
        }

        if (event.getSource() == this.repeat) {
            this.untilDateTextField.setEnabled(false);
            this.repeatTextField.setEnabled(true);
        }

        if (event.getSource() == this.untilDate) {
            this.untilDateTextField.setEnabled(true);
            this.repeatTextField.setEnabled(false);
        }
    }

    @Override
    public void saveOrUpdate() {
        Konto k = (Konto) this.konten.getSelectedItem();
        Dauerauftrag d = (Dauerauftrag) this.auftraege.getSelectedItem();
        String startzeit = this.startDate.getText();
        Zeitraum.Intervall i = (Zeitraum.Intervall) this.intervall.getSelectedItem();
        double betrag = Double.parseDouble(this.money.getText());
        String adressat = this.to.getText();
        boolean bWdh = this.repeat.isSelected();
        int wdh = (Integer) this.repeatTextField.getValue();
        String endezeit = this.untilDateTextField.getText();

        if (this.checkBoxNewStandingOrder.isSelected()) {
            if (bWdh) {
                Dauerauftrag neu = new Dauerauftrag(betrag, adressat, startzeit,
                        i, wdh, "verwendung", (Kategorie) this.categories.getSelectedItem());
                k.addDauerauftrag(neu);
            } else {
                Dauerauftrag neu = new Dauerauftrag(betrag, adressat, startzeit,
                        i, endezeit, "verwendung", (Kategorie) this.categories.getSelectedItem());
                k.addDauerauftrag(neu);
            }
        }
        // else Update
    }

    @Override
    public ArrayList<JPanel> getSectionPanels() {
        ArrayList<JPanel> sectionsD = new ArrayList();
        sectionsD.add(this.createStandingOrderPanel());
        return sectionsD;
    }

    @Override
    public void initFields() {
        this.categories = new JComboBox(this.user.getKategorien().toArray());
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

        this.checkBoxNewStandingOrder = new JCheckBox("(neuen Dauerauftrag anlegen)");
        this.checkBoxNewStandingOrder.addActionListener(this);

        this.user = GUIHelper.getInstance().getUser();
        this.intervall = new JComboBox(Zeitraum.getIntervallEnums());
        this.intervall.addActionListener(this);

        this.startDate = new JFormattedTextField(new Date());
        this.startDate.setPreferredSize(new Dimension(100, 28));
        this.startDate.addActionListener(this);
        this.money = new JFormattedTextField(NumberFormat.getNumberInstance());
        this.to = new JTextField();

        this.auftraege = null;

        this.konten = new JComboBox(this.user.getKonten().toArray());
        this.konten.addActionListener(this);
        if (!this.user.getKonten().isEmpty()) {
            this.auftraege = new JComboBox(this.user.getKonten().get(0).getDauerauftraege().toArray());
            this.konten.setSelectedIndex(0);
        } else {
            this.auftraege = new JComboBox();
            this.setAllEnabled(false);
            this.checkBoxNewStandingOrder.setSelected(true);
        }

        this.auftraege.addActionListener(this);

        this.untilDateTextField.setEnabled(false);
        this.repeatTextField.setEnabled(true);
    }

}
