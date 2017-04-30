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

    private Integer id;

    private String description;
    private String name;
    List<Seat> seats;
    private Station startingStation;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
