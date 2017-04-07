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
public class MediumMonitor implements Monitor {
    
    CSUCar car;
    State repairState;
    
    public MediumMonitor() {
        repairState = State.NO_NEED_REPAIR;
    }
    
    @Override 
    public void addCar(CSUCar car) {
        if (this.car == null) {
            this.car = car;
        } else {
            System.out.println("Cannot add more than one car to Simple Monitor");
        }
    }
    
    @Override
    public void update(State newRepairState) {
        repairState = newRepairState;
        System.out.println("Medium Monitor: " + repairState.getReason());
    }
    
}
