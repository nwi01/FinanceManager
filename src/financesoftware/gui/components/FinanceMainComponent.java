/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.components;

import financesoftware.base.Buchung;
import financesoftware.base.CSVImport;
import financesoftware.base.Kategorie;
import financesoftware.base.Konto;
import financesoftware.gui.base.ManagementBaseComponent;
import financesoftware.gui.base.ViewComponent;
import financesoftware.tools.GUIHelper;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.InternationalFormatter;

/**
 *
 * @author niels
 */
public class FinanceMainComponent extends ManagementBaseComponent implements ViewComponent, ActionListener, ListSelectionListener {

    private JFormattedTextField currentMoney;
    private JComboBox<Konto> accounts;
    private JTable table;
    private JButton importKonto;
    // Spezielle Buchung
    private JTextField bookingDate;
    private JFormattedTextField bookingValue;
    private JTextField bookingTo;
    private JPanel userOverviewPanel;
    private JCheckBox checkBoxNewBooking;
    private JScrollPane tableScrollPane;
    private JComboBox<Kategorie> categories;
    private JTextArea verwendungszweck;

    private JPanel createInAndOut() {
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);

        GridBagConstraints cons = new GridBagConstraints();

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.anchor = GridBagConstraints.CENTER;
        cons.insets = new Insets(5, 5, 5, 5);
        JLabel label = new JLabel("Ein & Ausgaben des Kontos: ");
        label.setFont(new Font("Dialog", Font.PLAIN, 18));
        panel.add(label, cons);

        cons.gridx++;
        panel.add(this.accounts, cons);

        cons.gridy += 3;
        cons.gridx = 0;
        JPanel panel2 = new JPanel();

        JLabel label3 = new JLabel("Aktueller Kontotstand: ");
        label3.setFont(new Font("Dialog", Font.BOLD, 13));
        panel2.add(label3);
        panel2.add(this.currentMoney);

        panel.add(panel2, cons);

//        cons.gridx++;
//        panel.add(this.currentMoney, cons);
//        cons.gridx++;
//        cons.gridheight = 20;
//        cons.gridwidth = 8;
//        JSeparator sep2 = new JSeparator(JSeparator.VERTICAL);
//        sep2.setPreferredSize(new Dimension(10, 500));
//        panel.add(sep2, cons);
        cons.gridy += 2;
        cons.gridx = 0;
        cons.gridheight = 15;
        cons.gridwidth = 1;
        panel.add(tableScrollPane, cons);

        cons.gridy = 4;
        cons.gridx = 4;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        JLabel label2 = new JLabel("Buchung: ");
        label2.setFont(new Font("Dialog", Font.BOLD, 15));
        panel.add(label2, cons);

        cons.gridy++;
        panel.add(this.checkBoxNewBooking, cons);

        cons.gridy++;
        panel.add(new JLabel("Kategorie:"), cons);

        cons.gridx++;
        panel.add(this.categories, cons);

        cons.gridy++;
        cons.gridx = 4;
        panel.add(new JLabel("Datum (DD.MM.YYYY):"), cons);

        cons.gridx++;
        panel.add(this.bookingDate, cons);

        cons.gridy++;
        cons.gridx--;
        panel.add(new JLabel("Betrag:"), cons);

        cons.gridx++;
        panel.add(this.bookingValue, cons);

        cons.gridy++;
        cons.gridx--;
        panel.add(new JLabel("Adressat:"), cons);

        cons.gridx++;
        panel.add(this.bookingTo, cons);

        cons.gridx--;
        cons.gridy++;
        panel.add(new JLabel("Verwendungszweck:"), cons);

        cons.gridx++;
        cons.gridwidth = 2;
        cons.gridheight = 3;
        panel.add(this.verwendungszweck, cons);

        cons.gridy += 4;
        cons.gridx--;
        cons.gridwidth = 1;
        cons.gridheight = 1;

        panel.add(this.importKonto, cons);

        return panel;
    }

    @Override
    public void updateContent() {
        this.accounts.setModel(new DefaultComboBoxModel(this.user.getKonten().toArray()));
        if (!this.user.getKonten().isEmpty()) {
            this.accounts.setEnabled(true);
            this.accounts.setSelectedIndex(0);
            this.enableOrDisable(true);

            this.categories.setModel(new DefaultComboBoxModel(this.user.getKategorien().toArray()));
        } else {
            this.enableOrDisable(false);
        }
        this.categories.setModel(new DefaultComboBoxModel(this.user.getKategorien().toArray()));
    }

    private void enableOrDisable(boolean enable) {
        this.checkBoxNewBooking.setEnabled(enable);
        this.categories.setEnabled(enable);
        this.bookingDate.setEnabled(enable);
        this.bookingTo.setEnabled(enable);
        this.bookingValue.setEnabled(enable);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();

        if(this.table.getModel().getRowCount() > 0)
        {
        String date = (String) this.table.getModel().getValueAt(e.getFirstIndex(), 0);
        double value = Double.parseDouble((String) this.table.getModel().getValueAt(e.getFirstIndex(), 1));
        String to = (String) this.table.getModel().getValueAt(e.getFirstIndex(), 2);

        Buchung buch = GUIHelper.findBuchung(((Konto) this.accounts.getSelectedItem()).getBuchungen(), date, value, to);
        if (buch != null) {
            this.bookingDate.setText(GUIHelper.getStringRepresantation(buch.getDatum().getStartzeit().getTime()));
            this.bookingValue.setValue(value);
            this.bookingTo.setText(to);
            this.verwendungszweck.setText(buch.getVerwendungszweck());
            this.categories.setSelectedItem(buch.getKategorie());
            setCategorieByName(buch.getKategorie().getlName());
        }
        }
    }

    private void setCategorieByName(String name) {
        for (int i = 0; i < this.categories.getModel().getSize(); i++) {
            if (this.categories.getModel().getElementAt(i).getlName().equals(name)) {
                this.categories.getModel().setSelectedItem(this.categories.getModel().getElementAt(i));
            }

        }
    }

    @Override
    public void specialAction(ActionEvent event) {
        if (event.getSource() == this.accounts) {
            this.currentMoney.setValue(((Konto) this.accounts.getSelectedItem()).getAktuellerKontostand());
            this.table.setModel(new DefaultTableModel(GUIHelper.getBookingData((Konto) this.accounts.getSelectedItem()), GUIHelper.getBookingColumnName((Konto) this.accounts.getSelectedItem())));
        }

        if (event.getSource() == this.importKonto) {
            JFileChooser chooser = new JFileChooser();
            int ret = chooser.showOpenDialog(this);
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                CSVImport.Import(null, file.getPath());
                this.updateContent();
            }
        }
    }

    @Override
    public void saveOrUpdate() {
        if (this.checkBoxNewBooking.isSelected()) {
            Konto selectedKonto = (Konto) this.accounts.getSelectedItem();
            boolean saved = GUIHelper.saveNewBooking(this.bookingDate.getText(), this.bookingValue.getValue().toString(), this.bookingTo.getText(),
                    selectedKonto, (Kategorie) this.categories.getSelectedItem(), this.verwendungszweck.getText());

            this.bookingDate.setText("");
            this.bookingValue.setValue(new Float(0.00));
            this.bookingTo.setText("");
            this.verwendungszweck.setText("");
            if (saved) {
                table.setVisible(false);
                this.table.setModel(new DefaultTableModel(GUIHelper.getBookingData(selectedKonto), GUIHelper.getBookingColumnName(selectedKonto)));
                this.currentMoney.setValue(GUIHelper.getCurrentMoney(selectedKonto));
                table.setVisible(true);
//            userOverviewPanel.repaint();
            }
        }
    }

    @Override
    public ArrayList<JPanel> getSectionPanels() {
        ArrayList<JPanel> sectionsD = new ArrayList();
        sectionsD.add(this.createInAndOut());
        return sectionsD;
    }

    @Override
    public void initFields() {
        this.importKonto = new JButton("Import");
        this.importKonto.addActionListener(this);
        this.verwendungszweck = new JTextArea();
        this.verwendungszweck.setPreferredSize(new Dimension(150, 100));
        this.categories = new JComboBox(this.user.getKategorien().toArray());
        //Tabelle
        this.table = new JTable();
        this.table.setShowHorizontalLines(true);
        this.table.getSelectionModel().addListSelectionListener(this);
 
        this.tableScrollPane = new JScrollPane(table);
        this.tableScrollPane.setPreferredSize(new Dimension(500, 400));
        this.table.setFillsViewportHeight(true);

        // Spezielle Buchung
        this.bookingDate = new JFormattedTextField(new Date());
        this.bookingDate.setPreferredSize(new Dimension(100, 28));

        this.bookingValue = new JFormattedTextField(new Float(0.00));
        this.bookingValue.setFormatterFactory(new JFormattedTextField.AbstractFormatterFactory() {
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

        this.bookingTo = new JTextField();
        this.userOverviewPanel = new JPanel();
        this.currentMoney = new JFormattedTextField(new Float(0.00));
        this.currentMoney.setFormatterFactory(new JFormattedTextField.AbstractFormatterFactory() {
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
        this.currentMoney.setPreferredSize(new Dimension(100, 28));
        this.currentMoney.setEditable(false);
        this.accounts = new JComboBox(this.user.getKonten().toArray());
        this.accounts.addActionListener(
                this);
        if (!this.user.getKonten()
                .isEmpty()) {
            this.accounts.setSelectedIndex(0);
        } else {
            this.accounts.setEnabled(false);
        }

        this.checkBoxNewBooking = new JCheckBox("(neue Buchung anlegen)");

    }

}
