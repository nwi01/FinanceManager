package financesoftware.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Mike
 */
public abstract class Verschluesselung {

    /**
     * *
     *
     * @param uBenutzer
     * @param uPassword
     * @return User-Objekt null --> wenn entschluesselung fehlgeschlagen
     */
    public static User load(String uBenutzer, String uPassword) {
        User Load = null;

        try {
            String dateiname = "./Benutzer/" + toHexString(uBenutzer.getBytes());
            File lFile = new File(dateiname);

            //Verschluesselung hinzufuegen
            byte[] input = FileUtils.readFileToByteArray(lFile);
            byte[] output = Crypt(input, uPassword, false);

            File lFile2 = new File("./temp.temp");

            FileUtils.writeByteArrayToFile(lFile2, output);

            FileInputStream lInputStream = new FileInputStream("./temp.temp");
            Load = JAXB.unmarshal(lInputStream, User.class);

            lInputStream.close();
            deleteTmp();
            return Load;
        } catch (Exception e) {
            e.printStackTrace();
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
    public static boolean save(User uUser) {
        try {
            String dateiname = "./Benutzer/" + toHexString(uUser.getName().getBytes());
            FileOutputStream lFile = new FileOutputStream("./temp.temp");

            JAXBContext context = JAXBContext.newInstance(User.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(uUser, lFile);

            //Verschluesselung hinzufuegen.
            File lFile2 = new File("./temp.temp");
            byte[] input = FileUtils.readFileToByteArray(lFile2);
            byte[] output = Crypt(input, uUser.getPassword(), true);

            FileUtils.writeByteArrayToFile(new File(dateiname), output);
            lFile.close();
            deleteTmp();
        } catch (Exception e) {
            e.printStackTrace();
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
    private static byte[] Crypt(byte[] ToCrypt, String uPassword, boolean zuVerschluesseln) {
        byte[] lPassword = uPassword.getBytes();
        byte[] lRueckgabe = ToCrypt;

        for (int i = 1; i <= lRueckgabe.length; i++) {
            if (zuVerschluesseln) {
                int lTmp = lRueckgabe[i - 1];
                int lValue = getValue(lPassword, i);

                lTmp += lValue;

                if (lTmp < -128) {
                    lTmp += 255;
                }

                if (lTmp > 127) {
                    lTmp -= 255;
                }
                lRueckgabe[i - 1] = (byte) lTmp;
            } else {
                int lTmp = lRueckgabe[i - 1];
                int lValue = getValue(lPassword, i);

                lTmp -= lValue;

                if (lTmp < -128) {
                    lTmp += 255;
                }

                if (lTmp > 127) {
                    lTmp -= 255;
                }
                lRueckgabe[i - 1] = (byte) lTmp;
            }
        }
        return lRueckgabe;
    }

    /**
     *
     * @param uPassword
     * @param index
     * @return
     */
    private static int getValue(byte[] uPassword, int index) {
        int lRueckgabe = 0;

        for (int i = 1; i <= uPassword.length; i++) {
            int q = i * i * i;
            for (int k = i; k <= uPassword.length; k++) {
                if ((k % 5) == 0) {
                    q -= k * k;
                } else {
                    q += k;
                }
            }

            if ((i % 2) == 0 || lRueckgabe > 10000) {
                q -= uPassword[i - 1] / 2;
            } else {
                q += uPassword[i - 1] / 2;
            }
            lRueckgabe += q;
        }
        return lRueckgabe;
    }

    /**
     *
     */
    private static void deleteTmp() {
        //temporaere Datei loeschen
        File file = new File("./temp.temp");

        //Zuvor alle mit dem File verknuepften Streams schließen.
        if (file.exists()) {
            file.delete();
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
}
