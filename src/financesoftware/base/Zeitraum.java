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
    
    // Konstruktor für einfache Buchung
    public Zeitraum(Calendar startzeit){
        lStartzeit = startzeit;
        lIntervall = null;
        lAnzahlWdh = 0;
        lEndezeit  = startzeit;
    }
    
    public static int[] IntervallInTage(Intervall intervall){
        int a[] = {0,0};
        
        switch(intervall){
            case TAEGLICH:     a[0]   = 1;
                break;
            case WOECHENTLICH: a[0]   = 7;
                break;
            case MONATLICH:    a[1]   = 1;
                break;
            case JAEHRLICH:    a[1]   = 12;
                break;         
        }         
        return a;
    }
        
    public static Calendar berechneEndezeit(Calendar startzeit, Intervall intervall, int anzahlWdh){
        int tmp[] = IntervallInTage(intervall);
        int tage = tmp[0];
        int monate = tmp[1];        
               
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
       
    public static int berechneWdh(Calendar startzeit, Intervall intervall, Calendar endezeit){
        int tmp[] = IntervallInTage(intervall);
        int tage = tmp[0];
        int monate = tmp[1]; 
        
        int wdh = 0;
        
        GregorianCalendar kalender = new GregorianCalendar(startzeit.get(Calendar.YEAR),
                                                           startzeit.get(Calendar.MONTH), 
                                                           startzeit.get(Calendar.DAY_OF_MONTH)); 
        
        while(kalender.compareTo(endezeit) <= 0){
            wdh++;            
            if(tage != 0){
                kalender.add(Calendar.DAY_OF_MONTH, tage);
            }
            else{
                kalender.add(Calendar.MONTH, monate);
            }
        }
        return wdh;
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
    
    /**
     * Gibt alle Enums zurueck
     * @return 
     */
    public static Intervall[] getIntervallEnums(){
        return Intervall.values();        
    }
    
    /**
     * TODO:
     * DIese methode macht aus einem String ein Zeitraum Objekt
     * @param date
     * @return 
     */
    public static Zeitraum parseDate(String date) {
        return new Zeitraum(null, Intervall.TAEGLICH, null); // Dummy
    }

    /**
     * @return the lIntervall
     */
    public Intervall getIntervall() {
        return lIntervall;
    }

    /**
     * @param lIntervall the lIntervall to set
     */
    public void setIntervall(Intervall lIntervall) {
        this.lIntervall = lIntervall;
    }
    
    
    /**
     * 
     * TODO ordentlich machen!
     * @return 
     */
    public String toString(){
        // Ausgabe DD.MM.YYYY bitte
        return this.lStartzeit.getTime().toString() + "Intervall: " + this.lIntervall.toString();
    }
    
    
    
    public enum Intervall { TAEGLICH, WOECHENTLICH, MONATLICH, JAEHRLICH; };

}
