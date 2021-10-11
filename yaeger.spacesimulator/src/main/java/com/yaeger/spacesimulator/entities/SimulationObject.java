package com.yaeger.spacesimulator.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicCircleEntity;
import com.yaeger.spacesimulator.services.AngleCalculatorService;

/**
 @author Wiebe van Aken
 @version 1.0.0
 
 An {@link SimulationObject} is the base class for all objects which can be simulated.
 It extends all the functionality of a {@link DynamicCircleEntity} so this object can be drawn on the screen.
 */
public abstract class SimulationObject extends DynamicCircleEntity implements Collider, Collided {

	private double movementAngle;
	private boolean shouldBeDeleted;
	
	protected double volume, density;
	protected Coordinate2D velocity;

	/**
	 * Construct a new {@link SimulationObject}
	 * @param initialLocation is the location this object appears at on the screen
	 * @param velocity is the direction + speed this object moves at when the simulation is running
	 * @param volume is the initial weight of this object
	 * @param density is the initial density of this object
	 */
	public SimulationObject(Coordinate2D initialLocation, Coordinate2D velocity, double volume, double density) {
		super(initialLocation);
		
		System.out.println(initialLocation);
		
		this.volume = volume;
		this.density = density;
		this.setVelocity(velocity);
	}
	
	
	@Override
	public void onCollision(Collider collidingObject) {
		SimulationObject other = (SimulationObject)collidingObject;
		double score = this.getVelocity().magnitude() + this.getMass();
		double otherScore = other.getVelocity().magnitude() + other.getMass();
		
		if(score > otherScore) {
			this.setVolume(this.getVolume() + other.getVolume());
			
			other.setShouldBeDeleted(true);
		}
	}

	/**
	 * Update the movement of this object, so the internal Yaeger engine knows this object has changed its properties and has to update
	 */
	public void updateMovement() {
		this.setMotion(this.getVelocity().magnitude(), this.movementAngle);
	}
	
	/**
	 * Return the volume of this simulatable object.
	 * @return The volume of this simulatable object.
	 */
	public double getVolume() {
		return volume;
	}
	
	/**
	 * Set the volume of this simulatable object.
	 * @param volume is the new volume of this object.
	 */
	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	/**
	 * Return the density of this simulatable object.
	 * @return The density of this simulatable object.
	 */
	public double getDensity() {
		return this.density;
	}

	/**
	 * Set the density of this simulatable object.
	 * @param density is the new density of this object.
	 */
	public void setDensity(double density) {
		this.density = density;
	}
	
	/**
	 * @return If this object should be deleted
	 */
	public boolean getShouldBeDeleted() {
		return shouldBeDeleted;
	}

	/**
	 * @param shouldBeDeleted the shouldBeDeleted to set
	 */
	public void setShouldBeDeleted(boolean shouldBeDeleted) {
		this.shouldBeDeleted = shouldBeDeleted;
	}


	/**
	 * Return the vector this object uses internally to move in a specific direction.
	 * {@link Movable.getDirection()} can at times return a vector which is equal to this vector, this vector is not used by the physics calculator.
	 * @return A {@link Coordinate2D} vector which is used internally to move this object.
	 */
	public Coordinate2D getVelocity() {
		return this.velocity;
	}
	
	/**
	 * Set the vector this object uses internally to move in a specific direction
	 * @param velocity is the new vector 
	 */
	public void setVelocity(Coordinate2D velocity) {
		this.velocity = velocity;
		this.movementAngle = AngleCalculatorService.getInstance().calculateAngle(velocity);
	}

	/**
	 * Return the mass of this object (the mass is decided by multiplying the volume and density of this object)
	 * @return The mass of this simulatable object
	 */
	public double getMass() {
		return this.volume * this.density;
	}
	
	/**
	 * Return the angle of this object moves at around the screen.
	 * This angle is decided by the {@link SimulationObject.getMovementDirection} vector.
	 * @return The angle this object moves at around the screen.
	 */
	public double getMovementAngle() {
		return this.movementAngle;
	}
	
	/**
	 * Return the position of this object on the screen.
	 * @return The position of this object on the screen (in pixels)
	 */
	public Coordinate2D getPosition() {
		return getLocationInScene();
	}
}
