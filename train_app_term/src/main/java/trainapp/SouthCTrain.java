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
public class SouthCTrain extends Train {

    public SouthCTrain(TrainMonitor trainMonitor) {
        this.trainMonitor = trainMonitor;
        createTrain();
    }

    public void createTrain() {
        this.name = "South C Station";
        this.description = "South C Station";
        this.direction = Direction.SOUTH;
        this.create();
    }
    
}
