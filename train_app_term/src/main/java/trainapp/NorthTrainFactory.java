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
public class NorthTrainFactory extends TrainFactory {
    
    private Train train;
    private Direction direction = Direction.NORTH;
    
    @Override 
    public Train createTrain(TrainModel model) {
        if (model.equals(TrainModel.A)) {
            train = new NorthATrain();
        } else if (model.equals(TrainModel.B)) {
            train = new NorthBTrain();
        } else if (model.equals(TrainModel.C)) {
            train = new NorthCTrain();
        }
        
        return train;
    }
    
}
