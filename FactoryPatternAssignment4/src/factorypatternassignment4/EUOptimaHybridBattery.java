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
public class EUOptimaHybridBattery extends Battery {
    
    public EUOptimaHybridBattery() {
        cells = "Dual Carbon"; 
        casing = "Kevlar"; 
        controller = "Arduino V2"; 
        charger = "230 Volts";
    }
    
}
