/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 *
 * @author vincentdu
 */
public class NorthAStation extends Station {

    @Autowired
    JdbcTemplate jdbcTemplate;
    List<Monitor> monitors;
    StationService stationService;
    boolean trainStationed = false;

    public NorthAStation(StationService stationService) {
        this.stationService = stationService;
        setupStation();
    }

    public void setupStation() {
        Station currentStation = stationService.findStationByDescription("North A Station");
        this.loadStation(currentStation);
    }

//    public abstract void trainArrived();
//
//    public abstract void trainLeft();
//
//    public abstract void trainNotification();
}
