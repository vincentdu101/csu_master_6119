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
public class Train {
    
    String description;
    String name;
    List<Seat> seats;
    Station startingStation;
    Station currentStation;
    List<Monitor> monitors;
    TrainState trainState = TrainState.STOPPED;
    
    public void setupSeats() {
        seats = new ArrayList<>();
        
        for (int i = 0; i < 6; i++) {
            seats.add(new SpecialReservedSeat());
        }
        
        for (int i = 0; i < 10; i++) {
            seats.add(new RegularSeat());
        }
        
        for (int i = 0; i < 4; i++) {
            seats.add(new TableSeat());
        }
    }
    
    public void linkToStartingStation(Station startingStation) {
        this.startingStation = startingStation;
    }
    
    public void addMonitor(Monitor monitor) {
        monitor.addTrain(this);
        monitors.add(monitor);
    }
    
    public void startTrain() {
        trainState = TrainState.STARTED;
        monitors.forEach(m -> m.update(trainState));
    }
}
