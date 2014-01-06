/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.base;

import javax.swing.JComponent;

/**
 *
 * @author melanie
 */
public interface ViewComponent {
    JComponent getComponent();

    void updateContent();
}
