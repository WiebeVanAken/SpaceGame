package com.yaeger.spacesimulator.ui.entities.controls;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.yaeger.spacesimulator.ui.entities.IControl;
import com.yaeger.spacesimulator.ui.entities.IUpdatableValue;
import com.yaeger.spacesimulator.ui.entities.Rectangle;

import javafx.scene.paint.Color;

public class Slider extends CompositeEntity implements IControl {

	private double baseWidth;
	private double minValue;
	private double maxValue;
	private double position;
	private IUpdatableValue<Double> controlValue;

	protected Slider(Coordinate2D initialLocation, double baseWidth, double minValue, double maxValue)
			throws Exception {
		super(initialLocation);
		if (minValue >= maxValue)
			throw new Exception("Can only instantiate slider when min value is less then max value.");
		this.baseWidth = baseWidth;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	@Override
	protected void setupEntities() {
		Rectangle sliderBase = new Rectangle(new Coordinate2D(), new Size(baseWidth, 4), Color.WHITESMOKE);
		sliderBase.setAnchorPoint(AnchorPoint.CENTER_LEFT);

		SliderHandle sliderHandle = new SliderHandle(new Coordinate2D(), new Size(7, 20), Color.CORAL, this);

		addEntity(sliderBase);
		addEntity(sliderHandle);
	}

	public double getValue() {
		return position / baseWidth * (maxValue - minValue) + minValue;
	}

	public void setPosition(double position) {
		this.position = position;
		updateValue();
	}

	public double getBaseWidth() {
		return baseWidth;
	}

	@Override
	public void updateValue() {
		controlValue.setValue(getValue());
	}

	@Override
	public void setControlValue(IUpdatableValue<Double> value) {
		controlValue = value;
	}

}
