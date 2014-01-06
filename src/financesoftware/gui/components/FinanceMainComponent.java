/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.components;

import financesoftware.base.Konto;
import financesoftware.gui.base.ViewComponent;
import financesoftware.tools.GUIHelper;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
public class FinanceMainComponent extends JComponent implements ViewComponent, ActionListener, ListSelectionListener {

    private final JTextField currentMoney = new JTextField();
    private JComboBox<Konto> accounts = new JComboBox();
    private JTable table = new JTable();
    // Spezielle Buchung
    private final JTextField bookingDate = new JTextField();
    private final JTextField bookingValue = new JTextField();
    private final JTextField bookingTo = new JTextField();
    private final JButton saveB = new JButton("Speichern");
    private JPanel userOverviewPanel = new JPanel();

    public FinanceMainComponent() {

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        Konto[] konten = GUIHelper.getAllBankAccounts();
        if (konten != null) {
            accounts = new JComboBox(konten);
            table = new JTable(GUIHelper.getBookingData((Konto) this.accounts.getSelectedItem()), GUIHelper.getBookingColumnName((Konto) this.accounts.getSelectedItem()));
        }

        this.add(createInAndOutTable());
        this.add(createSpecificDatarecord());

        this.setVisible(true);
    }

    private JPanel createInAndOutTable() {
        userOverviewPanel.setBorder(new EmptyBorder(9, 9, 9, 9));
        // Layout
        userOverviewPanel.setLayout(new BoxLayout(userOverviewPanel, BoxLayout.Y_AXIS));

        table.setShowHorizontalLines(true);
        this.table.getSelectionModel().addListSelectionListener(this);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        userOverviewPanel.add(createAccountSelect());
        userOverviewPanel.add(scrollPane);
        userOverviewPanel.add(createCurrentMoneyPanel());
        return userOverviewPanel;
    }

    private JPanel createAccountSelect() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Ein & Ausgaben des Kontos: ");
        label.setFont(new Font("Dialog", Font.PLAIN, 18));

        GridLayout layout = new GridLayout(1, 2);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(60, 5, 60, 5));

        this.accounts.addActionListener(this);

        panel.add(label);
        panel.add(this.accounts);

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

        this.saveB.addActionListener(this);
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
        specificPanel.add(this.saveB);

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
    public JComponent getComponent() {
        return this;
    }

    @Override
    public void updateContent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.accounts) {
            this.setVisible(false);
            this.table = new JTable(GUIHelper.getBookingData((Konto) this.accounts.getSelectedItem()), GUIHelper.getBookingColumnName((Konto) this.accounts.getSelectedItem()));
            this.setVisible(true);
        }
        if (e.getSource() == this.saveB) {
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
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Test");

    }

}
