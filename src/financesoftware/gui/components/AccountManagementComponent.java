/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.components;

import financesoftware.gui.base.ViewComponent;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author melanie
 */
public class AccountManagementComponent extends JComponent implements ViewComponent {

    private JPanel parentPanel;

    public AccountManagementComponent(JPanel parentPanel) {
        super();
        this.parentPanel = parentPanel;

//        GridLayout layout = new GridLayout(1, 2);
//        FlowLayout layout = new FlowLayout();
//        this.setLayout(layout);
//        this.setBorder(new EmptyBorder(9, 9, 9, 9));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.add(createUserOverview());
        this.add(createSeparator());
        this.add(createSpecificDatarecord());

        this.setVisible(true);
    }

    private JPanel createSeparator() {
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(1, 1);
        panel.setLayout(layout);
        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        panel.add(separator);

        return panel;
    }

    private JPanel createUserOverview() {
        JPanel userOverviewPanel = new JPanel();
        userOverviewPanel.setBorder(new EmptyBorder(9, 9, 9, 9));
        // Layout
        GridLayout layout = new GridLayout(3, 1);
        userOverviewPanel.setLayout(layout);

        JLabel label = new JLabel("Übersicht");
        label.setFont(new Font("Dialog", Font.PLAIN, 18));

        userOverviewPanel.add(label);
//        userOverviewPanel.setVisible(true);

        JTable table = new JTable(getData(), getColumnNames());
        table.getAutoCreateRowSorter();

        userOverviewPanel.add(table);
        return userOverviewPanel;
    }

    private JPanel createSpecificDatarecord() {
        JPanel specificPanel = new JPanel();
        specificPanel.setBorder(new EmptyBorder(9, 9, 9, 9));
        GridLayout layout = new GridLayout(3, 1);
        specificPanel.setLayout(layout);

        specificPanel.add(new JLabel("Test"));
        return specificPanel;
    }

    private Object[][] getData() {
        Object[][] data
                = {
                    {"Niels", "efeff", "efefe"},
                    {"Mike", "efef", "fefolfij"}
                };

        return data;
    }

    private Object[] getColumnNames() {
        return new Object[]{"Name", "Test1", "Test2"};
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    @Override
    public void updateContent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
