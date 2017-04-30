/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

/**
 *
 * @author vincentdu
 */
public interface Monitor {
    
    public void update(TrainState trainState);
    
    public void addTrain(Train train);
    
}
