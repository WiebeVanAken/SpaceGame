package com.yaeger.spacesimulator.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicCircleEntity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Planet extends DynamicCircleEntity implements Simulatable {
	private double volume, density, velocity;
	private Coordinate2D simulatableDirection;
	
	public Planet(Coordinate2D initialLocation) {
		super(initialLocation);
		// TODO Auto-generated constructor stub
		this.setFill(new Color(1, 1, 1, 1));
		this.setShape(new Circle(30, getFill()));
	}

	@Override
	public double getVolume() {
		// TODO Auto-generated method stub
		return volume;
	}

	@Override
	public void setVolume(double volume) {
		// TODO Auto-generated method stub
		this.volume = volume;
	}

	@Override
	public double getDensity() {
		// TODO Auto-generated method stub
		return this.density;
	}

	@Override
	public void setDensity(double density) {
		// TODO Auto-generated method stub
		this.density = density;
	}

	@Override
	public double getVelocity() {
		// TODO Auto-generated method stub
		return this.velocity;
	}

	@Override
	public void setVelocity(double velocity) {
		// TODO Auto-generated method stub
		this.velocity = velocity;
	}

	@Override
	public Coordinate2D getSimulatableDirection() {
		// TODO Auto-generated method stub
		return this.simulatableDirection;
	}
	
	@Override
	public void setSimulatableDirection(Coordinate2D simulatableDirection) {
		// TODO Auto-generated method stub
		this.simulatableDirection = simulatableDirection;
	}

	@Override
	public double getMass() {
		// TODO Auto-generated method stub
		return this.volume * this.density;
	}
	
	@Override
	public void updateMovement() {
		// TODO Auto-generated method stub
		this.setMotion(this.velocity, this.simulatableDirection.angle(0, -1));
	}
}
