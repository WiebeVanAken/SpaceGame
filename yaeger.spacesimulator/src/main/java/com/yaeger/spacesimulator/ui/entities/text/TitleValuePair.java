package com.yaeger.spacesimulator.ui.entities.text;

import java.text.Format;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.yaeger.spacesimulator.ui.entities.IFormattableValue;
import com.yaeger.spacesimulator.ui.entities.IObserver;
import com.yaeger.spacesimulator.ui.entities.ISubject;
import com.yaeger.spacesimulator.ui.entities.IUpdatableValue;

/**
 * A {@code TitleValuePair} is a concrete implementation of
 * {@link CompositeEntity}, which is used to create a pair of a title and value
 * of a specific type.
 *
 * @param <T> the type of the value to be displayed.
 */
public class TitleValuePair<T> extends CompositeEntity implements IUpdatableValue<T>, IFormattableValue, IObserver<T> {

	private ValueField<T> valueField;
	private String title;
	private String subtitle;

	/**
	 * Create a new instance of this {@link TitleValuePair} for the given initial
	 * location and title.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param title           the title as a {@code String}.
	 */
	private TitleValuePair(Coordinate2D initialLocation, String title) {
		super(initialLocation);
		this.title = title;
	}

	/**
	 * Create a new instance of this {@link TitleValuePair} for the given initial
	 * location, title, initialValue and width.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param title           the title as a {@code String}.
	 * @param initialValue    the initial value as a T specified for this
	 *                        {@link TitleValuePair}.
	 * @param width           the width as a {@code double}.
	 */
	public TitleValuePair(Coordinate2D initialLocation, String title, T initialValue, double width) {
		this(initialLocation, title);
		valueField = new ValueField<T>(new Coordinate2D(0, 25), width, initialValue);
	}

	/**
	 * Create a new instance of this {@link TitleValuePair} for the given initial
	 * location, title, initialValue, width and value format.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param title           the title as a {@code String}.
	 * @param initialValue    the initial value as a T specified for this
	 *                        {@link TitleValuePair}.
	 * @param width           the width as a {@code double}.
	 * @param valueFormat     the value format as a {@code String}.
	 */
	public TitleValuePair(Coordinate2D initialLocation, String title, T initialValue, double width,
			String valueFormat) {
		this(initialLocation, title);
		valueField = new ValueField<T>(new Coordinate2D(0, 25), width, initialValue, valueFormat);
	}

	/**
	 * Create a new instance of this {@link TitleValuePair} for the given initial
	 * location, title, initialValue, width and value formatter.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param title           the title as a {@code String}.
	 * @param initialValue    the initial value as a T specified for this
	 *                        {@link TitleValuePair}.
	 * @param width           the width as a {@code double}.
	 * @param valueFormatter  the value formatter as a {@link Format}.
	 */
	public TitleValuePair(Coordinate2D initialLocation, String title, T initialValue, double width,
			Format valueFormatter) {
		this(initialLocation, title);
		valueField = new ValueField<T>(new Coordinate2D(0, 25), width, initialValue, valueFormatter);
	}

	/**
	 * Used to set the subtitle of this {@link TitleValuePair}.
	 *
	 * @param subtitle the subtitle as a {@code String}.
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	@Override
	protected void setupEntities() {
		CompositeTitle compositeTitle = new CompositeTitle(new Coordinate2D(), title, subtitle);
		addEntity(compositeTitle);
		addEntity(valueField);
	}

	@Override
	public void setValue(T value) {
		valueField.setValue(value);
	}

	@Override
	public void setFormat(String format) {
		valueField.setFormat(format);
	}

	@Override
	public void setFormatter(Format formatter) {
		valueField.setFormatter(formatter);
	}

	@Override
	public void update(ISubject<T> subject, T data) {
		valueField.setValue(data);
	}
}
