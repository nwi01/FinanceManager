/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.components;

import financesoftware.base.Konto;
import financesoftware.gui.base.ManagementBaseComponent;
import financesoftware.gui.base.ViewComponent;
import financesoftware.tools.GUIHelper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author niels
 */
public class FinanceMainComponent extends ManagementBaseComponent implements ViewComponent, ActionListener, ListSelectionListener {

    private JTextField currentMoney;
    private JComboBox<Konto> accounts;
    private JTable table = new JTable();
    // Spezielle Buchung
    private JTextField bookingDate;
    private JTextField bookingValue;
    private JTextField bookingTo;
    private JPanel userOverviewPanel;
    private JCheckBox checkBoxNewBooking;
    private JScrollPane tableScrollPane;

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

        cons.gridx++;
        cons.gridheight = 20;
        JSeparator sep2 = new JSeparator(JSeparator.VERTICAL);
        sep2.setPreferredSize(new Dimension(10, 500));
        panel.add(sep2, cons);

        cons.gridy += 3;
        cons.gridx = 0;
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

        return panel;
    }

    private JPanel createCurrentMoneyPanel() {
        JPanel panel = new JPanel();
        this.currentMoney.setText(String.valueOf(GUIHelper.getCurrentMoney((Konto) this.accounts.getSelectedItem())));
        this.currentMoney.setEditable(false);
        panel.setLayout(new GridLayout(1, 2));
        panel.setBorder(new EmptyBorder(60, 60, 60, 60));

        panel.add(new JLabel("Aktueller Kontostand: "));
        panel.add(this.currentMoney);

        return panel;
    }

    private JPanel createSpecificDatarecord() {
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
        JSeparator sep = new JSeparator(JSeparator.VERTICAL);
        main.add(sep);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(9, 9, 9, 9));
        GridLayout layout = new GridLayout(3, 1);
        panel.setLayout(layout);

        JPanel specificPanel = new JPanel();
        specificPanel.setBorder(new EmptyBorder(9, 9, 9, 9));
        GridLayout layout2 = new GridLayout(5, 2);
        specificPanel.setLayout(layout2);

//        this.bookingDate.setBorder(new EmptyBorder(30, 5, 30, 5));
        specificPanel.add(new JLabel("neue Buchung:"));
        specificPanel.add(new JLabel());
        specificPanel.add(new JLabel("Datum: "));
        specificPanel.add(this.bookingDate);
        specificPanel.add(new JLabel("Betrag: "));
        specificPanel.add(this.bookingValue);
        specificPanel.add(new JLabel("Adressat:"));
        specificPanel.add(this.bookingTo);
        specificPanel.add(new JLabel());

        panel.add(new JPanel());
        panel.add(specificPanel);

        main.add(panel);
        return main;
    }

    private JPanel createSeparator() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        panel.add(separator);

        return panel;
    }

    private String[][] getData() {
        return null;

    }

    private String[] getColumnNames() {
        return null;
    }

    @Override
    public void updateContent() {
        this.accounts.setModel(new DefaultComboBoxModel(this.user.getKonten().toArray()));
        if (!this.user.getKonten().isEmpty()) {
            this.accounts.setEnabled(true);
            this.accounts.setSelectedIndex(0);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Test");

    }

    @Override
    public void specialAction(ActionEvent event) {
        if (event.getSource() == this.accounts) {
            this.setVisible(false);
            this.table.setModel(new DefaultTableModel(GUIHelper.getBookingData((Konto) this.accounts.getSelectedItem()), GUIHelper.getBookingColumnName((Konto) this.accounts.getSelectedItem())));
            this.setVisible(true);
        }
    }

    @Override
    public void saveOrUpdate() {
        Konto selectedKonto = (Konto) this.accounts.getSelectedItem();
        boolean saved = GUIHelper.saveNewBooking(this.bookingDate.getText(), this.bookingValue.getText(), this.bookingTo.getText(), selectedKonto);

        this.bookingDate.setText("");
        this.bookingValue.setText("");
        this.bookingTo.setText("");
        if (saved) {
            table.setVisible(false);
            this.table.setModel(new DefaultTableModel(GUIHelper.getBookingData(selectedKonto), GUIHelper.getBookingColumnName(selectedKonto)));
            this.currentMoney.setText(String.valueOf(GUIHelper.getCurrentMoney(selectedKonto)));
            table.setVisible(true);
            userOverviewPanel.repaint();
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
        //Tabelle
        this.table = new JTable();
        this.table.setShowHorizontalLines(true);
        this.table.getSelectionModel().addListSelectionListener(this);
        this.tableScrollPane = new JScrollPane(table);
        this.table.setFillsViewportHeight(true);

        // Spezielle Buchung
        this.bookingDate = new JTextField();
        this.bookingDate.setPreferredSize(new Dimension(100, 28));
        this.bookingValue = new JTextField();
        this.bookingTo = new JTextField();
        this.userOverviewPanel = new JPanel();
        this.currentMoney = new JTextField();
        this.currentMoney.setPreferredSize(new Dimension(100, 28));
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
