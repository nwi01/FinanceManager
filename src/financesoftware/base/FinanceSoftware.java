//Erstellt am 31.10.2013
package financesoftware.base;

import financesoftware.gui.base.FinanceSoftwareGUI;
import financesoftware.gui.base.PermissionCheck;

/**
 * @author Niels Willig
 * @author Mike Mertens
 * @author Kristina Vieten
 */
public class FinanceSoftware {    

    public static void main(String[] args) { 
        // Ueberpruefung der Daten
        PermissionCheck check = new PermissionCheck();
        check.checkPermission();
        
        FinanceSoftwareGUI lGUI = new FinanceSoftwareGUI();
        lGUI.setVisible(true);
    }
}