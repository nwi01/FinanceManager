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
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
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
    public static User load(String uBenutzer, String uPassword) throws ClassNotFoundException
    {
        User Load;
        
        try
        {
            String dateiname = toHexString(uBenutzer.getBytes());
            FileInputStream lFile = new FileInputStream(".\\" + dateiname);
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
            
            /*Load = encryptBlowfish(Input, uPassword);
            
            if(Load.getlEncryptTest().equals("FinanceManager"))
            {
                return Load;
            }*/
            Load = (User)(ByteToObject(Input));
            
            System.out.println(Load.getName());
            
            lObjectIn.close();
            lFile.close();
            
            return Load;
        }
        catch(FileNotFoundException Fe)
        {
            return null;
        }
        catch(Exception Fe)
        {
            return null;
        }
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
        try
        {




KeyGenerator keygen = KeyGenerator.getInstance("DES");
// keygen.init(128); //this works fine!
keygen.init(56); //this breaks!
//SecretKey key = keygen.generateKey();
            
           
          
            SecretKeySpec key = new SecretKeySpec(uPassword.getBytes(), "DES");

            System.out.println(key.getEncoded());
            Cipher lCipher = Cipher.getInstance("DES");
            lCipher.init(Cipher.DECRYPT_MODE, key);
            
            String dateiname = toHexString(uBenutzer.getBytes());
            FileOutputStream lFile = new FileOutputStream(".\\" + dateiname);
            ObjectOutputStream lObjectOut = new ObjectOutputStream(lFile);
            CipherOutputStream lKrypto = new CipherOutputStream(lObjectOut, lCipher);

            lKrypto.write(ObjectToByte(uUser));
            
            lKrypto.close();
            lObjectOut.close();
            lFile.close();
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
            SecretKeySpec key = new SecretKeySpec(uKey.getBytes(), "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] BUser = ObjectToByte(uUser);
            System.out.println(BUser);
            for(int k = 0; k < BUser.length; k++)
            {
                if(BUser[k] == 0)
                {
                    System.out.println(BUser[k]);
                    BUser[k] = (byte)(97);
                }
                else
                {
                    System.out.println("null");
                    
                }
            }
            return cipher.doFinal(BUser);
        }
        catch (Exception e) 
        {
            System.out.println(e.getStackTrace());
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
        byte[] lRueckgabe = out.toByteArray();
        if((lRueckgabe.length % 8) != 0)
        {
            byte[] ltemp = new byte[lRueckgabe.length + (8 - lRueckgabe.length % 8)];
            for(int i = 0; i < lRueckgabe.length; i++)
            {
                ltemp[i] = lRueckgabe[i];
            }
            lRueckgabe = ltemp;
        }
        return lRueckgabe;
    }
    
    public static Object ByteToObject(byte[] data) throws IOException, ClassNotFoundException 
    {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }


}