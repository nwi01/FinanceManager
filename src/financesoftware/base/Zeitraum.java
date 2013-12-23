/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Mike
 */
public class Zeitraum {

    /**
     * TODO: richtig machen
     * @param date
     * @return 
     */
    
    private Calendar lStartzeit;
    private Intervall lIntervall;
    private int lAnzahlWdh;
    private Calendar lEndezeit;
    
    
    // Konstruktor, wenn angegeben wurde "fuer ... Wochen/Tage"
    public Zeitraum(Calendar startzeit, Intervall intervall, int anzahlWdh){
        lStartzeit = startzeit;
        lIntervall = intervall;
        lAnzahlWdh = anzahlWdh;
        lEndezeit  = berechneEndezeit(startzeit, intervall, anzahlWdh);
    }
    
    // Konstruktor, wenn angegeben wurde "ausfuehren bis zum dd.mm.yyyy"
    public Zeitraum(Calendar startzeit, Intervall intervall, Calendar endezeit){
        lStartzeit = startzeit;
        lIntervall = intervall;
        lAnzahlWdh = berechneWdh(startzeit, intervall, endezeit);
        lEndezeit  = endezeit;
    }
    
    public static Calendar berechneEndezeit(Calendar startzeit, Intervall intervall, int anzahlWdh){
        int tage = 0;
        int monate = 0;
        
        switch(intervall){
            case TAEGLICH:     tage   = 1;
                break;
            case WOECHENTLICH: tage   = 7;
                break;
            case MONATLICH:    monate = 1;
                break;
            case JAEHRLICH:    monate = 12;
                break;         
        }        
        GregorianCalendar kalender = new GregorianCalendar(startzeit.get(Calendar.YEAR),
                                                           startzeit.get(Calendar.MONTH) + 1, 
                                                           startzeit.get(Calendar.DAY_OF_MONTH));        
        if(tage != 0){
            tage *= anzahlWdh;     
            
            kalender.add(Calendar.DAY_OF_MONTH, tage);
        }
        else{
            monate *= anzahlWdh;
            kalender.add(Calendar.MONTH, monate);
        }         
        return kalender;
    }
    
    
    // unvollständig
    public static int berechneWdh(Calendar startzeit, Intervall intervall, Calendar endezeit){
        return 1;
    }
  
    
    public static Calendar parseCalendar(String date){
        Calendar cal = Calendar.getInstance();
        String[] getrennt = date.split("\\.");
        int tag = Integer.parseInt(getrennt[0]);
        int monat = Integer.parseInt(getrennt[1])-1;
        int jahr = Integer.parseInt(getrennt[2]);
        cal.set(jahr,monat,tag);
        return cal;
    }
    
    public static Date parseDate(String date) {
        return new Date();
    }
    
    public enum Intervall { TAEGLICH, WOECHENTLICH, MONATLICH, JAEHRLICH; };

}
