package financesoftware.base;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
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
     *         null --> wenn entschluesselung fehlgeschlagen
     */
    public static User load(String uBenutzer, String uPassword)
    {
        User Load;
        
        try
        {
            FileInputStream lFile = new FileInputStream(toHexString(uBenutzer.getBytes()));
            ObjectInputStream lObjectIn = new ObjectInputStream(lFile);

            byte[] Input = new byte[lObjectIn.available()];
            int k = 0;
            try
            {
                while(k < Input.length)
                {
                    Input[k] = lObjectIn.readByte();
                    k++;
                }
            }
            catch(EOFException e)
            {
               
            }
            catch(IOException e)
            {
                return null;   
            }
            
            Load = encryptBlowfish(Input, uPassword);
            
            if(Load.getlEncryptTest().equals("FinanceManager"))
            {
                return Load;
            }
            return null;
        }
        catch(FileNotFoundException Fe)
        {
            return null;
        }
        catch(IOException Fe)
        {
            return null;
        }
    }
    
    /***
     * @MIKE: Warum muss der User +++++ pw und der user Name angegeben werden? Doppeltgemoppelt
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
        
        String lTextToDecrypt = "FinanceManager\n";
        
        try
        {
            FileOutputStream lFile = new FileOutputStream(toHexString(uBenutzer.getBytes()));
            ObjectOutputStream lObjectOut = new ObjectOutputStream(lFile);

            lObjectOut.write(decryptBlowfish(lTextToDecrypt, uPassword));
            lObjectOut.write(decryptBlowfish(uUser, uPassword));
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
    private static User encryptBlowfish(byte[] uUser, String uKey) 
    {
        try 
        {
            SecretKeySpec key = new SecretKeySpec(uKey.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return (User)ByteToObject(cipher.doFinal(uUser));
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
    private static byte[] decryptBlowfish(Object uUser, String uKey)  
    {
        try 
        {
            SecretKeySpec key = new SecretKeySpec(uKey.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(ObjectToByte(uUser));
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
    
    public static byte[] ObjectToByte(Object obj) throws IOException 
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }
    
    public static Object ByteToObject(byte[] data) throws IOException, ClassNotFoundException 
    {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }


}