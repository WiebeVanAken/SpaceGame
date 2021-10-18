package com.yaeger.spacesimulator.entities;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.yaeger.spacesimulator.services.ConfigService;

import javafx.scene.paint.Color;

public class Planet extends SimulationObject {
	private static double radiusScale = Double.parseDouble(ConfigService.getValue("planet-radius-scale"));
	
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

	private void updateRadius() {
		this.setRadius((this.volume / this.density) * Planet.radiusScale);
	}

}
