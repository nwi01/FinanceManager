/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.base.analysis;

/**
 *
 * @author niels
 */
public enum ChartEnum {

    PIECHART("Kreisdiagramm"),
    BARCHART("Balkendiagramm");

    private String name;

    ChartEnum(String name) {
        this.name = name;
    }
    

}
