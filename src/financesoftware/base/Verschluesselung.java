package financesoftware.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.apache.commons.io.FileUtils;

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
            File lFile = new File("./test.crypt");

            //Verschluesselung hinzufuegen
            byte[] input = FileUtils.readFileToByteArray(lFile);
            
            //byte[] output = Crypt(input, uPassword, false);
            byte[] output = input;
            
            
            File lFile2 = new File("./temp.temp");
            
            FileUtils.writeByteArrayToFile(lFile2, output);
            
            FileInputStream lInputStream = new FileInputStream(lFile2);
            Load = JAXB.unmarshal( lFile, User.class );

            lInputStream.close();
            
            
            /*
            String dateiname = toHexString(uBenutzer.getBytes());
            SecretKeySpec key = new SecretKeySpec(getKey(uPassword).getBytes(), "DES");

            System.out.println(key.getEncoded());
            Cipher lCipher = Cipher.getInstance("DES");
            lCipher.init(Cipher.ENCRYPT_MODE, key);
            
            FileInputStream lFile = new FileInputStream("./" + dateiname);
            CipherInputStream lKrypto = new CipherInputStream(lFile, lCipher);
            
            FileOutputStream lOutput = new FileOutputStream("./temp.temp");
            
            int len = lFile.available();
            
            if((len % 16) != 0)
            {
                len += (16 - len % 16);
            }
            
            byte[] temp = new byte[len+10000];
            try
            {
                lKrypto.read(temp, 0, temp.length);
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            lOutput.write(temp, 0, temp.length);
            
            FileInputStream lInput = new FileInputStream("./temp.temp");
            ObjectInputStream lObjectIn = new ObjectInputStream(lInput);
            
            Object tempObject = lObjectIn.readObject();
            Load = (User)tempObject;
          
            lKrypto.close();
            lFile.close();
            
            lOutput.close();
            lObjectIn.close();
            lInput.close();
            
            Load.setPassword(Load.getPassword().trim());
            
            if(Load.getKonten() == null)
            {
                Load.setKonten(new ArrayList<Konto>());
            }
            
            if(Load.getKategorien() == null)
            {
                Load.setKategorien(new ArrayList<Kategorie>());
            }*/
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
            FileOutputStream lFile = new FileOutputStream("./temp.temp");
            
            JAXBContext context = JAXBContext.newInstance( User.class );
            Marshaller m = context.createMarshaller();
            m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
            m.marshal(uUser, lFile);

            //Verschluesselung hinzufuegen.
            File lFile2 = new File("./temp.temp");
            byte[] input = FileUtils.readFileToByteArray(lFile2);
            
            //byte[] output = Crypt(input, uUser.getPassword(), true);            
           byte[] output = input;
            FileUtils.writeByteArrayToFile(new File("./test.crypt"), output);
            lFile.close();
            
            
            
            /*
            
            
            String dateiname = toHexString(uUser.getName().getBytes());
            FileOutputStream lFile = new FileOutputStream("./temp.temp");
            ObjectOutputStream lObjectOut = new ObjectOutputStream(lFile);
            
            lObjectOut.writeObject(uUser);

           
            
            FileInputStream lInput = new FileInputStream("./temp.temp");
            FileOutputStream lOutput = new FileOutputStream("./" + dateiname);
            
            SecretKeySpec key = new SecretKeySpec(getKey(uUser.getPassword()).getBytes(), "DES");

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
            lKrypto.write(temp, 0, temp.length);
            lKrypto.flush();
           
            lObjectOut.close();
            lFile.close();

            lInput.close();

            lKrypto.close();
            lOutput.close();*/
            
            lFile.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param ToCrypt
     * @param uPassword
     * @param zuVerschluesseln
     * @return 
     */
    private static byte[] Crypt(byte[] ToCrypt, String uPassword, boolean zuVerschluesseln)
    {
        byte[] lPassword = uPassword.getBytes();
        byte[] lRueckgabe = ToCrypt;
        
        for(int i = 1; i <= lRueckgabe.length; i++)
        {
            if(zuVerschluesseln)
            {
                int lTmp = lRueckgabe[i-1];
                int lValue = getValue(lPassword, i);
        
                lTmp += lValue;
        
                if(lTmp < -128)
                {
                    lTmp += 255;
                }

                if(lTmp > 127)
                {
                    lTmp -= 255;
                }
                lRueckgabe[i-1] = (byte) lTmp;
            }
            else
            {
                int lTmp = lRueckgabe[i-1];
                int lValue = getValue(lPassword, i);
        
                lTmp -= lValue;
        
                if(lTmp < -128)
                {
                    lTmp += 255;
                }

                if(lTmp > 127)
                {
                    lTmp -= 255;
                }
                lRueckgabe[i-1] = (byte) lTmp;
            }
        }
        return lRueckgabe;
    }
    
    private static int getValue(byte[] uPassword, int index)
    {
        int lRueckgabe = 0;
      
        for(int i = 1; i <= uPassword.length; i++)
        {
            int q = i*i*i;
            for(int k = i; k <= uPassword.length; k++)
            {
                if((k % 5) == 0)
                {
                    q -= k*k;
                }
                else
                {
                    q += k;
                }
            }
            
            if((i % 2) == 0 || lRueckgabe > 10000)
            {
                q -= uPassword[i-1]/2;
            }
            else
            {
                q += uPassword[i-1]/2;
            }
            lRueckgabe += q;
        }
        return lRueckgabe;
    }
    
    
    private static void deleteTmp()
    {
            //temporaere Datei loeschen
            File file = new File("./temp.temp");
        
            //Zuvor alle mit dem File verknuepften Streams schließen.
            if(file.exists()){
                file.delete();
            }
    }

   public static String toHexString(byte[] ba) 
   {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < ba.length; i++) {
            str.append(String.format("%x", ba[i]));
        }
        return str.toString();
    }

    public static String fromHexString(String hex) 
    {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            str.append((char) Integer.parseInt(hex.substring(i, i + 2), 16));
        }
        return str.toString();
    }
    
    private static String getKey(String uPassword)
    {
        String lRueckgabe = uPassword;
        
        if(lRueckgabe.length() < 8)
        {
            while(lRueckgabe.length() < 8)
            {
                lRueckgabe += "a";
            }
            return lRueckgabe;
        }
        else
        {
            return lRueckgabe.substring(0, 8);
        }
    }
    
    
    
    
     private static byte[] encryptBlowfish(byte[] uUser, String uKey) 
    {
        try 
        {
            SecretKeySpec key = new SecretKeySpec(getKey(uKey).getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(uUser);
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
    private static byte[] decryptBlowfish(byte[] uUser, String uKey) {
        try {
            SecretKeySpec key = new SecretKeySpec(uKey.getBytes(), "DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] BUser = uUser;
            System.out.println(BUser);
            return cipher.doFinal(BUser);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }
}
