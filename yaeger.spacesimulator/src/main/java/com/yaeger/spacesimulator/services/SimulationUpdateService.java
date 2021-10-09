package com.yaeger.spacesimulator.services;

import java.util.ArrayList;

import com.yaeger.spacesimulator.entities.SimulationObject;

public class SimulationUpdateService {
	private static SimulationUpdateService instance;

	private SimulationUpdateService() { }
	
	public void updateSimulation(ArrayList<SimulationObject> simulatables) {
		simulatables.forEach(obj -> {
			obj.updateMovement();
		});
	}
	
	public static SimulationUpdateService getInstance() {
		if(instance == null) {
			instance = new SimulationUpdateService();
		}
		
		return instance;
	}
}
