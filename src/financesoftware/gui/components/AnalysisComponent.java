/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.components;

import financesoftware.gui.base.ViewComponent;
import financesoftware.tools.GUIHelper;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author melanie
 */
public class AnalysisComponent extends JComponent implements ViewComponent {
GUIHelper helper = new GUIHelper();
    public AnalysisComponent() {
        super();
        GridLayout layout = new GridLayout(2, 1);
        this.setLayout(layout);
        this.add(createTopPanel());
        this.setVisible(true);
    }
    
    private JPanel createTopPanel(){
        JPanel panel = new JPanel();
        
        GridLayout layout = new GridLayout(1, 3);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        JLabel label = new JLabel("Auswertungen:");
        panel.add(label);
        
        panel.add(createAnalysisComboBox());
        panel.setVisible(true);
        return panel;
    }
    
    private JPanel createAnalysisComboBox(){
        JPanel panel = new JPanel();
        JComboBox comboBox = new JComboBox(helper.getAllAnaysisVariants());
        return panel;
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
