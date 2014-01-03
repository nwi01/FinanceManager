/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package financesoftware.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    public static List<Buchung> Import(String uBank, String uPath)
    {
        List<Buchung> lRueckgabe = new ArrayList<Buchung>();
        switch(uBank)
        {
            case "KSK": lRueckgabe = KSKImport(uPath); break;
            case "PB": lRueckgabe = PBImport(uPath); break;
            case "VB": lRueckgabe = VBImport(uPath); break;
            default: lRueckgabe = null; break;
        }
        return lRueckgabe;
    }
    
    private static List<Buchung> KSKImport(String uFile)
    {
        List<Buchung> lRueckgabe = new ArrayList<Buchung>();
        
        CsvListReader csvParser = null;
        try
        {
            List<String> lEintrag;
            csvParser = new CsvListReader(new FileReader(uFile), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
            
            if((lEintrag = csvParser.read()) != null && lEintrag.size() > 8)
            {
                while((lEintrag = csvParser.read()) != null)
                {
                    lRueckgabe.add(new Buchung(Double.parseDouble(lEintrag.get(8)), lEintrag.get(5), lEintrag.get(2)));
                }
            }
            return lRueckgabe;
        }
        catch(IOException e)
        {
            return lRueckgabe;
        } 
    }
    
    private static List<Buchung> PBImport(String uFile)
    {
        List<Buchung> lRueckgabe = new ArrayList<Buchung>();
        
        CsvListReader csvParser = null;
        try
        {
            List<String> lEintrag;
            csvParser = new CsvListReader(new FileReader(uFile), CsvPreference.STANDARD_PREFERENCE);
            
            while((lEintrag = csvParser.read()) != null)
            {
                lRueckgabe.add(new Buchung(Double.parseDouble(lEintrag.get(8)), lEintrag.get(5), lEintrag.get(1)));
            }
            return lRueckgabe;
        }
        catch(IOException e)
        {
            return lRueckgabe;
        } 
    }
    
    private static List<Buchung> VBImport(String uFile)
    {
       List<Buchung> lRueckgabe = new ArrayList<Buchung>();
        
        CsvListReader csvParser = null;
        try
        {
            List<String> lEintrag;
            csvParser = new CsvListReader(new FileReader(uFile), CsvPreference.STANDARD_PREFERENCE);
            
            while((lEintrag = csvParser.read()) != null)
            {
                lRueckgabe.add(new Buchung(Double.parseDouble(lEintrag.get(8)), lEintrag.get(5), lEintrag.get(1)));
            }
            return lRueckgabe;
        }
        catch(IOException e)
        {
            return lRueckgabe;
        } 
    }
 }
