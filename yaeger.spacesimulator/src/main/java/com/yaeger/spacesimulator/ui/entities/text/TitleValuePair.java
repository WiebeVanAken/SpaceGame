package com.yaeger.spacesimulator.ui.entities.text;

import java.text.Format;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.yaeger.spacesimulator.ui.entities.IFormattableValue;
import com.yaeger.spacesimulator.ui.entities.IObserver;
import com.yaeger.spacesimulator.ui.entities.ISubject;
import com.yaeger.spacesimulator.ui.entities.IUpdatableValue;

public class TitleValuePair<T> extends CompositeEntity implements IUpdatableValue<T>, IFormattableValue, IObserver<T> {

	private ValueField<T> valueField;
	private String title;
	private String subtitle;

	private TitleValuePair(Coordinate2D initialLocation, String title) {
		super(initialLocation);
		this.title = title;
	}

	public TitleValuePair(Coordinate2D initialLocation, String title, T initialValue, double width) {
		this(initialLocation, title);
		valueField = new ValueField<T>(new Coordinate2D(0, 25), width, initialValue);
	}

	public TitleValuePair(Coordinate2D initialLocation, String title, T initialValue, double width,
			String valueFormat) {
		this(initialLocation, title);
		valueField = new ValueField<T>(new Coordinate2D(0, 25), width, initialValue, valueFormat);
	}

	public TitleValuePair(Coordinate2D initialLocation, String title, T initialValue, double width,
			Format valueFormatter) {
		this(initialLocation, title);
		valueField = new ValueField<T>(new Coordinate2D(0, 25), width, initialValue, valueFormatter);
	}

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
