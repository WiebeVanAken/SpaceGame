package com.yaeger.spacesimulator.ui.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;

import javafx.scene.paint.Color;

public class Rectangle extends RectangleEntity {

	public Rectangle(Coordinate2D initialPosition, Size size, Color color) {
		super(initialPosition, size);
		this.setFill(color);
	}

}
