package com.yaeger.spacesimulator.shared.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;

import javafx.scene.paint.Color;

public class ValueFieldWrapper extends RectangleEntity {

	public ValueFieldWrapper(Coordinate2D initialPosition, Size size) {
		super(initialPosition, size);
		setStrokeWidth(0.5);
		setStrokeColor(Color.WHITESMOKE);
	}

}
