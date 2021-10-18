package com.yaeger.spacesimulator.dto;

import com.github.hanyaeger.api.Coordinate2D;
import com.yaeger.spacesimulator.entities.SimulationObject;
import com.yaeger.spacesimulator.scenes.SimulationScene;
import com.yaeger.spacesimulator.services.ConfigService;
import com.yaeger.spacesimulator.ui.entities.IObserver;
import com.yaeger.spacesimulator.ui.entities.ISubject;
import com.yaeger.spacesimulator.ui.entities.controls.DensityValueControl;
import com.yaeger.spacesimulator.ui.entities.controls.VolumeValueControl;

import javafx.scene.paint.Color;

/**
 * <p>
 * A {@code ObjectPlacementDTO} holds the placement data of the
 * {@link SimulationObject}, which is used as initial data when placing the
 * object in the {@link SimulationScene}.
 * </p>
 * <p>
 * It observes the {@link VolumeValueControl} and {@link DensityValueControl} in
 * the simulation, which will update this on change, so initial placing data can
 * be set.
 * </p>
 */
public class ObjectPlacementDTO implements IObserver<Double> {
	private double density, volume;
	private Coordinate2D startPosition, stopPosition;
	private boolean placing;
	private Color color;

	/**
	 * Create an new instance of {@link ObjectPlacementDTO}.
	 */
	public ObjectPlacementDTO() {
		this.color = Color.web(ConfigService.getValue("base-planet-color"));
	}

	/**
	 * <p>
	 * Reset the values this {@link ObjectPlacementDTO} holds.
	 * </p>
	 * <p>
	 * This should be called after a {@link SimulationObject} has been placed to
	 * reset values for the next placing.
	 * </p>
	 */
	public void reset() {
		startPosition = new Coordinate2D();
		stopPosition = new Coordinate2D();
		placing = false;
	}

	/**
	 * Returns the speed of this {@link ObjectPlacementDTO}.
	 *
	 * @return the speed as a {@code double}.
	 */
	public double getSpeed() {
		return getDirection().magnitude();
	}

	/**
	 * Returns the density of this {@link ObjectPlacementDTO}.
	 *
	 * @return the density as a {@code double}.
	 */
	public double getDensity() {
		return density;
	}

	/**
	 * Sets the density of this {@link ObjectPlacementDTO}.
	 *
	 * @param density the density as a {@code double}.
	 */
	public void setDensity(double density) {
		this.density = density;
	}

	/**
	 * Returns the volume of this {@link ObjectPlacementDTO}.
	 *
	 * @return the volume as a {@code double}.
	 */
	public double getVolume() {
		return volume;
	}

	/**
	 * Sets the volume of this {@link ObjectPlacementDTO}.
	 *
	 * @param volume the volume as a {@code double}.
	 */
	public void setVolume(double volume) {
		this.volume = volume;
	}

	/**
	 * Gets the start position of this {@link ObjectPlacementDTO}.
	 *
	 * @return the start position as a {@link Coordinate2D}.
	 */
	public Coordinate2D getStartPosition() {
		return startPosition;
	}

	/**
	 * Sets the start position of this {@link ObjectPlacementDTO}.
	 *
	 * @param startPosition the start position as a {@link Coordinate2D}.
	 */
	public void setStartPosition(Coordinate2D startPosition) {
		this.startPosition = startPosition;
	}

	/**
	 * Gets the stop position of this {@link ObjectPlacementDTO}.
	 *
	 * @return the stop position as a {@link Coordinate2D}.
	 */
	public Coordinate2D getStopPosition() {
		return stopPosition;
	}

	/**
	 * Sets the stop position of this {@link ObjectPlacementDTO}.
	 *
	 * @param stopPosition the stop position as a {@link Coordinate2D}.
	 */
	public void setStopPosition(Coordinate2D stopPosition) {
		this.stopPosition = stopPosition;
	}

	/**
	 * Gets the direction of this {@link ObjectPlacementDTO}.
	 *
	 * @return the direction as a {@link Coordinate2D}.
	 */
	public Coordinate2D getDirection() {
		return new Coordinate2D(stopPosition.subtract(startPosition.getX(), startPosition.getY()));
	}

	/**
	 * Sets the placing of this {@link ObjectPlacementDTO}.
	 *
	 * @param placing the stop position as a {@code boolean}.
	 */
	public void setPlacing(boolean placing) {
		this.placing = placing;
	}

	/**
	 * Gets the placing of this {@link ObjectPlacementDTO}.
	 *
	 * @return the placing as a {@code boolean}.
	 */
	public boolean getPlacing() {
		return this.placing;
	}

	/**
	 * Gets the color of this {@link ObjectPlacementDTO}.
	 *
	 * @return the color as a {@link Color}.
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Updates the values depending on the subject that is notifying.
	 * <p>
	 * This {@link ObjectPlacementDTO} observes the {@link VolumeValueControl} and
	 * {@link DensityValueControl}, which will notify this on change.
	 * </p>
	 */
	@Override
	public void update(ISubject<Double> subject, Double data) {
		if (subject instanceof VolumeValueControl) {
			setVolume(data);
		} else if (subject instanceof DensityValueControl) {
			setDensity(data);
		}
	}
}
