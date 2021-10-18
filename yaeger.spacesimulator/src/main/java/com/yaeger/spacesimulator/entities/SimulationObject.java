package com.yaeger.spacesimulator.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicCircleEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.yaeger.spacesimulator.OutOfBoundsTimer;
import com.yaeger.spacesimulator.services.AngleCalculatorService;
import com.yaeger.spacesimulator.services.SimulationPauseService;

import javafx.geometry.Point2D;

/**
 * An {@link SimulationObject} is the base class for all objects which can be
 * simulated. It extends all the functionality of a {@link DynamicCircleEntity}
 * so this object can be drawn on the screen.
 */
public abstract class SimulationObject extends DynamicCircleEntity
		implements Collider, Collided, TimerContainer, SceneBorderCrossingWatcher {
	private OutOfBoundsTimer outOfBoundsTimer;

	private double movementAngle;
	private boolean shouldBeDeleted;

	protected double volume, density;
	protected Coordinate2D velocity;

	Point2D frozenVelocity;

	/**
	 * Construct a new {@link SimulationObject}
	 *
	 * @param initialLocation is the location this object appears at on the screen
	 * @param velocity        is the direction + speed this object moves at when the
	 *                        simulation is running
	 * @param volume          is the initial weight of this object
	 * @param density         is the initial density of this object
	 */
	public SimulationObject(Coordinate2D initialLocation, Coordinate2D velocity, double volume, double density) {
		super(initialLocation);

		this.volume = volume;
		this.density = density;

		this.frozenVelocity = new Coordinate2D(0, 0);
		this.velocity = new Coordinate2D(0, 0);
		this.setVelocity(velocity);
	}

	@Override
	public void notifyBoundaryCrossing(SceneBorder border) {
		Coordinate2D pos = this.getPosition();
		if (pos.getX() < 0 || pos.getX() > getSceneWidth() || pos.getY() < 0 || pos.getY() > getSceneHeight()) {
			this.outOfBoundsTimer.start();
		} else {
			this.outOfBoundsTimer.stop();
		}
	}

	@Override
	public void setupTimers() {
		this.outOfBoundsTimer = new OutOfBoundsTimer(this, 1000);
		this.outOfBoundsTimer.pause();
		addTimer(outOfBoundsTimer);
	}

	@Override
	public void onCollision(Collider collidingObject) {
		SimulationObject other = (SimulationObject) collidingObject;
		double score = this.getVelocity().magnitude() + this.getMass();
		double otherScore = other.getVelocity().magnitude() + other.getMass();

		if (score > otherScore && !(other instanceof CentrePlanet)) {
			this.setVolume(this.getVolume() + other.getVolume());

			other.setShouldBeDeleted(true);
		}
	}

	/**
	 * Sets the velocity of this {@link SimulationObject} to zero, but stores the
	 * original velocity to be retrieved later using {@link #unfreezeVelocity()}.
	 */
	public void freezeVelocity() {
		this.frozenVelocity = this.frozenVelocity.add(this.velocity);
		this.velocity = this.velocity
				.add(new Point2D(this.frozenVelocity.getX() * -1, this.frozenVelocity.getY() * -1));
	}

	/**
	 * Restores the velocity of this {@link SimulationObject}, which was frozen
	 * using {@link #freezeVelocity()}.
	 */
	public void unfreezeVelocity() {
		this.velocity = this.velocity.add(this.frozenVelocity);
		this.frozenVelocity = this.frozenVelocity.subtract(this.velocity);
	}

	/**
	 * Update the movement of this object, so the internal Yaeger engine knows this
	 * object has changed its properties and has to update.
	 */
	public void updateMovement() {
		this.setMotion(this.getVelocity().magnitude(), this.movementAngle);
	}

	/**
	 * Return the volume of this simulatable object.
	 *
	 * @return The volume of this simulatable object.
	 */
	public double getVolume() {
		return volume;
	}

	/**
	 * Set the volume of this simulatable object.
	 *
	 * @param volume is the new volume of this object.
	 */
	public void setVolume(double volume) {
		this.volume = volume;
	}

	/**
	 * Return the density of this simulatable object.
	 *
	 * @return The density of this simulatable object.
	 */
	public double getDensity() {
		return this.density;
	}

	/**
	 * Set the density of this simulatable object.
	 *
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
	 * Return the vector this object uses internally to move in a specific
	 * direction. {@link Movable.getDirection()} can at times return a vector which
	 * is equal to this vector, this vector is not used by the physics calculator.
	 *
	 * @return A {@link Coordinate2D} vector which is used internally to move this
	 *         object.
	 */
	public Coordinate2D getVelocity() {
		return this.velocity;
	}

	/**
	 * Set the vector this object uses internally to move in a specific direction
	 *
	 * @param velocity is the new vector
	 */
	public void setVelocity(Coordinate2D velocity) {
		if (!SimulationPauseService.getInstance().getPaused()) {
			this.velocity = velocity;
			this.movementAngle = AngleCalculatorService.getInstance().calculateAngle(velocity);
		} else {
			this.frozenVelocity = velocity;
			this.movementAngle = AngleCalculatorService.getInstance().calculateAngle(frozenVelocity);
		}
	}

	/**
	 * Return the mass of this object (the mass is decided by multiplying the volume
	 * and density of this object)
	 *
	 * @return The mass of this simulatable object
	 */
	public double getMass() {
		return this.volume * this.density;
	}

	/**
	 * Return the angle of this object moves at around the screen. This angle is
	 * decided by the {@link SimulationObject.getMovementDirection} vector.
	 *
	 * @return The angle this object moves at around the screen.
	 */
	public double getMovementAngle() {
		return this.movementAngle;
	}

	/**
	 * Return the position of this object on the screen.
	 *
	 * @return The position of this object on the screen (in pixels)
	 */
	public Coordinate2D getPosition() {
		return getLocationInScene();
	}
}
