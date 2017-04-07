/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carmonitoringsystem;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vdu
 */
public class SimpleMonitor implements Monitor {
    
    State repairState;
    CSUCar car;
    
    public SimpleMonitor() {
        repairState = State.NO_NEED_REPAIR;
    }
    
    @Override 
    public void addCar(CSUCar car) {
        if (this.car == null) {
            this.car = car;
        } else {
            System.out.print("Cannot add more than one car to Simple Monitor");
        }
    }
    
    @Override
    public void update(State newRepairState) {
        repairState = newRepairState;
        if (State.NO_NEED_REPAIR.equals(repairState)) {
            System.out.println("Simple Monitor : " + repairState.getReason());
        } else {
            System.out.println("Simple Monitor : Repairs needed");
        }
    }
    
}
