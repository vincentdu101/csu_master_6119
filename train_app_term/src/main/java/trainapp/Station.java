/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author vincentdu
 */
public class Station {

    Integer id;
    String description = "Train Station";
    Integer nextNorthStationId;
    Integer nextSouthStationId;
    Date createdAt;
    Date modifiedAt;

    public Station() {}

    public Station(Integer id,
                   String description,
                   Integer nextNorthStationId,
                   Integer nextSouthStationId,
                   Date createdAt,
                   Date modifiedAt) {
        this.id = id;
        this.description = description;
        this.nextNorthStationId = nextNorthStationId;
        this.nextSouthStationId = nextSouthStationId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public String getDescription() {
        return description;
    }
    
    public Integer getNextNorthStationId() {
        return nextNorthStationId;
    }
    
    public Integer getNextSouthStationId() {
        return nextSouthStationId;
    }

    public void setNextNorthStationId(Integer nextNorthStationId) {
        this.nextNorthStationId = nextNorthStationId;
    }
    
    public void setNextSouthStationId(Integer nextSouthStationId) {
        this.nextSouthStationId = nextSouthStationId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void loadStation(Station station) {
        this.id = station.getId();
        this.description = station.getDescription();
        this.nextNorthStationId = station.getNextNorthStationId();
        this.nextSouthStationId = station.getNextSouthStationId();
        this.createdAt = station.getCreatedAt();
        this.modifiedAt = station.getModifiedAt();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
