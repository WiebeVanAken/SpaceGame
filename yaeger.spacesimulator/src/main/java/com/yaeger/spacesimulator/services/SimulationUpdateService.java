package com.yaeger.spacesimulator.services;

import java.util.ArrayList;

import com.github.hanyaeger.api.Coordinate2D;
import com.yaeger.spacesimulator.entities.Simulatable;

public class SimulationUpdateService {
	private static SimulationUpdateService instance;

	private SimulationUpdateService() { }
	
	public void updateSimulatíon(ArrayList<Simulatable> simulatables) {
		simulatables.forEach(obj -> {
			obj.setSimulatableDirection(new Coordinate2D(1, -1));
			obj.setVelocity(1);
		});
		
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
