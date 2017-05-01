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

	TrainMonitor monitor;
	TrainStationProgressService trainStationProgressService;

	public TrainFactory(TrainMonitor monitor, TrainStationProgressService trainStationProgressService) {
		this.monitor = monitor;
		this.trainStationProgressService = trainStationProgressService;
	}

	public Train prepareTrain(TrainModel model, Station startingStation) {
		Train train = createTrain(model);

        trainStationProgressService.create(train, startingStation.getId());
		train.setupSeats();
		train.linkToStartingStation(startingStation);
		beginTravel(train);

		return train;
	}

	public void beginTravel(Train train) {
        train.startTrain();
    }

	abstract Train createTrain(TrainModel model);
    
}
