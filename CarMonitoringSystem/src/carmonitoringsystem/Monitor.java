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
public interface Monitor {
    
    public void update(State repairState);
    
    public void addCar(CSUCar car);
    
}
