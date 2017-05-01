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
public class NorthCTrain extends Train {

    public NorthCTrain(TrainMonitor trainMonitor) {
        this.trainMonitor = trainMonitor;
        createTrain();
    }

    public void createTrain() {
        this.name = "North C Station";
        this.description = "North C Station";
        this.direction = Direction.NORTH;
        this.create();
    }
    
}
