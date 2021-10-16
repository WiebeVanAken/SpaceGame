package com.yaeger.spacesimulator.ui.entities.text;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.CompositeEntity;

public class SimpleValuePair extends CompositeEntity {

	Value<String> leftValue;
	Value<String> rightValue;

	public SimpleValuePair(Coordinate2D initialLocation, double width, String leftValue, String rightValue) {
		super(initialLocation);
		this.leftValue = new Value<String>(new Coordinate2D(), leftValue);
		this.rightValue = new Value<String>(new Coordinate2D(width, 0), rightValue);
	}

	@Override
	protected void setupEntities() {
		leftValue.setAnchorPoint(AnchorPoint.BOTTOM_LEFT);
		rightValue.setAnchorPoint(AnchorPoint.BOTTOM_RIGHT);
		addEntity(leftValue);
		addEntity(rightValue);
	}

}
