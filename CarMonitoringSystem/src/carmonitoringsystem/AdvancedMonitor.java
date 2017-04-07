/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carmonitoringsystem;

import java.util.ArrayList;

/**
 *
 * @author vdu
 */
public class AdvancedMonitor implements Monitor {
    
    State repairState;
    ArrayList<CSUCar> cars;
    
    public AdvancedMonitor() {
        repairState = State.NO_NEED_REPAIR;
        cars = new ArrayList<>();
    }
    
    @Override
    public void addCar(CSUCar car) {
        if (cars.size() < 2) {
            cars.add(car);
        } else {
            System.out.println("Cannot add more than two cars to Advanced Monitor");
        }
    }
    
    @Override
    public void update(State newRepairState) {
        repairState = newRepairState;
        System.out.println("Advanced Monitor: " + repairState.getReason());
    }    
    
}
