package com.yaeger.spacesimulator.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collider;

import javafx.scene.paint.Color;

/**
 * A {@code CentrePlanet} is a {@link Planet} with the purpose to stay at the
 * initial location.
 */
public class CentrePlanet extends Planet {

	/**
	 * Create a new instance of {@link CentrePlanet}.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param velocity        the velocity as a {@link Coordinate2D}.
	 * @param volume          the volume as a {@code double}.
	 * @param density         the density as a {@code double}.
	 * @param fill            the fill color as a {@link Color}.
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
