package financesoftware.base;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Mike
 */
public abstract class Verschluesselung {
    //Member

    //Methoden
    /**
     * *
     *
     * @param uBenutzer
     * @param uPassword
     * @return User-Objekt null --> wenn entschluesselung fehlgeschlagen
     */
    public static User load(String uBenutzer, String uPassword)
    {
        User Load = null;

        try {
            
            String dateiname = toHexString(uBenutzer.getBytes());
            SecretKeySpec key = new SecretKeySpec(uPassword.getBytes(), "DES");

            System.out.println(key.getEncoded());
            Cipher lCipher = Cipher.getInstance("DES");
            lCipher.init(Cipher.ENCRYPT_MODE, key);
            
            FileInputStream lFile = new FileInputStream(".\\" + dateiname);
            CipherInputStream lKrypto = new CipherInputStream(lFile, lCipher);
            
            FileOutputStream lOutput = new FileOutputStream(".\\temp.temp");
            
            int len = lFile.available();
            
            if((len % 16) != 0)
            {
                len += (16 - len % 16);
            }
            
            byte[] temp = new byte[len];
            lKrypto.read(temp, 0, temp.length);
            
            lOutput.write(temp, 0, temp.length);
            
            FileInputStream lInput = new FileInputStream(".\\temp.temp");
            ObjectInputStream lObjectIn = new ObjectInputStream(lInput);
            
            try
            {
                Object tempObject = lObjectIn.readObject();
                Load = (User)tempObject;
            }
            catch(EOFException ex)
            {
                System.out.println(ex.getMessage());
            }
               
            lKrypto.close();
            lFile.close();
            
            lOutput.close();
            lObjectIn.close();
            lInput.close();
            
            /*
            //temporaere Datei loeschen
            File file = new File(".\\temp.temp");
        
            //Zuvor alle mit dem File verknuepften Streams schließen.
            if(file.exists()){
                file.delete();
            }*/
            
            Load.setPassword(Load.getPassword().trim());
            return Load;
            
            } catch (Exception e) {
                return null;
            }
    }

    /**
     * *
     *
     * @param uBenutzer
     * @param uPassword
     * @param uUser
     * @return true --> speichern hat funktioniert false --> speichern
     * fehlgeschlagen
     */
    public static boolean save(User uUser)
    {
        try
        {
            String dateiname = toHexString(uUser.getName().getBytes());
            FileOutputStream lFile = new FileOutputStream(".\\temp.temp");
            ObjectOutputStream lObjectOut = new ObjectOutputStream(lFile);
            
            lObjectOut.writeObject(uUser);

           
            
            FileInputStream lInput = new FileInputStream(".\\temp.temp");
            FileOutputStream lOutput = new FileOutputStream(".\\" + dateiname);
            
            SecretKeySpec key = new SecretKeySpec(uUser.getPassword().getBytes(), "DES");

            Cipher lCipher = Cipher.getInstance("DES");
            lCipher.init(Cipher.DECRYPT_MODE, key);
            
            CipherOutputStream lKrypto = new CipherOutputStream(lOutput, lCipher); 
            
            int len = lInput.available();
            
            if((len % 16) != 0)
            {
                len += (16 - len % 16);
            }
            
            byte[] temp = new byte[len];
            lInput.read(temp, 0, temp.length);
            //lKrypto.write(temp, 0, temp.length);
            //lKrypto.flush();
            
            for(int z = 0; z < temp.length; z++)
            {
                lKrypto.write(temp[z]);
            }

            lObjectOut.close();
            lFile.close();

            lInput.close();

            lKrypto.close();
            lOutput.close();

            /*
            //temporaere Datei loeschen
            File file = new File(".\\temp.temp");
        
            //Zuvor alle mit dem File verknuepften Streams schließen.
            if(file.exists()){
                file.delete();
            }*/
            
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * *
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
            return (User) ByteToObject(cipher.doFinal(uUser));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * *
     *
     * @param uText
     * @param uKey
     * @return
     */
    private static byte[] decryptBlowfish(Object uUser, String uKey) {
        try {
            SecretKeySpec key = new SecretKeySpec(uKey.getBytes(), "DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] BUser = ObjectToByte(uUser);
            System.out.println(BUser);
            for (int k = 0; k < BUser.length; k++) {
                if (BUser[k] == 0) {
                    System.out.println(BUser[k]);
                    BUser[k] = (byte) (97);
                } else {
                    System.out.println("null");

                }
            }
            return cipher.doFinal(BUser);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    public static String toHexString(byte[] ba) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < ba.length; i++) {
            str.append(String.format("%x", ba[i]));
        }
        return str.toString();
    }

    public static String fromHexString(String hex) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            str.append((char) Integer.parseInt(hex.substring(i, i + 2), 16));
        }
        return str.toString();
    }

    public static byte[] ObjectToByte(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        byte[] lRueckgabe = out.toByteArray();
        if ((lRueckgabe.length % 8) != 0) {
            byte[] ltemp = new byte[lRueckgabe.length + (8 - lRueckgabe.length % 8)];
            for (int i = 0; i < lRueckgabe.length; i++) {
                ltemp[i] = lRueckgabe[i];
            }
            lRueckgabe = ltemp;
        }
        return lRueckgabe;
    }

    public static Object ByteToObject(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }

}
