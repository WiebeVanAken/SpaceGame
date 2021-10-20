package com.yaeger.spacesimulator.entities;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.yaeger.spacesimulator.scenes.SimulationScene;
import com.yaeger.spacesimulator.services.ConfigService;

import javafx.scene.paint.Color;

/**
 * A {@code Planet} is a concrete implementation of {@link SimulationObject}
 * that can be added to the {@link SimulationScene}.
 *
 */
public class Planet extends SimulationObject {
	private static double radiusScale = Double.parseDouble(ConfigService.getValue("planet-radius-scale"));

	/**
	 * Create a new instance of {@link Planet}.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param velocity        the velocity as a {@link Coordinate2D}.
	 * @param volume          the volume as a {@code double}.
	 * @param density         the density as a {@code double}.
	 * @param fill            the fill color as a {@link Color}.
	 */
	public Planet(Coordinate2D initialLocation, Coordinate2D velocity, double volume, double density, Color fill) {
		super(initialLocation, velocity, volume, density);

		this.updateRadius();
		this.setFill(fill);
		this.setAnchorPoint(AnchorPoint.CENTER_CENTER);
	}

	@Override
	public void setVolume(double volume) {
		super.setVolume(volume);
		this.updateRadius();
	}

	@Override
	public void setDensity(double density) {
		super.setDensity(density);
		this.updateRadius();
	}

	/**
	 * Sets the radius of this {@link Planet} by using its volume and density.
	 */
	private void updateRadius() {
		this.setRadius(this.volume / this.density * radiusScale);
	}

}
