package com.yaeger.spacesimulator.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicCircleEntity;

public abstract class SimulationObject extends DynamicCircleEntity {

	protected double volume, density, velocity;
	protected Coordinate2D movementDirection;
	
	public SimulationObject(Coordinate2D initialLocation, Coordinate2D movementDirection, double velocity, double volume, double density) {
		super(initialLocation);
		this.movementDirection = movementDirection;
		this.velocity = velocity;
		this.volume = volume;
		this.density = density;
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
	}

	public double getMass() {
		return this.volume * this.density;
	}
	
	public void updateMovement() {
		double angle = this.movementDirection.angle(0, 1);
//		
//		System.out.println(String.format("ANGLE %f", angle));
		this.setMotion(this.velocity, angle);
	}
}
