package com.yaeger.spacesimulator.ui.entities;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.yaeger.spacesimulator.services.ConfigService;

import javafx.scene.paint.Color;

/**
 * A {@code PreviewObject} is a concrete implementation of {@link Circle}, which
 * is used to create a drawable preview simulation object.
 *
 */
public class PreviewObject extends Circle {

	/**
	 * Create a new instance of this {@link PreviewObject} for the given initial
	 * location, radius and fill color.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param radius          the radius as a {@code double}.
	 * @param fill            the fill color as {@link Color}.
	 */
	public PreviewObject(Coordinate2D initialLocation, double radius, Color fill) {
		super(initialLocation, radius * Double.parseDouble(ConfigService.getValue("planet-radius-scale")), fill);
		this.setAnchorPoint(AnchorPoint.CENTER_CENTER);
	}

}
