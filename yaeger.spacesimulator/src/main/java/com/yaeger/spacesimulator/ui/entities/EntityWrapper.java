package com.yaeger.spacesimulator.ui.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;

import javafx.scene.paint.Color;

public class EntityWrapper extends RectangleEntity {

	public EntityWrapper(Coordinate2D initialPosition, Size size) {
		super(initialPosition, size);
		setStrokeWidth(0.5);
		setStrokeColor(Color.WHITESMOKE);
	}

}
