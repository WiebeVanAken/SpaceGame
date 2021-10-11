package com.yaeger.spacesimulator.data;

import com.github.hanyaeger.api.Coordinate2D;
import com.yaeger.spacesimulator.services.ConfigService;

import javafx.scene.paint.Color;

public class ObjectPlacementData {
	private double density = 25, volume = 500;
	private Coordinate2D startPosition, stopPosition;
	private boolean placing;
	private Color color;
	
	/**
	 * @param density
	 * @param volume
	 * @param startPosition
	 * @param stopPosition
	 * @param color
	 */
	public ObjectPlacementData(double density, double volume, Coordinate2D startPosition, Coordinate2D stopPosition,
			Color color) {
		this.density = density;
		this.volume = volume;
		this.startPosition = startPosition;
		this.stopPosition = stopPosition;
		this.color = color;
	}

	public ObjectPlacementData() {
		this(
			Double.parseDouble(ConfigService.getValue("base-planet-volume")),
			Double.parseDouble(ConfigService.getValue("base-planet-density")),
			new Coordinate2D(),
			new Coordinate2D(),
			Color.web(ConfigService.getValue("base-planet-color"))
		);
	}
	
	public void reset() {
		startPosition = new Coordinate2D();
		stopPosition = new Coordinate2D();
		placing = false;
	}
	
	public double getSpeed() {
		return getDirection().magnitude();
	}

	public double getDensity() {
		return density;
	}

	public void setDensity(double density) {
		this.density = density;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public Coordinate2D getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Coordinate2D startPosition) {
		this.startPosition = startPosition;
	}

	public Coordinate2D getStopPosition() {
		return stopPosition;
	}

	public void setStopPosition(Coordinate2D stopPosition) {
		this.stopPosition = stopPosition;
	}

	public Coordinate2D getDirection() {
		return new Coordinate2D(stopPosition.subtract(startPosition.getX(), startPosition.getY()));
	}
	
	public void setPlacing(boolean placing) {
		this.placing = placing;
	}
	
	public boolean getPlacing() {
		return this.placing;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	
}
