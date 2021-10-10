package com.yaeger.spacesimulator.ui.entities.text;

import java.text.Format;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.yaeger.spacesimulator.ui.entities.EntityWrapper;
import com.yaeger.spacesimulator.ui.entities.IUpdatableValue;

public class ValueField<T> extends CompositeEntity implements IUpdatableValue<T> {

	private Value<T> value;
	private double width;

	public ValueField(Coordinate2D initialLocation, double width, T initialValue) {
		this(initialLocation, width);
		value = new Value<T>(new Coordinate2D(5, 0), initialValue);
	}

	public ValueField(Coordinate2D initialLocation, double width, T initialValue, String valueFormat) {
		this(initialLocation, width);
		value = new Value<T>(new Coordinate2D(5, 0), initialValue, valueFormat);
	}

	public ValueField(Coordinate2D initialLocation, double width, T initialValue, Format valueFormatter) {
		this(initialLocation, width);
		value = new Value<T>(new Coordinate2D(5, 0), initialValue, valueFormatter);
	}

	private ValueField(Coordinate2D initialLocation, double width) {
		super(initialLocation);
		this.width = width;
	}

	@Override
	protected void setupEntities() {
		var valueWrapper = new EntityWrapper(new Coordinate2D(), new Size(width, 20));

		valueWrapper.setAnchorPoint(AnchorPoint.CENTER_LEFT);
		value.setAnchorPoint(AnchorPoint.CENTER_LEFT);
		addEntity(valueWrapper);
		addEntity(value);
	}

	@Override
	public void setValue(T value) {
		this.value.setValue(value);
	}

	@Override
	public void setFormat(String format) {
		this.value.setFormat(format);
	}

	@Override
	public void setFormatter(Format formatter) {
		value.setFormatter(formatter);

	}

}
