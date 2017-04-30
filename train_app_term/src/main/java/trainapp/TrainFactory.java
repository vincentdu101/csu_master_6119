/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

/**
 *
 * @author vincentdu
 */
public abstract class TrainFactory {

	public Train prepareTrain(TrainModel model, Station startingStation) {
		Train train = createTrain(model);

		train.setupSeats();
		train.linkToStartingStation(startingStation);
		train.startTrain();

		return train;
	}

	abstract Train createTrain(TrainModel model);
    
}
