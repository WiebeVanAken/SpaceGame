package com.yaeger.spacesimulator.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicCircleEntity;

import javafx.geometry.Point2D;

public abstract class SimulationObject extends DynamicCircleEntity {

	private double movementAngle;
	protected double volume, density, velocity;
	protected Coordinate2D movementDirection;
	
	public SimulationObject(Coordinate2D initialLocation, Coordinate2D movementDirection, double velocity, double volume, double density) {
		super(initialLocation);
		this.velocity = velocity;
		this.volume = volume;
		this.density = density;
		
		this.setMovementDirection(movementDirection);
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getDensity() {
		return this.density;
	}

	public void setDensity(double density) {
		this.density = density;
	}

	public double getVelocity() {
		return this.velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public Coordinate2D getSimulatableDirection() {
		return this.movementDirection;
	}
	
	public void setMovementDirection(Coordinate2D movementDirection) {
		this.movementDirection = movementDirection;
		this.movementAngle = calculateAngle(movementDirection);
	}

	public double getMass() {
		return this.volume * this.density;
	}
	
	public void updateMovement() {
		this.setMotion(this.velocity, this.movementAngle);
	}
	
	private double calculateAngle(Coordinate2D dir) {
		Point2D normDir = dir.normalize();
		double angle = 0;
		
		if(normDir.getX() >= 0) {
			angle = new Point2D(0, 1).angle(normDir);
		}
		
		if(normDir.getX() < 0) {
			angle = new Point2D(0, -1).angle(normDir) + 180;
		}
		System.out.println(angle);
		return angle;
	}
}
