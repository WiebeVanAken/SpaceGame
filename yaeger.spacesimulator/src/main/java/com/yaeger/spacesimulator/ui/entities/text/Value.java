package com.yaeger.spacesimulator.ui.entities.text;

import java.text.Format;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.yaeger.spacesimulator.services.ConfigService;
import com.yaeger.spacesimulator.ui.entities.IFormattableValue;
import com.yaeger.spacesimulator.ui.entities.IObserver;
import com.yaeger.spacesimulator.ui.entities.ISubject;
import com.yaeger.spacesimulator.ui.entities.IUpdatableValue;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * A {@code Value} is a {@link TextEntity}, which is used to create a drawable
 * text value of the specified type.
 *
 * @param <T> the type of the value to be displayed.
 */
public class Value<T> extends TextEntity implements IUpdatableValue<T>, IFormattableValue, IObserver<T> {

	private String format;
	private Format formatter;

	/**
	 * Create a new instance of this {@link Value} for the given initial location
	 * and initial value.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param initialValue    the initial value as the specified T.
	 */
	public Value(Coordinate2D initialLocation, T initialValue) {
		this(initialLocation, String.valueOf(initialValue));
	}

	/**
	 * Create a new instance of this {@link Value} for the given initial location,
	 * initial value and value format.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param initialValue    the initial value as the specified T.
	 * @param valueFormat     the value format as a {@code String}.
	 */
	public Value(Coordinate2D initialLocation, T initialValue, String valueFormat) {
		this(initialLocation, String.format(valueFormat, initialValue));
		format = valueFormat;
	}

	/**
	 * \ Create a new instance of this {@link Value} for the given initial location,
	 * initial value and value formatter.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param initialValue    the initial value as the specified T.
	 * @param valueFormatter  the value formatter as a {@link Format}.
	 */
	public Value(Coordinate2D initialLocation, T initialValue, Format valueFormatter) {
		this(initialLocation, valueFormatter.format(initialValue));
		formatter = valueFormatter;
	}

	/**
	 * Create a new instance of this {@link Value} for the given initial location
	 * and initial value.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param initialValue    the initial value as {@code String}.
	 */
	private Value(Coordinate2D initialLocation, String initialValue) {
		super(initialLocation, initialValue);
		setFill(Color.WHITESMOKE);
		setFont(Font.font(ConfigService.getValue("font-family"), FontWeight.SEMI_BOLD,
				Integer.parseInt(ConfigService.getValue("font-size-m"))));
	}

	@Override
	public void setValue(T value) {
		if (formatter != null)
			setText(formatter.format(value));
		else if (format != null)
			setText(String.format(format, value));
		else
			setText(String.valueOf(value));
	}

	@Override
	public void setFormat(String format) {
		this.format = format;
		formatter = null;
	}

	@Override
	public void setFormatter(Format formatter) {
		this.formatter = formatter;
		format = null;
	}

	@Override
	public void update(ISubject<T> subject, T data) {
		setValue(data);
	}
}
