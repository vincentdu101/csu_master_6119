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
public enum CarModel {
    
    SOUL_EV("Soul Ev"),
    OPTIMA_HYBRID("Optima Hybrid"),
    RIO("Rio");
    
    String model;
    
    CarModel(String model) {
        this.model = model;
    } 
    
    public String model() {
        return model;
    }
    
}
