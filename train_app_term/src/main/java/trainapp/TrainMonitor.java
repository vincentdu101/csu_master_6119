/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vincentdu
 */
public class TrainMonitor implements Monitor {
    
    TrainState trainState;
    List<Train> trains;
    
    public TrainMonitor() {
        trainState = TrainState.STOPPED;
        trains = new ArrayList<>();
    }
    
    @Override 
    public void update(TrainState newTrainState) {
        trainState = newTrainState;
    }
    
    @Override 
    public void addTrain(Train train) {
        trains.add(train);
    }
    
}
