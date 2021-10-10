package com.yaeger.spacesimulator.services;

import java.util.ArrayList;

import com.yaeger.spacesimulator.entities.SimulationObject;

/**
 * 
 * @author Wiebe van Aken
 * @version 1.0.0
 * 
 * A {@link SimulationUpdateService} is a service to physics update all the simulatable objects in the scene. 
 */
public class SimulationUpdateService {
	private static SimulationUpdateService instance;

	private SimulationUpdateService() { }
	
	/**
	 * Calculate & update all the positions of the simulationobjects in the scene
	 * @param simulationObjects is the list of all simulationobjects
	 */
	public void updateSimulation(ArrayList<SimulationObject> simulationObjects) {
		simulationObjects.forEach(obj -> {
			obj.updateMovement();
		});
	}
	
	/**
	 * Retrieve an instance of this service class
	 * @return an instance of this singleton service class
	 */
	public static SimulationUpdateService getInstance() {
		if(instance == null) {
			instance = new SimulationUpdateService();
		}
		
		return instance;
	}
}
