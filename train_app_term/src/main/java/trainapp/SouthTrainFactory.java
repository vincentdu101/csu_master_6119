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
public class SouthTrainFactory extends TrainFactory {
    
    private Train train; 
    private Direction direction = Direction.SOUTH;
    
    @Override 
    public Train createTrain(TrainModel model) {
        if (model.equals(TrainModel.A)) {
            train = new SouthATrain();
        } else if (model.equals(TrainModel.B)) {
            train = new SouthBTrain();
        } else if (model.equals(TrainModel.C)) {
            train = new SouthCTrain();
        }
        
        return train;
    }
    
}
