package com.yaeger.spacesimulator.entities;

import com.github.hanyaeger.api.Coordinate2D;

public interface Simulatable {
	public abstract double getVolume();
	public abstract void setVolume(double volume);
	public abstract double getDensity();
	public abstract void setDensity(double density);
	public abstract double getVelocity();
	public abstract void setVelocity(double velocity);
	public abstract Coordinate2D getSimulatableDirection();
	public abstract void setSimulatableDirection(Coordinate2D simulatableDirection);
	public abstract double getMass();
	public abstract void updateMovement();
}
