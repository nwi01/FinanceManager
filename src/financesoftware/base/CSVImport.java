/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package financesoftware.base;

import financesoftware.tools.GUIHelper;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;

/**
 *
 * @author Mike
 */
public class CSVImport 
{
    public static boolean Import(String uBank, String uPath)
    {
        List<HilfsBuchung> lRueckgabe;
        if(uBank == null){
            File file = new File(uPath);
            if(file.getName().contains("PB")){
                uBank = "PB";
            }
            if(file.getName().contains("KSK")){
                uBank = "KSK";
            }
            if(file.getName().contains("VB")){
                uBank = "VB";
            }
            if(uBank == null){
                uBank = "PB";
            }
            
        }
        switch(uBank)
        {
            case "KSK": lRueckgabe = KSKImport(uPath); break;
            case "PB": lRueckgabe = PBImport(uPath); break;
            case "VB": lRueckgabe = VBImport(uPath); break;
            default: return false;
        }
        
       if(lRueckgabe != null && lRueckgabe.size() > 0)
       {
            User laktUser = GUIHelper.getInstance().getUser();
            List<Konto> lKonten = laktUser.getKonten();
        
            for(HilfsBuchung lHilfsBuchung : lRueckgabe)
            {
                for(int i = 0; i < lKonten.size(); i++)
                {
                    if(lKonten.get(i).getKontoNr().equals(lHilfsBuchung.getAuftragskonto()))
                    {
                        lKonten.get(i).addBuchung(lHilfsBuchung.getBuchung());
                    }
                }
            }
       }
       else
       {
           return false;
       }
        return true;
    }
    
    private static List<HilfsBuchung> KSKImport(String uFile)
    {
        List<HilfsBuchung> lRueckgabe = new ArrayList<>();
        CsvListReader csvParser;
        
        try
        {
            List<String> lEintrag;
            csvParser = new CsvListReader(new FileReader(uFile), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
            
            if((lEintrag = csvParser.read()) != null && lEintrag.size() > 8)
            {
                while((lEintrag = csvParser.read()) != null)
                {
                    String lBetrag = lEintrag.get(8);
                    if(lBetrag.contains(","))
                    {
                        lBetrag = lBetrag.replace(",", ".");
                    }
                    lRueckgabe.add(new HilfsBuchung(Double.parseDouble(lBetrag), lEintrag.get(5), lEintrag.get(2), lEintrag.get(0), lEintrag.get(4)));
                }
            }
            return lRueckgabe;
        }
        catch(IOException e)
        {
            return null;
        } 
    }
    
    private static List<HilfsBuchung> PBImport(String uFile)
    {
        List<HilfsBuchung> lRueckgabe = new ArrayList<>();
        CsvListReader csvParser;
            
        try
        {
            List<String> lEintrag;
            csvParser = new CsvListReader(new FileReader(uFile), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
            
            if((lEintrag = csvParser.read()) != null && lEintrag.size() > 8)
            {
                while((lEintrag = csvParser.read()) != null)
                {
                    lRueckgabe.add(new HilfsBuchung(Double.parseDouble(lEintrag.get(8)), lEintrag.get(5), lEintrag.get(2), lEintrag.get(0), lEintrag.get(4)));
                }
            }
            return lRueckgabe;
        }
        catch(IOException e)
        {
            return null;
        } 
    }
    
    private static List<HilfsBuchung> VBImport(String uFile)
    {
        List<HilfsBuchung> lRueckgabe = new ArrayList<>();
        CsvListReader csvParser;
        
        try
        {
            List<String> lEintrag;
            csvParser = new CsvListReader(new FileReader(uFile), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
            
            if((lEintrag = csvParser.read()) != null && lEintrag.size() > 8)
            {
                while((lEintrag = csvParser.read()) != null)
                {
                    String lVerwendungszweck = "";
                    for(int k = 4; k < 18; k++)
                    {
                        lVerwendungszweck += lEintrag.get(k) + "\n";
                    }
                    
                    String lBetrag = lEintrag.get(19);
                    if(lBetrag.contains(","))
                    {
                        lBetrag = lBetrag.replace(",", ".");
                    }
                    
                    lRueckgabe.add(new HilfsBuchung(Double.parseDouble(lBetrag), lEintrag.get(3), lEintrag.get(1), lEintrag.get(0), lVerwendungszweck));
                }
            }
            return lRueckgabe;
        }
        catch(IOException e)
        {
           return null;
        } 
    }
 }

/**
 * 
 * @author Mike
 */
class HilfsBuchung
{
    Buchung lBuchung;
    String lAuftragskonto = "";
    
    public HilfsBuchung(double Betrag, String Adressat, Zeitraum datum, String Auftragskonto, String Verwendungszweck) 
    {
        lBuchung = new Buchung(Betrag, Adressat, datum, Verwendungszweck);
        lAuftragskonto = Auftragskonto;
    }
    
    public HilfsBuchung(double Betrag, String Adressat, String datum, String Auftragskonto, String Verwendungszweck) 
    {
        lBuchung = new Buchung(Betrag, Adressat, datum, Verwendungszweck);
        lAuftragskonto = Auftragskonto;
    }
    
    /**
     * 
     * @return 
     */
    public String getAuftragskonto()
    {
        return lAuftragskonto;
    }
    
    /**
     * 
     * @return 
     */
    public Buchung getBuchung()
    {
        return lBuchung;
    }
}
