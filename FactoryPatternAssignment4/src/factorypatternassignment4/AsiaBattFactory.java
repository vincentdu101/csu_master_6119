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
public class AsiaBattFactory extends BattFactory {
    
    private Battery battery;
    
    @Override
    public Battery createBattery(CarModel model) {
        if (model.equals(CarModel.SOUL_EV)) {
            battery = new AsiaSoulEVBattery(); 
        } else if (model.equals(CarModel.OPTIMA_HYBRID)) {
            battery = new AsiaOptimaHybridBattery();
        } else if (model.equals(CarModel.RIO)) {
            battery = new AsiaRioBattery();
        }
        
        return battery;
    }
    
}
