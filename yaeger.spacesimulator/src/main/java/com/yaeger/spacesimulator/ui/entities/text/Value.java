package com.yaeger.spacesimulator.ui.entities.text;

import java.text.Format;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.yaeger.spacesimulator.services.ConfigService;
import com.yaeger.spacesimulator.ui.entities.IUpdatableValue;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Value<T> extends TextEntity implements IUpdatableValue<T> {

	private String format;
	private Format formatter;

	public Value(Coordinate2D initialLocation, T initialValue) {
		this(initialLocation, String.valueOf(initialValue));
	}

	public Value(Coordinate2D initialLocation, T initialValue, String valueFormat) {
		this(initialLocation, String.format(valueFormat, initialValue));
		format = valueFormat;
	}

	public Value(Coordinate2D initialLocation, T initialValue, Format valueFormatter) {
		this(initialLocation, valueFormatter.format(initialValue));
		formatter = valueFormatter;
	}

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

}
