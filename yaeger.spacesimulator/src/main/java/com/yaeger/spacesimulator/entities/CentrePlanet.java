package com.yaeger.spacesimulator.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collider;

import javafx.scene.paint.Color;

/**
 *
 * @author Wiebe van Aken
 *
 */
public class CentrePlanet extends Planet {

	/**
	 * @param initialLocation
	 * @param velocity
	 * @param volume
	 * @param density
	 * @param fill
	 */
	public CentrePlanet(Coordinate2D initialLocation, Coordinate2D velocity, double volume, double density,
			Color fill) {
		super(initialLocation, velocity, volume, density, fill);
	}

	@Override
	public void updateMovement() {
		// Empty on purpose
	}

	@Override
	public void onCollision(Collider collidingObject) {
		SimulationObject other = (SimulationObject) collidingObject;
		this.setVolume(this.getVolume() + other.getVolume());
		other.setShouldBeDeleted(true);
	}
}
