package com.yaeger.spacesimulator.ui.entities.controls;

import java.text.Format;
import java.util.ArrayList;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.yaeger.spacesimulator.ui.entities.IObserver;
import com.yaeger.spacesimulator.ui.entities.ISubject;
import com.yaeger.spacesimulator.ui.entities.text.TitleValuePair;

public class ValueControl extends CompositeEntity implements ISubject<Double>, IObserver<Double> {

	private TitleValuePair<Double> titleValuePair;
	private Slider slider;
	private ArrayList<IObserver<Double>> observers = new ArrayList<IObserver<Double>>();

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
		slider.observe(titleValuePair);
		slider.observe(this);
		addEntity(titleValuePair);
		addEntity(slider);
	}

	@Override
	public void observe(IObserver<Double> observer) {
		observers.add(observer);
		System.out.println("observe valuecontrol");
	}

	@Override
	public void unobserve(IObserver<Double> observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (IObserver<Double> o : observers) {
			o.update(this, slider.getValue());
		}
	}

	@Override
	public void update(ISubject<Double> subject, Double data) {
		notifyObservers();
	}

}
