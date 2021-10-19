package com.yaeger.spacesimulator.ui.entities.text;

import java.text.Format;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.yaeger.spacesimulator.ui.entities.EntityWrapper;
import com.yaeger.spacesimulator.ui.entities.IFormattableValue;
import com.yaeger.spacesimulator.ui.entities.IObserver;
import com.yaeger.spacesimulator.ui.entities.ISubject;
import com.yaeger.spacesimulator.ui.entities.IUpdatableValue;

/**
 * A {@code ValueField} is a concrete implementation of {@link CompositeEntity},
 * which is used to create a drawable value field.
 *
 * @param <T> The type specified for the value of this value field.
 */
public class ValueField<T> extends CompositeEntity implements IUpdatableValue<T>, IFormattableValue, IObserver<T> {

	private Value<T> value;
	private double width;

	/**
	 * Create a new instance of {@link ValueField} for the given initial location,
	 * width and initial value.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param width           the width as a {@code double}.
	 * @param initialValue    the initial value as a T specified for this
	 *                        {@link ValueField}.
	 */
	public ValueField(Coordinate2D initialLocation, double width, T initialValue) {
		this(initialLocation, width);
		value = new Value<T>(new Coordinate2D(5, 0), initialValue);
	}

	/**
	 * Create a new instance of {@link ValueField} for the given initial location,
	 * width, initial value and value format.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param width           the width as a {@code double}.
	 * @param initialValue    the initial value as a T specified for this
	 *                        {@link ValueField}.
	 * @param valueFormat     the format as a {@code String}.
	 */
	public ValueField(Coordinate2D initialLocation, double width, T initialValue, String valueFormat) {
		this(initialLocation, width);
		value = new Value<T>(new Coordinate2D(5, 0), initialValue, valueFormat);
	}

	/**
	 * Create a new instance of {@link ValueField} for the given initial location,
	 * width, initial value and value formatter.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param width           the width as a {@code double}.
	 * @param initialValue    the initial value as a T specified for this
	 *                        {@link ValueField}.
	 * @param valueFormatter  the formatter as a {@link Format}.
	 */
	public ValueField(Coordinate2D initialLocation, double width, T initialValue, Format valueFormatter) {
		this(initialLocation, width);
		value = new Value<T>(new Coordinate2D(5, 0), initialValue, valueFormatter);
	}

	/**
	 * Create a new instance of {@link ValueField} for the given initial location
	 * and width.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param width           the width as a {@code double}.
	 */
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

	@Override
	public void update(ISubject<T> subject, T data) {
		value.setValue(data);
	}
}
