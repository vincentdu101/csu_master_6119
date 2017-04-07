/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorypatternassignment4;

/**
 *
 * @author vincentdu
 */
public class EUSoulEVBattery extends Battery {
    
    public EUSoulEVBattery() {
        cells = "Lithium"; 
        casing = "Aluminium"; 
        controller = "Arduino"; 
        charger = "230 Volts";
    }
    
}
