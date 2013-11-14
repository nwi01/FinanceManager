package financesoftware.tools;

import financesoftware.base.Kategorie;
import financesoftware.base.Konto;
import financesoftware.base.Zeitraum;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author nwi01
 */
public class GUIHelper {

    public GUIHelper() {
    }
    
    /**
     * Gibt ein Array zurueck, welches die verschiedenen, 
     * vom Benutzer angelegten Auswertungen beinhaltet.
     * Muss/ Sollte nicht unbedingt ein String[] sein, keine Ahnung was die JComboBox da verarbeiten kann. 
     * @return 
     */
    public String[] getAllAnaysisVariants(){
        return new String[]{};
    }
    
    /**
     * Uebergabeparameter noch nicht sicher
     * Diese Methode soll ein CompareAnalysis Objekt erzeugen und speichern.
     */
    public void createAndSaveCompareAnalysis(String name,Zeitraum zeitraum, List<Kategorie> kategorien, List<Konto> konten){
        
    }
    
    /**
     * Uebergabeparameter noch nicht sicher
     * Diese Methode soll ein ChartAnalysis Objekt erzeugen und speichern.
     */
    public void createAndSaveChartAnalysis(String name,Zeitraum zeitraum,  List<Kategorie> kategorien, List<JFreeChart> charts){
        
    }

    public static String getHelpText(String name) {
        String helpText = "";
        File file = new File("./src/data/helpTexts/" + name + ".txt");
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                helpText += sc.nextLine() + "\n";
            }
        }
        catch(Exception ex){
            return "Keinen passenden Hilfetext gefunden.";
        }
        
        return helpText;
    }
}
