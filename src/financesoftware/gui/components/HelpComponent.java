package financesoftware.gui.components;

import financesoftware.gui.base.ViewComponent;
import financesoftware.tools.Helper;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;

/**
 *
 * @author nwi01
 */
public class HelpComponent extends JComponent implements ViewComponent, MouseListener {

    private JList list;
    private JPanel helpPanel;

    public HelpComponent() {
        super();

        GridLayout layout = new GridLayout(1, 2);
//        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);

        Object[] help = {"Grundlegendes", "Account", "Bla"};
        this.list = new JList(help);
        list.addMouseListener(this);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(list);
        splitPane.setRightComponent(this.createHelpText());        
        splitPane.setEnabled(false);
        
        this.add(splitPane);
              
        this.setVisible(true);
    }

    private JPanel createHelpText() {
        helpPanel = new JPanel();
        return helpPanel;
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    @Override
    public void updateContent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Holt sich den Hilfe Text aus einer Datei mit Hilfe des Helpers.
     * @param name
     * @return 
     */
    private String getHelpText(String name){                       
        return Helper.getHelpText(name);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        int index = list.getSelectedIndex();
        helpPanel.setVisible(false);
        helpPanel.removeAll();
        
        area.setText(this.getHelpText((String)list.getSelectedValue()));

        helpPanel.add(area);
        helpPanel.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
