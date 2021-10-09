package com.yaeger.spacesimulator.entities;

import com.github.hanyaeger.api.Coordinate2D;

import javafx.scene.paint.Color;

public class Planet extends SimulationObject {

	/**
	 * @param initialLocation
	 * @param movementDirection
	 * @param velocity
	 * @param volume
	 * @param density
	 */
	public Planet(Coordinate2D initialLocation, Coordinate2D movementDirection, double velocity, double volume, double density, Color fill) {
		super(initialLocation, movementDirection, velocity, volume, density);
		
		this.setRadius(this.volume / this.density);
		this.setFill(fill);
	}
	
}
