package com.yaeger.spacesimulator.services;

import java.util.ArrayList;

import com.yaeger.spacesimulator.entities.SimulationObject;

import javafx.geometry.Point2D;

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
	private final double GRAV_CONST = 0.1D;
	
	/**
	 * Calculate & update all the positions of the simulationobjects in the scene
	 * @param simulationObjects is the list of all simulationobjects
	 */
	public void updateSimulation(ArrayList<SimulationObject> simulationObjects) {
		double distanceSquared, distance;
		Point2D acceleration, force, forceDir;
		
		for(SimulationObject body : simulationObjects) {
			for(SimulationObject otherBody : simulationObjects) {
				if(body == otherBody)
					continue;
				
				distance = otherBody.distanceTo(body);
				distanceSquared = distance * distance;
				
				forceDir = otherBody.getPosition().subtract(body.getPosition()).normalize();
				force = forceDir.multiply(GRAV_CONST * body.getMass() * otherBody.getMass() / distanceSquared);
				acceleration = new Point2D(force.getX() / body.getMass(), force.getY() / body.getMass());
				
				body.setVelocity(body.getVelocity().add(acceleration));
			}
		}
		
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
