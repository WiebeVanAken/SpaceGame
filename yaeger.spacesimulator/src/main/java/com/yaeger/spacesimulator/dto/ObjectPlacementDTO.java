package com.yaeger.spacesimulator.data;

import com.github.hanyaeger.api.Coordinate2D;
import com.yaeger.spacesimulator.services.ConfigService;

import javafx.scene.paint.Color;

public class ObjectPlacementData {
	private double density = 25, volume = 500;
	private Coordinate2D startPosition, stopPosition;
	private boolean placing;
	private Color color;
	
	public ObjectPlacementData() {
		this.startPosition = new Coordinate2D();
		this.stopPosition = new Coordinate2D();
		
		this.volume = Integer.parseInt(ConfigService.getValue("base-planet-volume"));
		this.density = Integer.parseInt(ConfigService.getValue("base-planet-density"));
		this.color = Color.web(ConfigService.getValue("base-planet-color"));
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
