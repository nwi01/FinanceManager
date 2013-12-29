/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.components;

import financesoftware.gui.base.ManagementBaseComponent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author niels
 */
public class CategoriesManagementComponent extends ManagementBaseComponent {

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
