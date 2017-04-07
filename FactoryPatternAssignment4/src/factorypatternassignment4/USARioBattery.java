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
public class USARioBattery extends Battery {
    
    public USARioBattery() {
        cells = "Lead"; 
        casing = "Particleboard"; 
        controller = "Raspberry Pi"; 
        charger = "110 Volts";
    }
    
}
