package com.yaeger.spacesimulator.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.yaeger.spacesimulator.dto.ObjectPlacementDTO;

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
		
		this.setRadius(this.volume / this.density);
		this.setFill(fill);
	}
	
}
