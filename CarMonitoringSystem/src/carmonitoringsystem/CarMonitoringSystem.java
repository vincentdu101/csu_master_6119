/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carmonitoringsystem;

/**
 *
 * @author vdu
 */
public class CarMonitoringSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CSUCar car = new CSUCar();
        
        SimpleMonitor simpleMonitor = new SimpleMonitor();
        MediumMonitor mediumMonitor = new MediumMonitor();
        AdvancedMonitor advancedMonitor = new AdvancedMonitor();
        
        car.addMonitor(simpleMonitor);
        car.addMonitor(mediumMonitor);
        car.addMonitor(advancedMonitor);
        
        car.setRepairState(State.NO_NEED_REPAIR);
        car.setRepairState(State.IMMENIENT_REPAIR);
        car.setRepairState(State.MUST_REPAIR);
    }
    
}
