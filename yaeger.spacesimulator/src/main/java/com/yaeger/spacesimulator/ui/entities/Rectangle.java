package com.yaeger.spacesimulator.ui.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;

import javafx.scene.paint.Color;

/**
 * A {@code Rectangle} is a concrete implementation of {@link RectangleEntity},
 * which is used to create a drawable rectangle.
 *
 */
public class Rectangle extends RectangleEntity {

	/**
	 * Create a new instance of this {@link Rectangle} for the given initial
	 * location, size and fill color.
	 *
	 * @param initialPosition the initial position as a {@link Coordinate2D}.
	 * @param size            the size as a {@link Size}.
	 * @param color           the fill color as a {@link Color}.
	 */
	public Rectangle(Coordinate2D initialPosition, Size size, Color color) {
		super(initialPosition, size);
		this.setFill(color);
	}

}
