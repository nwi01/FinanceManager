/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.base;

import financesoftware.gui.components.AccountManagementComponent;
import financesoftware.gui.components.CategoriesManagementComponent;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

/**
 *
 * @author melanie
 */
public class FinanceSoftwareGUI extends JFrame implements MouseListener {

    private final JPanel mainPanel = new JPanel();
    private JFrame mainFrame;

    public FinanceSoftwareGUI() {
        super("Finanz Software");
        // LookAndFeel
        this.setLookAndFeel();

        //Standardangaben fuer das JFrame
        this.setLocation(100, 300);
        this.setResizable(true);
        this.setSize(1000, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainFrame = this;
        JLabel label = new JLabel("efeffe");
        this.mainPanel.setLayout(new BorderLayout());
        mainPanel.add(label);
        this.mainPanel.setVisible(true);

//        // Grundlegendes Layout
//        BorderLayout layout = new BorderLayout();
//        this.setLayout(layout);
        //Hinzufuegen der Menueleiste zum Frame 
        this.createMainView();

        this.setVisible(true);
    }

    private void setLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
    }

    private void createMainView() {
        // SplitPane erzeugen
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setRightComponent(this.mainPanel);
        splitPane.setLeftComponent(this.createControlBar());
        splitPane.setEnabled(false);
        this.add(splitPane);
//        splitPane.setRightComponent();

    }

    private JList createControlBar() {
        final JList list = new JList();
        list.setListData(new String[]{"Konto verwalten", "Kategorien verwalten", "Daueraufträge verwalten", "Ein & Ausgabe", "Auswertung", "Auswertungen verwalten", "Hilfe", "Beenden"});
        list.setVisible(true);

        list.addMouseListener(this);

        return list;
    }

    @Override
    public void mouseClicked(MouseEvent me) {

        // Hier sollte das Event nur von einer JList kommen
        JList list = (JList) me.getSource();
        int index = list.getSelectedIndex();

        if (index == 7) {
            int confirm = JOptionPane.showConfirmDialog(this, "Wollen Sie das Programm wirklich beenden?","Programm beenden", JOptionPane.OK_CANCEL_OPTION);
            if (confirm == 0) {
                System.exit(0);
            }
        } else {
            this.mainPanel.setVisible(false);
            this.mainPanel.removeAll();
            this.mainPanel.setLayout(new BorderLayout());

            switch (index) {
                case 0: {
                    AccountManagementComponent component = new AccountManagementComponent();
                    mainPanel.add(component.getComponent(), BorderLayout.CENTER);
                    break;
                }
                case 1: {
                    mainPanel.add(new CategoriesManagementComponent().getComponent());
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    break;
                }
                case 5: {
                    break;
                }
                case 6: {
                    break;
                }
            }
            this.mainPanel.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
