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

/**
 * A {@code Slider} is a concrete implementation of {@link CompositeEntity},
 * which is used to create a slider control.
 * <p>
 * A {@code Slider} is a {@link ISubject} of type {@link Double}, which can be
 * observed by observers of this type.
 * </p>
 *
 */
public class Slider extends CompositeEntity implements ISubject<Double> {

	private double baseWidth;
	private double minValue;
	private double maxValue;
	private double position;
	private ArrayList<IObserver<Double>> observers;

	/**
	 * Create a new instance of this {@link Slider} for the given initial location,
	 * base width, min value and max value.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param baseWidth       the base width as a {@code double}.
	 * @param minValue        the min value as a {@code double}.
	 * @param maxValue        the max value as a {@code double}.
	 * @throws Exception the exception that get thrown when te max value is not
	 *                   greater then the min value.
	 */
	public Slider(Coordinate2D initialLocation, double baseWidth, double minValue, double maxValue) throws Exception {
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

	/**
	 * Used to get the value of this {@link Slider}.
	 *
	 * @return the value as a {@code double}.
	 */
	public double getValue() {
		return position / baseWidth * (maxValue - minValue) + minValue;
	}

	/**
	 * Used to set the position of this {@link Slider}.
	 * <p>
	 * This method is called when the {@link SliderHandle} of this {@link Slider} is
	 * moved.
	 * </p>
	 * <p>
	 * When this method is called, this {@link Slider} will notify its observers.
	 * </p>
	 *
	 * @param position the position as a {@code double}.
	 */
	public void setPosition(double position) {
		this.position = position;
		notifyObservers();
	}

	/**
	 * Used to get the base width of this {@link Slider}.
	 *
	 * @return the base widht as a {@code double}.
	 */
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
