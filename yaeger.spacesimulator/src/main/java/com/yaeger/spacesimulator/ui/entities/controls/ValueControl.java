package com.yaeger.spacesimulator.ui.entities.controls;

import java.text.Format;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.yaeger.spacesimulator.ui.entities.text.TitleValuePair;

public class ValueControl extends CompositeEntity {

	private TitleValuePair<Double> titleValuePair;
	private Slider slider;

	public ValueControl(Coordinate2D initialLocation, double width, String title, double initialValue, double minValue,
			double maxValue, String valueFormat) throws Exception {
		this(initialLocation, width, minValue, maxValue);
		titleValuePair = new TitleValuePair<Double>(new Coordinate2D(), title, initialValue, width, valueFormat);
	}

	public ValueControl(Coordinate2D initialLocation, double width, String title, double initialValue, double minValue,
			double maxValue, Format valueFormatter) throws Exception {
		this(initialLocation, width, minValue, maxValue);
		titleValuePair = new TitleValuePair<Double>(new Coordinate2D(), title, initialValue, width, valueFormatter);
	}

	public ValueControl(Coordinate2D initialLocation, double width, String title, double initialValue, double minValue,
			double maxValue) throws Exception {
		this(initialLocation, width, minValue, maxValue);
		titleValuePair = new TitleValuePair<Double>(new Coordinate2D(), title, initialValue, width);
	}

	private ValueControl(Coordinate2D initialLocation, double width, double minValue, double maxValue)
			throws Exception {
		super(initialLocation);
		slider = new Slider(new Coordinate2D(0, 60), width, minValue, maxValue);
	}

	public void setSubtitle(String subtitle) {
		titleValuePair.setSubtitle(subtitle);
	}

	@Override
	protected void setupEntities() {
		slider.setControlValue(titleValuePair);
		addEntity(titleValuePair);
		addEntity(slider);
	}

}
