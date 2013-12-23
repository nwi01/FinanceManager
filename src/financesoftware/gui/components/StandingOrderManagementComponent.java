package financesoftware.gui.components;

import financesoftware.gui.base.ViewComponent;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

/**
 *
 * @author niels
 */
public class StandingOrderManagementComponent extends JComponent implements ViewComponent {

    public StandingOrderManagementComponent(){
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setVisible(true);
    }
    
    @Override
    public JComponent getComponent() {
       return this;
    }

    @Override
    public void updateContent() {
        
    }
    
}
