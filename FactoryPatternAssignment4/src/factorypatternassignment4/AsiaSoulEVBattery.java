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
public class AsiaSoulEVBattery extends Battery {
    
    public AsiaSoulEVBattery() {
        cells = "Lithium"; 
        casing = "Aluminium"; 
        controller = "Xiaomi"; 
        charger = "220 Volts";
    }
    
}
