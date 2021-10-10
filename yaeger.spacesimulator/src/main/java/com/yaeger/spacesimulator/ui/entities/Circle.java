package com.yaeger.spacesimulator.ui.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.CircleEntity;

import javafx.scene.paint.Color;

public class Circle extends CircleEntity {

	public Circle(Coordinate2D initialLocation, double radius, Color fill) {
		super(initialLocation);
		this.setRadius(radius);
		this.setFill(fill);
	}

}
