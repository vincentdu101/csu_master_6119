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
public class CSUCar {
    
    ArrayList<Monitor> monitors;
    State repairState;
    
    public CSUCar() {
        repairState = State.NO_NEED_REPAIR;
        monitors = new ArrayList<>();
    }
    
    public void addMonitor(Monitor monitor) {
        monitors.add(monitor);
        monitor.addCar(this);
    }
    
    public void setRepairState(State repairState) {
        this.repairState = repairState;
        notifyMonitors();
    }
    
    public void notifyMonitors() {
        monitors.forEach(m -> m.update(repairState));
    }
    
}
