package com.yaeger.spacesimulator.dto;

import com.github.hanyaeger.api.Coordinate2D;
import com.yaeger.spacesimulator.services.ConfigService;
import com.yaeger.spacesimulator.ui.entities.IObserver;
import com.yaeger.spacesimulator.ui.entities.ISubject;
import com.yaeger.spacesimulator.ui.entities.controls.DensityValueControl;
import com.yaeger.spacesimulator.ui.entities.controls.VolumeValueControl;

import javafx.scene.paint.Color;

public class ObjectPlacementDTO implements IObserver<Double> {
	private double density, volume;
	private Coordinate2D startPosition, stopPosition;
	private boolean placing;
	private Color color;

	public ObjectPlacementDTO() {
		//this.volume = Integer.parseInt(ConfigService.getValue("base-planet-volume")); // uncommenting this wil fuck up
		//this.density = Integer.parseInt(ConfigService.getValue("base-planet-density")); // uncommenting this wil fuck up																// placing
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

	@Override
	public void update(ISubject<Double> subject, Double data) {
		if (subject instanceof VolumeValueControl) {
			setVolume(data);
		} else if (subject instanceof DensityValueControl) {
			setDensity(data);
		}
	}

	@Override
	public String toString() {
		return String.format(
				"ObjectPlacementDTO [density=%s, volume=%s, startPosition=%s, stopPosition=%s, color=%s, getSpeed()=%s, getDirection()=%s]",
				density, volume, startPosition, stopPosition, color, getSpeed(), getDirection());
	}
	
	
}