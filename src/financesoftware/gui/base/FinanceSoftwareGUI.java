/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.base;

import financesoftware.gui.components.AccountManagementComponent;
import financesoftware.gui.components.AnalysisComponent;
import financesoftware.gui.components.AnalysisManagementComponent;
import financesoftware.gui.components.CategoriesManagementComponent;
import financesoftware.gui.components.FinanceMainComponent;
import financesoftware.gui.components.HelpComponent;
import financesoftware.gui.components.StandingOrderManagementComponent;
import financesoftware.tools.GUIHelper;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
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
    private final ArrayList<ViewComponent> components = new ArrayList();
    private JList list;

    public FinanceSoftwareGUI() {
        super("Finanz Manager");
        // LookAndFeel
        this.setLookAndFeel();

        //Standardangaben fuer das JFrame
        this.setLocation(100, 300);
//        this.setResizable(false);
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Hinzufuegen der Menueleiste zum Frame 
        this.createMainView();

        if(!this.checkPermission()){
            System.exit(0);
        }
        this.loadComponents();

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

    }

    private JList createControlBar() {
        list = new JList();
        list.setListData(new String[]{"Konto verwalten", "Kategorien verwalten", "Daueraufträge verwalten", "Ein & Ausgabe", "Auswertung", "Auswertungen verwalten", "Hilfe", "Beenden"});
        list.setVisible(true);

        list.addMouseListener(this);

        return list;
    }

    @Override
    public void mouseClicked(MouseEvent me) {

        // Hier sollte das Event nur von einer JList kommen
        JList dummyList = (JList) me.getSource();
        int index = dummyList.getSelectedIndex();

        if (index == 7) {
            int confirm = JOptionPane.showConfirmDialog(this, "Wollen Sie das Programm wirklich beenden?", "Programm beenden", JOptionPane.OK_CANCEL_OPTION);
            if (confirm == 0) {
                System.exit(0);
            }
        } else {
            this.mainPanel.setVisible(false);
            this.mainPanel.removeAll();
            this.mainPanel.setLayout(new BorderLayout());

            mainPanel.add(this.components.get(index).getComponent());
            
            this.mainPanel.setVisible(true);
        }
    }
    
    /**
     * Erzeugen aller GUI Komponenten
     */
    private void loadComponents(){
        this.components.add(new AccountManagementComponent()); //0
        this.components.add(new CategoriesManagementComponent()); //1
        this.components.add(new StandingOrderManagementComponent()); //2
        this.components.add(new FinanceMainComponent()); //3
        this.components.add(new AnalysisComponent());
        this.components.add(new AnalysisManagementComponent());
        this.components.add(new HelpComponent()); //6
//        mainPanel.add(this.components.get(6).getComponent());
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

    private boolean checkPermission() {    
            PermissionCheck check = new PermissionCheck();

            return GUIHelper.getInstance().isUserValid();
    }
}
