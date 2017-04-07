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
public class EURioBattery extends Battery {
    
    public EURioBattery() {
        cells = "Lead"; 
        casing = "Cardboard"; 
        controller = "Raspberry Pi"; 
        charger = "230 Volts";
    }
    
}
