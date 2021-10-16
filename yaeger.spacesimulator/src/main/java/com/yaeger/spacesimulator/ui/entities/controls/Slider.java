package com.yaeger.spacesimulator.ui.entities.controls;

import java.util.ArrayList;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.yaeger.spacesimulator.ui.entities.IObserver;
import com.yaeger.spacesimulator.ui.entities.ISubject;
import com.yaeger.spacesimulator.ui.entities.Rectangle;

import javafx.scene.paint.Color;

public class Slider extends CompositeEntity implements ISubject<Double> {

	private double baseWidth;
	private double minValue;
	private double maxValue;
	private double position;
	private ArrayList<IObserver<Double>> observers;

	protected Slider(Coordinate2D initialLocation, double baseWidth, double minValue, double maxValue)
			throws Exception {
		super(initialLocation);
		if (minValue >= maxValue)
			throw new Exception("Can only instantiate slider when min value is less then max value.");
		this.baseWidth = baseWidth;
		this.minValue = minValue;
		this.maxValue = maxValue;
		observers = new ArrayList<IObserver<Double>>();
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
		notifyObservers();
	}

	public double getBaseWidth() {
		return baseWidth;
	}

	@Override
	public void observe(IObserver<Double> observer) {
		observers.add(observer);
	}

	@Override
	public void unobserve(IObserver<Double> observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (IObserver<Double> o : observers) {
			o.update(this, getValue());
		}
	}
}
