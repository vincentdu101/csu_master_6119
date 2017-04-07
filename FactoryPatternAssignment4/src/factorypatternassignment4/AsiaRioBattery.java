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
public class AsiaRioBattery extends Battery {
    
    public AsiaRioBattery() {
        cells = "Lead"; 
        casing = "Bamboo"; 
        controller = "Raspberry Pi"; 
        charger = "220 Volts";
    }
    
}
