package com.yaeger.spacesimulator.ui.entities;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;

import javafx.scene.paint.Color;

public class PreviewObject extends Circle {

	public PreviewObject(Coordinate2D initialLocation, double radius, Color fill) {
		super(initialLocation, radius, fill);
		this.setAnchorPoint(AnchorPoint.CENTER_CENTER);
	}

}
