/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.components;

import financesoftware.gui.base.ViewComponent;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author melanie
 */
public class CategoriesManagementComponent extends JComponent implements ViewComponent {

    public CategoriesManagementComponent() {
        super();
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        this.add(createCategoriesOverview());
        this.add(createAddComponent());
        this.setVisible(true);
    }
    
    private JPanel createCategoriesOverview(){
        return new JPanel();
    }
    
    private JPanel createAddComponent(){
        return new JPanel();
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    @Override
    public void updateContent() {
    }
}
