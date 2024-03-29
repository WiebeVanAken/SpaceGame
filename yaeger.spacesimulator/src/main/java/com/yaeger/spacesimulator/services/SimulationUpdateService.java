package com.yaeger.spacesimulator.services;

import java.util.ArrayList;

import com.yaeger.spacesimulator.entities.SimulationObject;

import javafx.geometry.Point2D;

/**
 *
 *
 * A {@link SimulationUpdateService} is a service to physics update all the
 * simulatable objects in the scene.
 */
public class SimulationUpdateService {
	private static SimulationUpdateService instance;
	private final double GRAV_CONST = Double.parseDouble(ConfigService.getValue("gravity-constant"));

	private ArrayList<SimulationObject> objectsToBeRemoved;

	private SimulationUpdateService() {
		this.objectsToBeRemoved = new ArrayList<>();
	}

	/**
	 * Calculate and update all the positions of the simulationobjects
	 *
	 * @param simulationObjects is the list of all simulationobjects
	 */
	public void updateSimulation(ArrayList<SimulationObject> simulationObjects) {
		if (!SimulationPauseService.getInstance().getPaused()) {
			processPhysicsCalculations(simulationObjects);
			updateObjects(simulationObjects);
		}

		if (objectsToBeRemoved.size() > 0)
			removeObjects(simulationObjects);
	}

	/**
	 * Process the physics calculations for all simulation objects
	 *
	 * @param objects the objects as {@code ArrayList<SimulationObject>}.
	 */
	private void processPhysicsCalculations(ArrayList<SimulationObject> objects) {
		double distanceSquared, distance;
		Point2D acceleration, force, forceDir;

		// Calculate gravity based object movement
		for (SimulationObject body : objects) {
			for (SimulationObject otherBody : objects) {
				if (body == otherBody)
					continue;

				distance = otherBody.distanceTo(body);
				distanceSquared = distance * distance;

				forceDir = otherBody.getPosition().subtract(body.getPosition()).normalize();
				force = forceDir.multiply(GRAV_CONST * body.getMass() * otherBody.getMass() / distanceSquared);
				acceleration = new Point2D(force.getX() / body.getMass(), force.getY() / body.getMass());

				body.setVelocity(body.getVelocity().add(acceleration));
			}
		}
	}

	/**
	 * Update all the objects in the scene
	 *
	 * @param objects the objects as {@code ArrayList<SimulationObject>}.
	 */
	private void updateObjects(ArrayList<SimulationObject> objects) {
		objects.forEach(obj -> {
			obj.updateMovement();

			if (obj.getShouldBeDeleted())
				objectsToBeRemoved.add(obj);
		});
	}

	/**
	 * Remove all objects which should be removed from the simulation
	 *
	 * @param objects the objects as {@code ArrayList<SimulationObject>}.
	 */
	private void removeObjects(ArrayList<SimulationObject> objects) {
		this.objectsToBeRemoved.forEach(obj -> {
			objects.remove(obj);
			obj.remove();
		});

		this.objectsToBeRemoved.clear();
	}

	/**
	 * Retrieve an instance of this service class
	 *
	 * @return an instance of this singleton service class
	 */
	public static SimulationUpdateService getInstance() {
		if (instance == null) {
			instance = new SimulationUpdateService();
		}

		return instance;
	}
}
