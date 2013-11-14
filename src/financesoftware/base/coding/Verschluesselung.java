package financesoftware.base.coding;

import financesoftware.base.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.math.BigInteger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author Mike
 */
public abstract class Verschluesselung 
{
    //Member
    
    //Methoden
    /***
     * 
     * @param uBenutzer
     * @param uPassword
     * @return User-Objekt
     */
    public static User load(String uBenutzer, String uPassword)
    {
        User Load = new User(uBenutzer, uPassword, null, null);
        
        try
        {
            FileReader lFileReader = new FileReader(toHexString(uBenutzer.getBytes()));
            BufferedReader lReader = new BufferedReader(lFileReader);
            
            String lTest = "";
            String crypto = "";
            while(lTest != null)
            {
                crypto += lTest;
                lTest = lReader.readLine();
            }
            
            String plainText = encryptBlowfish(crypto, uPassword);
            
            //User-Objekt erstellen und zurueck geben
        }
        catch(Exception e)
        {
            return null;
        }
        return Load;
    }
    
    /***
     * 
     * @param uBenutzer
     * @param uPassword
     * @param uUser
     * @return true --> speichern hat funktioniert
     *         false --> speichern fehlgeschlagen
     */
    public static boolean save(String uBenutzer, String uPassword, User uUser)
    {
        //Objekt als String vorbereiten.
        
        String lTextToDecrypt = "";
        String crypto = decryptBlowfish(lTextToDecrypt, uPassword);
        
        try
        {
            File lFile = new File(toHexString(uBenutzer.getBytes()));
            FileOutputStream lWriter = new FileOutputStream(lFile);
            lWriter.write(crypto.getBytes());
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }
    
    /***
     * 
     * @param uText
     * @param uKey
     * @return 
     */
    private static String encryptBlowfish(String uText, String uKey) 
    {
        try 
        {
            SecretKeySpec key = new SecretKeySpec(uKey.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return new String(cipher.doFinal(uText.getBytes()));
        }
        catch (Exception e) 
        {
            return null; 
        }
    }
 
    /***
     * 
     * @param uText
     * @param uKey
     * @return 
     */
    private static String decryptBlowfish(String uText, String uKey)  
    {
        try 
        {
            SecretKeySpec key = new SecretKeySpec(uKey.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypted = cipher.doFinal(uText.getBytes());
            return new String(decrypted);
        }
        catch (Exception e) 
        {
            return null; 
        }
    }
    
    public static String toHexString(byte[] ba) 
    {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < ba.length; i++)
        {
            str.append(String.format("%x", ba[i]));
        }
        return str.toString();
    }

    public static String fromHexString(String hex) 
    {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < hex.length(); i+=2)     
        {
            str.append((char) Integer.parseInt(hex.substring(i, i + 2), 16));
        }
        return str.toString();
    }
}