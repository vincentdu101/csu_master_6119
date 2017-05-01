/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

/**
 *
 * @author vdu
 */
public class SouthATrain extends Train {

    public SouthATrain(TrainMonitor trainMonitor) {
        this.trainMonitor = trainMonitor;
        createTrain();
    }

    public void createTrain() {
        this.name = "South A Station";
        this.description = "South A Station";
        this.direction = Direction.NORTH;
        this.create();
    }
    
}
