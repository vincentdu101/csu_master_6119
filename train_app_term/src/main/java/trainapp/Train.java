/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 *
 * @author vincentdu
 */
public class Train {

    private Integer id;

    String description;
    String name;
    List<Seat> seats;
    Station startingStation;
    Station currentStation;
    List<Monitor> monitors;
    TrainState trainState = TrainState.STOPPED;
    Date created_at;
    Date modified_at;

    @Autowired
    JdbcTemplate jdbcTemplate;
    
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

    public void create() {
        String SQL = "INSERT INTO station (description, next_north_station_id, next_south_station_id) " +
                "VALUES (:description, :next_north_station_id, :next_south_station_id)";
        Map namedParameters = new HashMap();
        namedParameters.put("name", this.name);
        namedParameters.put("description", this.description);
        namedParameters.put("start_station_id", 1);
        namedParameters.put("create_at", new Date());
        namedParameters.put("modified_at", new Date());
        jdbcTemplate.update(SQL, namedParameters);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
