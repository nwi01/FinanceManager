/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.components;

import financesoftware.gui.base.ViewComponent;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author melanie
 */
public abstract class BaseComponent extends JComponent implements ViewComponent {
    public JPanel mainPanel = new JPanel();
    
    
    public BaseComponent(Boolean scrollable) {
        if(scrollable){
           JScrollPane scrollPane = new JScrollPane(mainPanel);
           this.add(scrollPane);           
        }
        else{
            this.add(mainPanel);
        }        
        GridLayout layout = new GridLayout(1, 1);
        this.setLayout(layout);
        
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
}
