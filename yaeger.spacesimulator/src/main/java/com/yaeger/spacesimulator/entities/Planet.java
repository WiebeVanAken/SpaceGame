package com.yaeger.spacesimulator.entities;

import com.github.hanyaeger.api.Coordinate2D;

import javafx.scene.paint.Color;

public class Planet extends SimulationObject {

	/**
	 * @param initialLocation
	 * @param velocity
	 * @param volume
	 * @param density
	 */
	public Planet(Coordinate2D initialLocation, Coordinate2D velocity, double volume, double density, Color fill) {
		super(initialLocation, velocity, volume, density);

		this.updateRadius();
		this.setFill(fill);
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

	private void updateRadius() {
		this.setRadius(this.volume / this.density);
	}

}
