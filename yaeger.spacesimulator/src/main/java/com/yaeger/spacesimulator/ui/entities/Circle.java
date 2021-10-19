package com.yaeger.spacesimulator.ui.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.CircleEntity;

import javafx.scene.paint.Color;

/**
 * A {@code Circle} is a concrete implementation of {@link CircleEntity}, which
 * is used to create a drawable circle.
 *
 */
public class Circle extends CircleEntity {

	/**
	 * Create a new instance of {@link Circle} for the given initial location,
	 * radius and fill.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param radius          the radius as a {@code double}.
	 * @param fill            the fill color as a {@link Color}.
	 */
	public Circle(Coordinate2D initialLocation, double radius, Color fill) {
		super(initialLocation);
		this.setRadius(radius);
		this.setFill(fill);
	}

}
