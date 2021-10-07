package com.yaeger.spacesimulator.entities.controls;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.CompositeEntity;

public class Slider extends CompositeEntity {

	private int width;
	private double minValue;
	private double maxValue;

	protected Slider(Coordinate2D initialLocation, int width, double minValue, double maxValue) {
		super(initialLocation);
		this.width = width;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	@Override
	protected void setupEntities() {

	}

}
