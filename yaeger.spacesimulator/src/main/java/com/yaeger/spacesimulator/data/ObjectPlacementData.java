package com.yaeger.spacesimulator.data;

import com.github.hanyaeger.api.Coordinate2D;

import javafx.scene.paint.Color;

public class ObjectPlacementData {
	private double density, volume;
	private Coordinate2D startPosition, stopPosition;
	private boolean placing;
	private Color color;
	
	public ObjectPlacementData() {
		this.startPosition = new Coordinate2D();
		this.stopPosition = new Coordinate2D();
		this.color = new Color(1, 1, 1, 1);
	}
	
	public void reset() {
		startPosition = new Coordinate2D();
		stopPosition = new Coordinate2D();
		placing = false;
	}
	
	public double getVelocity() {
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

	@Override
	public String toString() {
		return "ObjectPlacementData [density=" + density + ", volume=" + volume + ", startPosition=" + startPosition
				+ ", stopPosition=" + stopPosition + ", placing=" + placing + ", color=" + color + "]";
	}
	
	
}
