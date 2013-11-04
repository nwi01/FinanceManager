/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.Components;

import financesoftware.gui.base.ViewComponent;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author melanie
 */
public class AccountManagementComponent extends JComponent implements ViewComponent{
    
    public AccountManagementComponent(){
        super();
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        this.add(createUserOverview());
        this.setVisible(true);
    }
    
    private JPanel createUserOverview(){
        JPanel panel = new JPanel();
        // Layout
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        
        panel.add(new JLabel("Übersicht"), BoxLayout.LINE_AXIS);
        panel.setVisible(true);
        return panel;
    }

    @Override
    public JComponent getComponent() {
        return new JLabel("rgrg");
    }

    @Override
    public void updateContent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
