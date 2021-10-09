package com.yaeger.spacesimulator.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicCircleEntity;
import com.yaeger.spacesimulator.services.AngleCalculatorService;

/**
 @author Wiebe van Aken
 @version 1.0.0
 
 An {@link SimulationObject} is the base class for all objects which can be simulated.
 It extends all the functionality of a {@link DynamicCircleEntity} so this object can be drawn on the screen.
 */
public abstract class SimulationObject extends DynamicCircleEntity {

	private double movementAngle;
	
	protected double volume, density;
	protected Coordinate2D movementDirection;

	/**
	 * Construct a new {@link SimulationObject}
	 * @param initialLocation is the location this object appears at on the screen
	 * @param movementDirection is the direction this object moves when the simulation is running
	 * @param velocity is the initial velocity this object uses to move along the screen
	 * @param volume is the initial weight of this object
	 * @param density is the initial density of this object
	 */
	public SimulationObject(Coordinate2D initialLocation, Coordinate2D movementDirection, double velocity, double volume, double density) {
		super(initialLocation);
		this.volume = volume;
		this.density = density;

		this.setMovementDirection(movementDirection);
		this.setVelocity(velocity);
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
	 * Get the velocity (movement speed) of this simulatable object. 
	 * @return The speed this object uses to move over the screen.
	 */
	public double getVelocity() {
		return this.movementDirection.magnitude();
	}

	/**
	 * Set the velocity (movement speed) of this simulatable object.
	 * This speed represents the rate at which the object moves around the screen.
	 * @param velocity is the new rate this object will move at.
	 */
	public void setVelocity(double velocity) {
		this.movementDirection.add(
			movementDirection.getX() * velocity - movementDirection.getX(),
			movementDirection.getY() * velocity - movementDirection.getY()
		);
	}
	
	/**
	 * Return the vector this object uses internally to move in a specific direction.
	 * {@link Movable.getDirection()} can at times return a vector which is equal to this vector, this vector is not used by the physics calculator.
	 * @return A {@link Coordinate2D} vector which is used internally to move this object.
	 */
	public Coordinate2D getMovementDirection() {
		return this.movementDirection;
	}
	
	/**
	 * Set the vector this object uses internally to move in a specific direction
	 * @param movementDirection is the new vector 
	 */
	public void setMovementDirection(Coordinate2D movementDirection) {
		this.movementDirection = movementDirection;
		this.movementAngle = AngleCalculatorService.getInstance().calculateAngle(movementDirection);
	}

	/**
	 * Return the mass of this object (the mass is decided by multiplying the volume and density of this object)
	 * @return The mass of this simulatable object
	 */
	public double getMass() {
		return this.volume * this.density;
	}
	
	/**
	 * Return the angle this object moves at around the screen.
	 * This angle is decided by the {@link SimulationObject.getMovementDirection} vector.
	 * @return The angle this object moves at around the screen.
	 */
	public double getMovementAngle() {
		return this.movementAngle;
	}
	
	/**
	 * Update the movement of this object, so the internal Yaeger engine knows this object has changed its properties and has to update
	 */
	public void updateMovement() {
		this.setMotion(this.getVelocity(), this.movementAngle);
	}
}
