package com.yaeger.spacesimulator.ui.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;

import javafx.scene.paint.Color;

/**
 * A {@code EntityWrapper} is a concrete implementation of
 * {@link RectangleEntity}, which is used to create a drawable border.
 *
 */
public class EntityWrapper extends RectangleEntity {

	/**
	 * Create a new instance of {@link EntityWrapper} for the given initial position
	 * and size.
	 *
	 * @param initialPosition the initial position as a {@link Coordinate2D}.
	 * @param size            the size as a {@link Size}.
	 */
	public EntityWrapper(Coordinate2D initialPosition, Size size) {
		super(initialPosition, size);
		setStrokeWidth(0.5);
		setStrokeColor(Color.WHITESMOKE);
	}

}
