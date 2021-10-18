package com.yaeger.spacesimulator.services;

import javafx.geometry.Point2D;

/**
 * An {@link AngleCalculatorService} is a helper service to calculate movement
 * vector angles. The general purpose of this service is to make sure angles are
 * correct, due to the weird behaviour of {@link Point2D.getAngle(double x,
 * double y)}.
 */
public class AngleCalculatorService {
	private static AngleCalculatorService instance;

	private AngleCalculatorService() {
	}

	/**
	 * Calculate the angle (in degrees) the input vector is at.
	 *
	 * @param dir is the input vector to use for calculating.
	 * @return the angle (in degrees) of the input vector.
	 */
	public double calculateAngle(Point2D dir) {
		Point2D normDir = dir.normalize();
		return (normDir.getX() >= 0 ? new Point2D(0, 1).angle(normDir) : new Point2D(0, -1).angle(normDir) + 180);
	}

	/**
	 * Retrieve an instance of this service class
	 *
	 * @return an instance of this singleton service class
	 */
	public static AngleCalculatorService getInstance() {
		if (instance == null) {
			instance = new AngleCalculatorService();
		}

		return instance;
	}
}
