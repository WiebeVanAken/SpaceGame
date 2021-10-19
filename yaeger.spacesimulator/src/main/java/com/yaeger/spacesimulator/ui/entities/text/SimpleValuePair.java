package com.yaeger.spacesimulator.ui.entities.text;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.CompositeEntity;

/**
 * A {@code SimpleValuePair} is a concrete implementation of
 * {@link CompositeEntity}, which is used to create a value pair that contains
 * of a left- and right value.
 *
 */
public class SimpleValuePair extends CompositeEntity {

	Value<String> leftValue;
	Value<String> rightValue;

	/**
	 * Create a new instance of this {@link SimpleValuePair} for the given initial
	 * location, width, left value and right value.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param width           the widht as a {@code double}.
	 * @param leftValue       the left value as a {@code String}.
	 * @param rightValue      the right value as a {@code String}.
	 */
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
