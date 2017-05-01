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
public class SouthBTrain extends Train {

    public SouthBTrain(TrainMonitor trainMonitor) {
        this.trainMonitor = trainMonitor;
        createTrain();
    }

    public void createTrain() {
        this.name = "South B Station";
        this.description = "South B Station";
        this.direction = Direction.NORTH;
        this.create();
    }
    
}
