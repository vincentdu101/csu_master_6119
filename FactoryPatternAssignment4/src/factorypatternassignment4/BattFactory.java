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
public abstract class BattFactory {
    
    public Battery orderBattery(CarModel model) {
        Battery battery = createBattery(model);
        
        battery.collectparts();
        battery.testparts();
        battery.assembleparts();
        battery.ship();
        
        return battery;
    }
    
    abstract Battery createBattery(CarModel model);
    
}
