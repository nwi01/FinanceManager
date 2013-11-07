package financesoftware.gui.components;

import financesoftware.gui.base.ViewComponent;
import financesoftware.tools.GUIHelper;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

/**
 *
 * @author nwi01
 */
public class HelpComponent extends JComponent implements ViewComponent, MouseListener {

    private JList list;
    private JPanel helpPanel = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(helpPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    public HelpComponent() {
        super();

        GridLayout layout = new GridLayout(1, 2);
        this.setLayout(layout);

        Object[] help = {"Grundlegendes", "Account", "Bla"};
        this.list = new JList(help);
        list.addMouseListener(this);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(list);
        splitPane.setRightComponent(scrollPane);
        splitPane.setEnabled(false);
        this.add(splitPane);
        
        splitPane.setVisible(true);
        

        this.setVisible(true);
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
     *
     * @param name
     *
     * @return
     */
    private String getHelpText(String name) {
        return GUIHelper.getHelpText(name);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setText(this.getHelpText((String) list.getSelectedValue()));
        area.setVisible(true);
        
        helpPanel.setVisible(false);
        helpPanel.removeAll();       
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
