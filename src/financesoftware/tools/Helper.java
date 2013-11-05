package financesoftware.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author nwi01
 */
public class Helper {

    public Helper() {
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
