/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;


import java.util.List;

/**
 *
 * @author vincentdu
 */
public abstract class Station {

    private Integer id;
    private String description = "Train Station";
    boolean trainStationed = false;
    Station nextNorthStation;
    Station nextSouthStation;
    List<Monitor> monitors;
    
    public String getDescription() {
        return description;
    }
    
    public Station getNextNorthStation() {
        return nextNorthStation;
    }
    
    public Station getNextSouthStation() {
        return nextSouthStation;
    }
    
    public void setNextNorthStation(Station nextNorthStation) {
        this.nextNorthStation = nextNorthStation;
    }
    
    public void setNextSouthStation(Station nextSouthStation) {
        this.nextSouthStation = nextSouthStation;
    }   
    
    public abstract void trainArrived();
    
    public abstract void trainLeft();
    
    public abstract void trainNotification();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
