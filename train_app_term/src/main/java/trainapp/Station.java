/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

import java.util.Date;

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
    StationMonitor stationMonitor;
    StationService stationService;
    TrainMonitor trainMonitor;
    TrainStationProgressService trainStationProgressService;
    Boolean trainStationed = false;
    Train currentTrain;


    public Station(){}

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

    public Station getNextStation(Direction direction) {
        if (direction.equals(Direction.NORTH)) {
            return stationService.findStationById(nextNorthStationId);
        } else {
            return stationService.findStationById(nextSouthStationId);
        }
    }

    public void setupStation(String description) {
        Station currentStation = stationService.findStationByDescription(description);
        trainMonitor.addStation(this);
        loadStation(currentStation);
    }

    public void trainArrived(Train train) {
        trainStationed = true;
        currentTrain = train;
        trainStationProgressService.create(currentTrain, this.id);
        trainNotification(StationState.TRAIN_ARRIVED);
    }

    public void trainLeft() {
        trainStationed = false;
        currentTrain = null;
        trainStationProgressService.delete(this.id);
        trainNotification(StationState.TRAIN_LEFT);
    }

    public void trainNotification(StationState stationState) {
        stationMonitor.update(stationState);
    };

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void loadStation(Station station) {
        id = station.getId();
        description = station.getDescription();
        nextNorthStationId = station.getNextNorthStationId();
        nextSouthStationId = station.getNextSouthStationId();
        createdAt = station.getCreatedAt();
        modifiedAt = station.getModifiedAt();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
