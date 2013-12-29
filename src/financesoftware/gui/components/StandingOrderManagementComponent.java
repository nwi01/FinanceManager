package financesoftware.gui.components;

import financesoftware.gui.base.ManagementBaseComponent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author niels
 */
public class StandingOrderManagementComponent extends ManagementBaseComponent {

    public StandingOrderManagementComponent(){
        super();
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

    @Override
    public void specialAction(ActionEvent event) {
       
    }

    @Override
    public void saveOrUpdate() {
        
    }

    @Override
    public ArrayList<JPanel> getSectionPanels() {
                ArrayList<JPanel> sectionsD = new ArrayList();
        JPanel panel = new JPanel();
        panel.add(new JLabel("efefe"));

//        this.mainPanel.add(panel, BorderLayout.CENTER);
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("efefe2222222"));
        sectionsD.add(panel);
        sectionsD.add(panel2);
        return sectionsD;
    }

    @Override
    public void initFields() {
     
    }
    
}
