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
public class FactoryPatternAssignment4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BattFactory usaBattFactory = new USABattFactory();
        BattFactory asiaBattFactory = new AsiaBattFactory();
        
        System.out.println("Ordering USA Battery");
        Battery battery = usaBattFactory.orderBattery(CarModel.SOUL_EV);
        System.out.println("Ordering Asia Battery");
        battery = asiaBattFactory.orderBattery(CarModel.OPTIMA_HYBRID);
    }
    
}
