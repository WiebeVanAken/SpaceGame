package com.yaeger.spacesimulator.ui.entities.controls;

import java.text.Format;
import java.util.ArrayList;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.yaeger.spacesimulator.ui.entities.IObserver;
import com.yaeger.spacesimulator.ui.entities.ISubject;
import com.yaeger.spacesimulator.ui.entities.text.TitleValuePair;

/**
 * A {@code ValueControl} is a concrete implementation of
 * {@link CompositeEntity}, which is used to create a value control that
 * consists of a title, value and slider.
 * <p>
 * A {@code ValueControl} observes its own {@link Slider} and is currently not
 * meant to observe any other {@link ISubject}.
 * </p>
 * <p>
 * Others of type {@link IObserver} can observe this control to be notified when
 * the value of this control changes.
 * </p>
 *
 */
public class ValueControl extends CompositeEntity implements ISubject<Double>, IObserver<Double> {

	private TitleValuePair<Double> titleValuePair;
	private Slider slider;
	private ArrayList<IObserver<Double>> observers = new ArrayList<IObserver<Double>>();

	/**
	 * Create a new instance of this {@link ValueControl} for the given initial
	 * location, width, title, initial value, min value, max value and value format.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param width           the width as a {@code double}.
	 * @param title           the title as a {@code String}.
	 * @param initialValue    the initial value as a {@code double}.
	 * @param minValue        the min value as a {@code double}.
	 * @param maxValue        the max value as a {@code double}.
	 * @param valueFormat     the value format as a {@code String}.
	 * @throws Exception the exception that is thrown when the max value of the is
	 *                   not greater than the min value.
	 */
	public ValueControl(Coordinate2D initialLocation, double width, String title, double initialValue, double minValue,
			double maxValue, String valueFormat) throws Exception {
		this(initialLocation, width, minValue, maxValue);
		titleValuePair = new TitleValuePair<Double>(new Coordinate2D(), title, initialValue, width, valueFormat);
	}

	/**
	 * Create a new instance of this {@link ValueControl} for the given initial
	 * location, width, title, initial value, min value, max value and value
	 * formatter.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param width           the width as a {@code double}.
	 * @param title           the title as a {@code String}.
	 * @param initialValue    the initial value as a {@code double}.
	 * @param minValue        the min value as a {@code double}.
	 * @param maxValue        the max value as a {@code double}.
	 * @param valueFormatter  the value formatter as a {@link Format}.
	 * @throws Exception the exception that is thrown when the max value of the is
	 *                   not greater than the min value.
	 */
	public ValueControl(Coordinate2D initialLocation, double width, String title, double initialValue, double minValue,
			double maxValue, Format valueFormatter) throws Exception {
		this(initialLocation, width, minValue, maxValue);
		titleValuePair = new TitleValuePair<Double>(new Coordinate2D(), title, initialValue, width, valueFormatter);
	}

	/**
	 * Create a new instance of this {@link ValueControl} for the given initial
	 * location, width, title, initial value, min value and max value.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param width           the width as a {@code double}.
	 * @param title           the title as a {@code String}.
	 * @param initialValue    the initial value as a {@code double}.
	 * @param minValue        the min value as a {@code double}.
	 * @param maxValue        the max value as a {@code double}.
	 * @throws Exception the exception that is thrown when the max value of the is
	 *                   not greater than the min value.
	 */
	public ValueControl(Coordinate2D initialLocation, double width, String title, double initialValue, double minValue,
			double maxValue) throws Exception {
		this(initialLocation, width, minValue, maxValue);
		titleValuePair = new TitleValuePair<Double>(new Coordinate2D(), title, initialValue, width);
	}

	/**
	 * Create a new instance of this {@link ValueControl} for the given initial
	 * location, width, min value and max value.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param width           the width as a {@code double}.
	 * @param minValue        the min value as a {@code double}.
	 * @param maxValue        the max value as a {@code double}.
	 * @throws Exception the exception that is thrown when the max value of the is
	 *                   not greater than the min value.
	 */
	private ValueControl(Coordinate2D initialLocation, double width, double minValue, double maxValue)
			throws Exception {
		super(initialLocation);
		slider = new Slider(new Coordinate2D(0, 60), width, minValue, maxValue);
	}

	/**
	 * Used to set the subtitle of the {@link TitleValuePair} of this control.
	 *
	 * @param subtitle the subtitle as a {@code String}.
	 */
	public void setSubtitle(String subtitle) {
		titleValuePair.setSubtitle(subtitle);
	}

	@Override
	protected void setupEntities() {
		slider.observe(titleValuePair);
		slider.observe(this);
		addEntity(titleValuePair);
		addEntity(slider);
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

	@Override
	public void update(ISubject<Double> subject, Double data) {
		notifyObservers();
	}

	/**
	 * Used to get the current value of this control.
	 *
	 * @return the value as a {@code double}.
	 */
	public double getValue() {
		return slider.getValue();
	}
}
