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
public class USAOptimaHybridBattery extends Battery {
    
    public USAOptimaHybridBattery() {
        cells = "Dual Carbon"; 
        casing = "Kevlar"; 
        controller = "Arduino V2"; 
        charger = "110 Volts";
    }
    
}
